package com.liyongquan.dp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class FindTargetSumWaysTest {
    private FindTargetSumWays ft = new FindTargetSumWays();

    /**
     * [1,0]
     * 1
     */
    @Test
    public void findTargetSumWays() {
        int res = ft.findTargetSumWays(new int[]{1, 0}, 1);
        log.info("{}", res);
        Assert.assertEquals(2, res);
    }

    /**
     * [0,0,0,0,0,0,0,0,1]
     * 1
     */
    @Test
    public void test2() {
        int res = ft.findTargetSumWays(new int[]{0, 0, 0, 0, 0, 0, 0, 0, 1}, 1);
        log.info("{}", res);
        Assert.assertEquals(256, res);
    }
}