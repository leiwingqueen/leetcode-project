package com.liyongquan.slidewindow;

import org.junit.Assert;
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

    @Test
    public void maxSlidingWindow3() {
        int[] r = sw.maxSlidingWindow3(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3);
        for (int i : r) {
            System.out.println(i);
        }
        Assert.assertArrayEquals(new int[]{3, 3, 5, 5, 6, 7}, r);
    }

    @Test
    public void test3() {
        int[] r = sw.maxSlidingWindow3(new int[]{1, 3, 1, 2, 0, 5}, 3);
        for (int i : r) {
            System.out.println(i);
        }
    }
}