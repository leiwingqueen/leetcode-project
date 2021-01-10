package com.liyongquan.dp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

@Slf4j
public class Stock5Test {
    private Stock5 stock = new Stock5();

    @Test
    public void test() {
        int res = stock.maxProfit(new int[]{3, 3, 5, 0, 0, 3, 1, 4});
        log.info("res:{}", res);
        Assert.assertEquals(6, res);
    }
}