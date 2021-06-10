package com.liyongquan.dp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class CoinChange2Test {
    private CoinChange2 cc = new CoinChange2();

    @Test
    public void change() {
        int res = cc.change(5, new int[]{1, 2, 5});
        log.info("{}", res);
        Assert.assertEquals(4, res);
    }
}