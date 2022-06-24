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
        int res = ms.maximizeSweetness2(arr, 2);
        Assert.assertEquals(5, res);
    }

    @Test
    public void test2() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int res = ms.maximizeSweetness2(arr, 5);
        Assert.assertEquals(6, res);
    }

    @Test
    public void test3() {
        int[] arr = {5, 6, 7, 8, 9, 1, 2, 3, 4};
        int res = ms.maximizeSweetness2(arr, 8);
        Assert.assertEquals(1, res);
    }

    //[52832,63820,96186,1623,88717]
    //3
    @Test
    public void test4() {
        int[] arr = {52832, 63820, 96186, 1623, 88717};
        int res = ms.maximizeSweetness2(arr, 3);
        Assert.assertEquals(52832, res);
    }
}