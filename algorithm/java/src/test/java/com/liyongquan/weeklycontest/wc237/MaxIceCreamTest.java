package com.liyongquan.weeklycontest.wc237;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class MaxIceCreamTest {
    private MaxIceCream mic = new MaxIceCream();

    @Test
    public void maxIceCream() {
        int res = mic.maxIceCream(new int[]{1, 3, 2, 4, 1}, 7);
        log.info("{}", res);
        Assert.assertEquals(4, res);
    }
}