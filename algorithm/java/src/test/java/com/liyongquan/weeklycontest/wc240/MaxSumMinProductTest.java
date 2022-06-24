package com.liyongquan.weeklycontest.wc240;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class MaxSumMinProductTest {
    private MaxSumMinProduct ms = new MaxSumMinProduct();

    @Test
    public void maxSumMinProduct3() {
        int res = ms.maxSumMinProduct3(new int[]{1, 2, 3, 2});
        log.info("{}", res);
        Assert.assertEquals(14, res);
    }
}