package com.liyongquan.slidewindow;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class TwoSumLessThanKTest {
    private TwoSumLessThanK ts = new TwoSumLessThanK();

    @Test
    public void twoSumLessThanK() {
        int i = ts.twoSumLessThanK(new int[]{34, 23, 1, 24, 75, 33, 54, 8}, 60);
        System.out.println(i);
        Assert.assertEquals(58, i);
    }
}