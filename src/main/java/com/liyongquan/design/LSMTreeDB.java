package com.liyongquan.design;

import javafx.util.Pair;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;
import java.util.TreeMap;

/**
 * LSMTree java实现
 * https://yetanotherdevblog.com/lsm/
 */
public class LSMTreeDB {
    private static final String OP_PUT = "PUT";
    private static final String OP_RM = "RM";

    private TreeMap<String, Command> memTable;

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

    public Collection<Pair<String, String>> scan(String left, String right) {
        return null;
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
