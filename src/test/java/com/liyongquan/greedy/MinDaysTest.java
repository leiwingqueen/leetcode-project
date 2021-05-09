package com.liyongquan.greedy;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class MinDaysTest {
    private MinDays md = new MinDays();

    /**
     * [1,10,3,10,2]
     * 3
     * 1
     */
    @Test
    public void minDays() {
        int res = md.minDays(new int[]{1, 10, 3, 10, 2}, 3, 1);
        log.info("{}", res);
        Assert.assertEquals(3, res);
    }
}