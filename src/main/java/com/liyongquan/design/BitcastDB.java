package com.liyongquan.design;

import com.alibaba.fastjson.JSON;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Map;
import java.util.Optional;

/**
 * 参考bitcast的DB实现
 * <p>
 * 顺序写，随机读
 */
public class BitcastDB {
    public static final String OP_PUT = "PUT";
    public static final String OP_RM = "RM";

    private RandomAccessFile reader;
    private RandomAccessFile writer;

    private Map<String, CommandPos> index;
    private String path;

    public BitcastDB(String path) {
        this.path = path;
        String filename = path + "/0.log";
        try {
            reader = new RandomAccessFile(filename, "r");
            writer = new RandomAccessFile(filename, "w");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean put(String key, String value) throws IOException {
        long pos = writer.length();
        Command cmd = new Command(OP_PUT, key, value);
        byte[] json = JSON.toJSONBytes(cmd);
        //顺序写
        writer.write(json);
        index.put(key, new CommandPos(pos, json.length));
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
        byte[] buffer = new byte[commandPos.len];
        reader.read(buffer, 0, commandPos.len);
        Command cmd = JSON.parseObject(buffer, Command.class);
        return Optional.of(cmd.value);
    }

    public static class CommandPos {
        long pos;
        int len;

        public CommandPos(long pos, int len) {
            this.pos = pos;
            this.len = len;
        }
    }

    private static class Command {
        String op;
        String key;
        String value;

        public Command(String op, String key, String value) {
            this.op = op;
            this.key = key;
            this.value = value;
        }
    }
}
