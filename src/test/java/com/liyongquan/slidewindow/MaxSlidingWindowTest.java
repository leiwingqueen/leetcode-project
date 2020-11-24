package com.liyongquan.slidewindow;

import org.junit.Test;

import static org.junit.Assert.*;

public class MaxSlidingWindowTest {
    private MaxSlidingWindow sw = new MaxSlidingWindow();

    @Test
    public void maxSlidingWindow2() {
        int[] r = sw.maxSlidingWindow2(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3);
        for (int i : r) {
            System.out.println(i);
        }
    }
}