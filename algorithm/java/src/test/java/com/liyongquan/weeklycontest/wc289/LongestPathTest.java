package com.liyongquan.weeklycontest.wc289;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class LongestPathTest {

    /**
     * [-1,0,0,1,1,2]
     * "abacbe"
     */
    @Test
    public void longestPath() {
        LongestPath lp = new LongestPath();
        int res = lp.longestPath(new int[]{-1, 0, 0, 1, 1, 2}, "abacbe");
        Assert.assertEquals(3, res);
    }
}