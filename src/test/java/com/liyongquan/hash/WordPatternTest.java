package com.liyongquan.hash;

import org.junit.Test;

import static org.junit.Assert.*;

public class WordPatternTest {
    private WordPattern wp = new WordPattern();

    /**
     * "abba"
     * "dog cat cat fish"
     */
    @Test
    public void wordPattern() {
        boolean b = wp.wordPattern("abba", "dog cat cat fish");
        System.out.println(b);
    }
}