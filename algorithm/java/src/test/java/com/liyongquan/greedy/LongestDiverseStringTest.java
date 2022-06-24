package com.liyongquan.greedy;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class LongestDiverseStringTest {
    private LongestDiverseString lds = new LongestDiverseString();

    @Test
    public void longestDiverseString() {
        String res = lds.longestDiverseString(1, 1, 7);
        Assert.assertEquals("ccaccbcc", res);
    }
}