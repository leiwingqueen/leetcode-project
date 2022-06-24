package com.liyongquan.slidewindow;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class MaxSatisfiedTest {
    private MaxSatisfied ms = new MaxSatisfied();

    @Test
    public void maxSatisfied() {
        int[] customers = {1, 0, 1, 2, 1, 1, 7, 5};
        int[] grumpy =    {0, 1, 0, 1, 0, 1, 0, 1};
        int i = ms.maxSatisfied(customers, grumpy, 3);
        System.out.println(i);
        Assert.assertEquals(16, i);
    }
}