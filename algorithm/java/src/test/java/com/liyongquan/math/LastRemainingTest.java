package com.liyongquan.math;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author liyongquan
 * @date 2021/10/12
 */
@Slf4j
public class LastRemainingTest {
    private LastRemaining remaining = new LastRemaining();

    @Test
    public void lastRemaining() {
        int res = remaining.lastRemaining(5, 3);
        Assert.assertEquals(3, res);
    }
}