package com.liyongquan.design;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.Map;
import java.util.Optional;

/**
 * 参考bitcast的DB实现
 */
public class BitcastDB {
    public static final String OP_PUT = "PUT";
    public static final String OP_RM = "RM";

    private BufferedReader reader;
    private BufferedWriter writer;

    private Map<String, CommandPos> index;
    private String path;

    public BitcastDB(String path) {
    }

    private void load() {

    }

    public boolean put(String key, String value) {
        //TODO
        return false;
    }

    public void remove(String key) {

    }

    public Optional<String> get(String key) {
        return null;
    }

    public static class CommandPos {
        int pos;
        int len;
    }

    private static class Command {
        String op;
        String key;
        String value;
    }
}
