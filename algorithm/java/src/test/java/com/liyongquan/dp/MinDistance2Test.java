package com.liyongquan.dp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class MinDistance2Test {
    private MinDistance2 md = new MinDistance2();

    /**
     * "horse"
     * "ros"
     */
    @Test
    public void minDistance() {
        int res = md.minDistance2("horse", "ros");
        log.info("{}", res);
        Assert.assertEquals(3, res);
    }

    /**
     * "intention"
     * "execution"
     */
    @Test
    public void test2() {
        int res = md.minDistance2("intention", "execution");
        log.info("{}", res);
        Assert.assertEquals(5, res);
    }

    /**
     * "park"
     * "spake"
     */
    @Test
    public void test3() {
        int res = md.minDistance2("park", "spake");
        log.info("{}", res);
        Assert.assertEquals(3, res);
    }
}