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
        int res = md.minDistance("horse", "ros");
        log.info("{}", res);
        Assert.assertEquals(3, res);
    }
}