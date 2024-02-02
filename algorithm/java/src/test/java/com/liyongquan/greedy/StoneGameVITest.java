package com.liyongquan.greedy;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class StoneGameVITest {
    private StoneGameVI stoneGameVI = new StoneGameVI();

    @Test
    public void stoneGameVI() {
        int res = stoneGameVI.stoneGameVI(new int[]{1, 2}, new int[]{3, 1});
        Assert.assertEquals(0, res);
    }
}