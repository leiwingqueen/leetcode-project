package com.liyongquan.weeklycontest.wc236;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class MinSideJumpsTest {
    private MinSideJumps msj = new MinSideJumps();

    @Test
    public void minSideJumps() {
        int res = msj.minSideJumps(new int[]{0, 1, 2, 3, 0});
        Assert.assertEquals(2, res);
    }
}