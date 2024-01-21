package com.liyongquan.weeklycontest.wc381;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class MinimumPushesTest {
    @Test
    public void t1() {
        MinimumPushes mp = new MinimumPushes();
        int res = mp.minimumPushes("xycdefghij");
        System.out.println(res);
        Assert.assertEquals(12, res);
    }
}