package com.liyongquan.weeklycontest.wc280;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class MaximumANDSumTest {
    private MaximumANDSum sum = new MaximumANDSum();

    @Test
    public void maximumANDSum3() {
        int res = sum.maximumANDSum3(new int[]{1, 2, 3, 4, 5, 6}, 3);
        Assert.assertEquals(9, res);
    }
}