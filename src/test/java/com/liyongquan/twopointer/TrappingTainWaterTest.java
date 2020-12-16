package com.liyongquan.twopointer;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class TrappingTainWaterTest {
    private TrappingTainWater tt = new TrappingTainWater();

    @Test
    public void trap2() {
        int[] array = {4, 2, 0, 3, 2, 5};
        int i = tt.trap2(array);
        System.out.println(i);
        Assert.assertEquals(9, i);
    }
}