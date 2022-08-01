package com.liyongquan.weeklycontest.bwc82;

import org.junit.Assert;
import org.junit.Test;

public class MinSumSquareDiffTest {
    private MinSumSquareDiff diff = new MinSumSquareDiff();

    @Test
    public void minSumSquareDiff() {
        long res = diff.minSumSquareDiff(new int[]{1, 4, 10, 12}, new int[]{5, 8, 6, 9}, 1, 1);
        Assert.assertEquals(43, res);
    }
}