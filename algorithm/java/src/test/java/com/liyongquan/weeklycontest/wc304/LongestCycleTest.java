package com.liyongquan.weeklycontest.wc304;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class LongestCycleTest {
    private LongestCycle cycle = new LongestCycle();

    @Test
    public void longestCycle() {
        int res = cycle.longestCycle(new int[]{3, 3, 4, 2, 3});
        Assert.assertEquals(3, res);
    }
}