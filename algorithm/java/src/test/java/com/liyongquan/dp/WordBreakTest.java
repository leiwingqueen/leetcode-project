package com.liyongquan.dp;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class WordBreakTest {

    @Test
    public void wordBreak() {
        WordBreak wb = new WordBreak();
        boolean b = wb.wordBreak("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat"));
        Assert.assertEquals(false, b);
    }
}