package com.liyongquan.dp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class StockIVTest {
    private StockIV stockIV = new StockIV();

    /**
     * 2
     * [3,2,6,5,0,3]
     */
    @Test
    public void maxProfit() {
        int res = stockIV.maxProfit(2, new int[]{3, 2, 6, 5, 0, 3});
        log.info("{}", res);
        Assert.assertEquals(7, res);
    }

    /**
     * 2
     * [2,4,1]
     */
    @Test
    public void test2() {
        int res = stockIV.maxProfit(2, new int[]{2, 4, 1});
        log.info("{}", res);
        Assert.assertEquals(2, res);
    }
}