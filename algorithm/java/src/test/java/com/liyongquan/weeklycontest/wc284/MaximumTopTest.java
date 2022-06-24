package com.liyongquan.weeklycontest.wc284;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class MaximumTopTest {
    private MaximumTop mt = new MaximumTop();

    @Test
    public void maximumTop() {
        int res = mt.maximumTop(new int[]{31, 15, 92, 84, 19, 92, 55},
                4);
        Assert.assertEquals(92, res);
    }

    @Test
    public void test1() {
        int res = mt.maximumTop(new int[]{35, 43, 23, 86, 23, 45, 84, 2, 18, 83, 79, 28, 54, 81, 12, 94, 14, 0, 0, 29, 94, 12, 13, 1, 48, 85, 22, 95, 24, 5, 73, 10, 96, 97, 72, 41, 52, 1, 91, 3, 20, 22, 41, 98, 70, 20, 52, 48, 91, 84, 16, 30, 27, 35, 69, 33, 67, 18, 4, 53, 86, 78, 26, 83, 13, 96, 29, 15, 34, 80, 16, 49},
                15);
        Assert.assertEquals(94, res);
    }
}