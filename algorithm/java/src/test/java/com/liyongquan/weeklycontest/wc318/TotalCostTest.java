package com.liyongquan.weeklycontest.wc318;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class TotalCostTest {
    private TotalCost totalCost = new TotalCost();

    /**
     * [1,2,4,1]
     * 3
     * 3
     */
    @Test
    public void totalCost() {
        long res = totalCost.totalCost(new int[]{1, 2, 4, 1}, 3, 3);
        Assert.assertEquals(4, res);
    }

    //[31,25,72,79,74,65,84,91,18,59,27,9,81,33,17,58]
    //11
    //2
    @Test
    public void test2() {
        long res = totalCost.totalCost(new int[]{31,25,72,79,74,65,84,91,18,59,27,9,81,33,17,58}, 11, 2);
        Assert.assertEquals(423, res);
    }

    // [57,33,26,76,14,67,24,90,72,37,30]
    //11
    //2
    @Test
    public void test3() {
        long res = totalCost.totalCost(new int[]{57,33,26,76,14,67,24,90,72,37,30}, 11, 2);
        Assert.assertEquals(423, res);
    }


}