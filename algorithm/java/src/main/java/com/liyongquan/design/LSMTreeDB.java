package com.liyongquan.design;

import com.alibaba.fastjson.JSON;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Collection;
import java.util.Optional;
import java.util.TreeMap;

/**
 * LSMTree java实现
 * https://yetanotherdevblog.com/lsm/
 */
public class LSMTreeDB {

    private static class Pair {
        String key;
        String value;
        Pair(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }
    private static final String OP_PUT = "PUT";
    private static final String OP_RM = "RM";

    private TreeMap<String, Command> memTable;
    private SSTable ssTable;

    public void put(String key, String value) throws IOException {
        memTable.put(key, new Command(OP_PUT, key, value));
    }

    public void remove(String key) throws IOException {
        memTable.put(key, new Command(OP_RM, key, ""));
    }

    public Optional<String> get(String key) throws IOException {
        if (!memTable.containsKey(key)) {
            return Optional.empty();
        }
        return Optional.empty();
    }

    public Collection<Pair> scan(String left, String right) {
        return null;
    }

    private static class SSTable {
        public static final int BUFFER_SIZE = 1024;
        private int segmentId;
        private int partSize;
        private String path;

        public SSTable(int segmentId, int partSize, String path) {
            this.segmentId = segmentId;
            this.partSize = partSize;
            this.path = path;
        }

        /**
         * 持久化memTable到Segment
         *
         * @param memTable
         */
        public void persistent(TreeMap<String, Command> memTable) throws IOException {
            ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
            segmentId++;
            String filename = buildFilename(path, String.valueOf(segmentId));
            FileChannel fileChannel = new FileOutputStream(filename).getChannel();
            for (Command command : memTable.values()) {
                byte[] json = JSON.toJSONBytes(command);
                buffer.putInt(json.length);
                buffer.put(json);
                if (buffer.position() >= partSize) {
                    //写入一个索引
                    fileChannel.write(buffer);
                }
                buffer.clear();
            }
            fileChannel.close();
        }
    }


    private static String buildFilename(String path, String filename) {
        StringBuilder sb = new StringBuilder(path);
        if (path.charAt(path.length() - 1) != '/') {
            sb.append('/');
        }
        sb.append(filename + ".log");
        return sb.toString();
    }

    private static class SparseIndex {
        TreeMap<String, SparseIndexItem> map;
    }

    private static class SparseIndexItem {
        long offset;
        int len;
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
