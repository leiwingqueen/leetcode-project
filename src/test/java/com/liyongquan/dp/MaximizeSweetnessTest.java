package com.liyongquan.dp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

@Slf4j
public class MaximizeSweetnessTest {
    private MaximizeSweetness ms = new MaximizeSweetness();

    @Test
    public void maximizeSweetness() {
        int[] arr = {1, 2, 2, 1, 2, 2, 1, 2, 2};
        int res = ms.maximizeSweetness(arr, 2);
        Assert.assertEquals(5, res);
    }

    @Test
    public void test2() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int res = ms.maximizeSweetness(arr, 5);
        Assert.assertEquals(6, res);
    }

    @Test
    public void test3() {
        int[] arr = {5, 6, 7, 8, 9, 1, 2, 3, 4};
        int res = ms.maximizeSweetness(arr, 8);
        Assert.assertEquals(1, res);
    }
}