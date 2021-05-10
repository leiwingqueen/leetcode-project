package com.liyongquan.weeklycontest.wc240;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class MaxDistanceTest {
    private MaxDistance md = new MaxDistance();

    /**
     * [55,30,5,4,2]
     * [100,20,10,10,5]
     */
    @Test
    public void maxDistance() {
        int res = md.maxDistance(new int[]{55, 30, 5, 4, 2}, new int[]{100, 20, 10, 10, 5});
        log.info("{}", res);
        Assert.assertEquals(2, res);
    }
}