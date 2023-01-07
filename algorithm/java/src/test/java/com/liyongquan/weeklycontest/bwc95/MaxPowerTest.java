package com.liyongquan.weeklycontest.bwc95;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class MaxPowerTest {
    private MaxPower maxPower = new MaxPower();

    // [1,2,4,5,0]
    //1
    //2
    @Test
    public void maxPower() {
        long res = maxPower.maxPower(new int[]{1, 2, 4, 5, 0}, 1, 2);
        Assert.assertEquals(5, res);
    }
}