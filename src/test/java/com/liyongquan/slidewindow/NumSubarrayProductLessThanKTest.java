package com.liyongquan.slidewindow;

import org.junit.Test;

import static org.junit.Assert.*;

public class NumSubarrayProductLessThanKTest {
    private NumSubarrayProductLessThanK ns = new NumSubarrayProductLessThanK();

    @Test
    public void numSubarrayProductLessThanK() {
        int i = ns.numSubarrayProductLessThanK(new int[]{10, 5, 2, 6}, 100);
        System.out.println(i);
    }
}