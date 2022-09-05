package com.liyongquan.weeklycontest.wc309;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class MostBookedTest {
    private MostBooked mostBooked = new MostBooked();

    @Test
    public void mostBooked() {
        int res = mostBooked.mostBooked(2, new int[][]{{0, 10}, {1, 5}, {2, 7}, {3, 4}});
        Assert.assertEquals(0, res);
    }

    @Test
    public void test2() {
        int res = mostBooked.mostBooked(3, new int[][]{
                {1, 20}, {2, 10}, {3, 5}, {4, 9}, {6, 8}});
        Assert.assertEquals(1, res);
    }
}