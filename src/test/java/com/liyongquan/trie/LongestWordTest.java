package com.liyongquan.trie;

import org.junit.Test;

import static org.junit.Assert.*;

public class LongestWordTest {
    private LongestWord lw = new LongestWord();

    @Test
    public void longestWord() {
        String[] worlds = new String[]{"w", "wo", "wor", "worl", "world"};
        String s = lw.longestWord2(worlds);
        System.out.println(s);
    }
}