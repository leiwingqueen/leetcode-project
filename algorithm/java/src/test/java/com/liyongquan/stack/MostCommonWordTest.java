package com.liyongquan.stack;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class MostCommonWordTest {
    private MostCommonWord mcw = new MostCommonWord();

    //"Bob hit a ball, the hit BALL flew far after it was hit."
    //["hit"]
    @Test
    public void mostCommonWord() {
        String res = mcw.mostCommonWord("Bob hit a ball, the hit BALL flew far after it was hit.",
                new String[]{"hit"});
        Assert.assertEquals("ball", res);
    }

    @Test
    public void test2() {
        String res = mcw.mostCommonWord("a.",
                new String[]{});
        Assert.assertEquals("a", res);
    }
}