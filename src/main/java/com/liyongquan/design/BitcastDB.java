package com.liyongquan.design;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 参考bitcast的DB实现
 * <p>
 * 顺序写，随机读
 */
@Slf4j
public class BitcastDB {
    public static final String LOG_INDEX_FILENAME = "index";
    public static final String OP_PUT = "PUT";
    public static final String OP_RM = "RM";

    private Map<Integer, RandomAccessFile> readerMap;
    private RandomAccessFile writer;

    public static final int BUFFER_MX_SIZE = 1024;
    public static final int LOG_MX_SIZE = 1024 * 1024;

    private Map<String, CommandPos> index;
    private String path;

    //日志压缩相关
    private int curLogId;
    //已经压缩的日志，一般是curLogId-1
    private int compactLogId;
    //记录curLogId和compactLogId
    private RandomAccessFile logIndexRW;
    private volatile boolean running;


    public BitcastDB(String path) {
        index = new HashMap<>();
        readerMap = new HashMap<>();
        this.path = path;
        this.running = false;
    }

    public void start() throws IOException {
        running = true;
        load();
        final BitcastDB db = this;
        Thread checkCompact = new Thread(() -> {
            try {
                db.compact();
                Thread.sleep(1000);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        checkCompact.start();
    }

    public void stop() {
        running = false;
    }


    private void load() throws IOException {
        index.clear();
        //加载curLogId和compactLogId
        logIndexRW = new RandomAccessFile(buildFilename(path, LOG_INDEX_FILENAME), "rw");
        if (logIndexRW.length() == 0) {
            //空文件
            curLogId = 0;
            compactLogId = -1;
            logIndexRW.writeInt(curLogId);
            logIndexRW.writeInt(compactLogId);
        } else {
            curLogId = logIndexRW.readInt();
            compactLogId = logIndexRW.readInt();
        }
        writer = new RandomAccessFile(buildFilename(path, curLogId), "rw");
        writer.seek(writer.length());
        if (compactLogId >= 0) {
            readerMap.put(compactLogId, new RandomAccessFile(buildFilename(path, String.valueOf(compactLogId)), "r"));
            //扫描一遍日志进行数据加载
            load0(readerMap.get(compactLogId));
        }
        readerMap.put(curLogId, new RandomAccessFile(buildFilename(path, curLogId), "r"));
        load0(readerMap.get(curLogId));
    }

    private void load0(RandomAccessFile reader) throws IOException {
        //扫描一遍日志进行数据加载
        int b;
        byte[] buffer = new byte[BUFFER_MX_SIZE];
        int pos = 0;
        while ((b = reader.read()) != -1) {
            int b2 = reader.read();
            int b3 = reader.read();
            int b4 = reader.read();
            if ((b | b2 | b3 | b4) < 0)
                throw new EOFException();
            //读前4个byte，这是内容的长度
            int len = ((b << 24) + (b2 << 16) + (b3 << 8) + (b4 << 0));
            reader.read(buffer, 0, len);
            Command cmd = JSON.parseObject(buffer, 0, len, Charset.defaultCharset(), Command.class);
            if (OP_PUT.equals(cmd.op)) {
                index.put(cmd.key, new CommandPos(pos, curLogId));
            } else if (OP_RM.equals(cmd.op)) {
                index.remove(cmd.key);
            } else {
                throw new IllegalArgumentException("命令异常");
            }
            pos += 4 + len;
        }
    }

    private static String buildFilename(String path, int logId) {
        StringBuilder sb = new StringBuilder(path);
        if (path.charAt(path.length() - 1) != '/') {
            sb.append('/');
        }
        sb.append(logId + ".log");
        return sb.toString();
    }

    private static String buildFilename(String path, String filename) {
        StringBuilder sb = new StringBuilder(path);
        if (path.charAt(path.length() - 1) != '/') {
            sb.append('/');
        }
        sb.append(filename + ".log");
        return sb.toString();
    }

    public boolean put(String key, String value) throws IOException {
        long pos = writer.length();
        Command cmd = new Command(OP_PUT, key, value);
        byte[] json = JSON.toJSONBytes(cmd);
        //顺序写,4个byte是长度，剩下是内容
        writer.writeInt(json.length);
        writer.write(json);
        log.info("put:{}", new String(json));
        index.put(key, new CommandPos(pos, curLogId));
        return true;
    }

    public boolean remove(String key) throws IOException {
        Command cmd = new Command(OP_RM, key, "");
        byte[] json = JSON.toJSONBytes(cmd);
        //顺序写
        writer.writeInt(json.length);
        writer.write(json);
        index.remove(key);
        return true;
    }

    public Optional<String> get(String key) throws IOException {
        if (!index.containsKey(key)) {
            return Optional.empty();
        }
        CommandPos commandPos = index.get(key);
        RandomAccessFile reader = readerMap.get(curLogId);
        reader.seek(commandPos.pos);
        int len = reader.readInt();
        byte[] buffer = new byte[len];
        reader.read(buffer, 0, len);
        log.info("get:{}", new String(buffer));
        Command cmd = JSON.parseObject(buffer, Command.class);
        return Optional.of(cmd.value);
    }

    public void compact() throws IOException {
        if (writer.length() <= LOG_MX_SIZE) {
            return;
        }
        compact0();
    }

    /**
     * 日志文件压缩
     * 经典的write on copy的做法
     */
    public void compact0() throws IOException {
        log.info("start compact...");
        //TODO:这里需要加锁
        int compactTo = curLogId + 1;
        int compactFrom = curLogId;
        curLogId = compactTo + 1;
        //这里理论上是不需要加锁了？日志压缩的过程
        RandomAccessFile compactWriter = new RandomAccessFile(buildFilename(path, compactTo), "rw");
        long pos = 0;
        byte[] buffer = new byte[BUFFER_MX_SIZE];
        for (Map.Entry<String, CommandPos> entry : index.entrySet()) {
            String key = entry.getKey();
            CommandPos commandPos = entry.getValue();
            if (commandPos.logId == curLogId) {
                //写入到新文件的key不需要压缩
                continue;
            }
            RandomAccessFile reader = readerMap.get(commandPos.logId);
            reader.seek(commandPos.pos);
            int len = reader.readInt();
            reader.read(buffer, 0, len);
            compactWriter.writeInt(len);
            compactWriter.write(buffer, 0, len);
            index.put(key, new CommandPos(pos, curLogId));
            pos += 4 + len;
        }
        //删除旧的日志文件
        readerMap.get(compactLogId).close();
        readerMap.get(compactFrom).close();
        readerMap.remove(compactLogId);
        readerMap.remove(compactFrom);
        compactLogId = compactTo;
        logIndexRW.writeInt(curLogId);
        logIndexRW.writeInt(compactLogId);
    }

    public static class CommandPos {
        long pos;
        //长度直接在文件里面标识
        //int len;
        //对应的日志文件
        int logId;

        public CommandPos(long pos, int logId) {
            this.pos = pos;
            //this.len = len;
            this.logId = logId;
        }
    }

    private static class Command {
        String op;
        String key;
        String value;

        public Command() {
        }

        public Command(String op, String key, String value) {
            this.op = op;
            this.key = key;
            this.value = value;
        }

        public String getOp() {
            return op;
        }

        public void setOp(String op) {
            this.op = op;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
