package com.liyongquan.weeklycontest.wc299;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class MaximumsSplicedArrayTest {
    private MaximumsSplicedArray array = new MaximumsSplicedArray();

    @Test
    public void maximumsSplicedArray() {
        int res = array.maximumsSplicedArray(new int[]{60, 60, 60}, new int[]{10, 90, 10});
        Assert.assertEquals(210, res);
    }

    /**
     * [10,20,50,15,30,10]
     * [40,20,10,100,10,10]
     */
    @Test
    public void test2() {
        int res = array.maximumsSplicedArray(new int[]{10, 20, 50, 15, 30, 10}, new int[]{40, 20, 10, 100, 10, 10});
        Assert.assertEquals(230, res);
    }
}