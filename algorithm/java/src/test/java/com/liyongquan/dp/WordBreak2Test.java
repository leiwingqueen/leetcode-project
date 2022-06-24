package com.liyongquan.dp;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class WordBreak2Test {
    private WordBreak2 wb = new WordBreak2();

    @Test
    public void wordBreak() {
        /**
         * s = "catsanddog"
         * wordDict = ["cat", "cats", "and", "sand", "dog"]
         */
        List<String> r = wb.wordBreak2("catsanddog", Arrays.asList("cat", "cats", "and", "sand", "dog"));
        for (String s : r) {
            System.out.println(s);
        }
    }
}