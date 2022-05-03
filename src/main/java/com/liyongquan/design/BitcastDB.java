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
    public static final String OP_PUT = "PUT";
    public static final String OP_RM = "RM";

    private RandomAccessFile reader;
    private RandomAccessFile writer;

    public static final int MX_LEN = 1024;

    private Map<String, CommandPos> index;
    private String path;

    public BitcastDB(String path) throws IOException {
        index = new HashMap<>();
        this.path = path;
        load();
    }

    private void load() throws IOException {
        index.clear();
        String filename = path + "/0.log";
        writer = new RandomAccessFile(filename, "rw");
        writer.seek(writer.length());
        reader = new RandomAccessFile(filename, "r");
        //扫描一遍日志进行数据加载
        int b;
        byte[] buffer = new byte[MX_LEN];
        int pos = 0;
        while ((b = reader.read()) != -1) {
            int b2 = reader.read();
            int b3 = reader.read();
            int b4 = reader.read();
            if ((b | b2 | b3 | b4) < 0)
                throw new EOFException();
            int len = ((b << 24) + (b2 << 16) + (b3 << 8) + (b4 << 0));
            reader.read(buffer, 0, len);
            Command cmd = JSON.parseObject(buffer, 0, len, Charset.defaultCharset(), Command.class);
            if (OP_PUT.equals(cmd.op)) {
                index.put(cmd.key, new CommandPos(pos));
            }
            pos += 4 + len;
        }
    }

    public boolean put(String key, String value) throws IOException {
        long pos = writer.length();
        Command cmd = new Command(OP_PUT, key, value);
        byte[] json = JSON.toJSONBytes(cmd);
        //顺序写,4个byte是长度，剩下是内容
        writer.writeInt(json.length);
        writer.write(json);
        log.info("put:{}", new String(json));
        index.put(key, new CommandPos(pos));
        return true;
    }

    public boolean remove(String key) throws IOException {
        Command cmd = new Command(OP_RM, key, "");
        byte[] json = JSON.toJSONBytes(cmd);
        //顺序写
        writer.write(json);
        index.remove(key);
        return true;
    }

    public Optional<String> get(String key) throws IOException {
        if (!index.containsKey(key)) {
            return Optional.empty();
        }
        CommandPos commandPos = index.get(key);
        reader.seek(commandPos.pos);
        int len = reader.readInt();
        byte[] buffer = new byte[len];
        reader.read(buffer, 0, len);
        log.info("get:{}", new String(buffer));
        Command cmd = JSON.parseObject(buffer, Command.class);
        return Optional.of(cmd.value);
    }

    public static class CommandPos {
        long pos;
        //长度直接在文件里面标识
        //int len;

        public CommandPos(long pos) {
            this.pos = pos;
            //this.len = len;
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
