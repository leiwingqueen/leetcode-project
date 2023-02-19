package com.liyongquan.math;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class MaxAverageRatioTest {
    private MaxAverageRatio ratio = new MaxAverageRatio();

    // [[1,2],[3,5],[2,2]]
    //2
    @Test
    public void maxAverageRatio() {
        int[][] params = {
                {1, 2}, {3, 5}, {2, 2}
        };
        double res = ratio.maxAverageRatio(params, 2);
        Assert.assertEquals(0.78333, res, 0.0001);
    }
}