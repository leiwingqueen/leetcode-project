package com.liyongquan.dp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class NumWaysTest {
    private NumWays nw = new NumWays();

    @Test
    public void numWays() {
        int res = nw.numWays(3, 2);
        log.info("{}", res);
        Assert.assertEquals(6, res);
    }

    @Test
    public void test2() {
        int res = nw.numWays(7, 2);
        log.info("{}", res);
        Assert.assertEquals(42, res);
    }
}