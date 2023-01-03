package com.liyongquan.heap;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class NumberOfBacklogOrdersTest {
    private NumberOfBacklogOrders backlogOrders = new NumberOfBacklogOrders();

    @Test
    public void getNumberOfBacklogOrders() {
        // [[7,1000000000,1],[15,3,0],[5,999999995,0],[5,1,1]]
        int[][] orders = {
                {7, 1000000000, 1},
                {15, 3, 0},
                {5, 999999995, 0},
                {5, 1, 1}
        };
        int res = backlogOrders.getNumberOfBacklogOrders(orders);
        Assert.assertEquals(999999984, res);
    }
}