package com.liyongquan.design;

import javafx.util.Pair;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

/**
 * LSMTree java实现
 * https://yetanotherdevblog.com/lsm/
 */
public class LSMTreeDB {
    public boolean put(String key, String value) throws IOException {
        return false;
    }

    public boolean remove(String key) throws IOException {
        return false;
    }

    public Optional<String> get(String key) throws IOException {
        return Optional.empty();
    }

    public Collection<Pair<String, String>> scan(String left, String right) {
        return null;
    }
}
