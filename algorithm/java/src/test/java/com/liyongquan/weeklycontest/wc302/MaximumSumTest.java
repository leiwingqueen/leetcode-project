package com.liyongquan.weeklycontest.wc302;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class MaximumSumTest {
    private MaximumSum maximumSum = new MaximumSum();

    @Test
    public void maximumSum() {
        int res = maximumSum.maximumSum(new int[]{18, 43, 36, 13, 7});
        Assert.assertEquals(54, res);
    }

    @Test
    public void t2() {
        int cal = maximumSum.cal(18);
        int c2 = maximumSum.cal(36);
        Assert.assertEquals(c2, cal);
    }
}