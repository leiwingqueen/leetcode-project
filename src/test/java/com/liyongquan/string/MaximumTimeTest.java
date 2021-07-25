package com.liyongquan.string;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author liyongquan
 * @date 2021/7/25
 */
@Slf4j
public class MaximumTimeTest {
    private MaximumTime mt = new MaximumTime();

    @Test
    public void maximumTime() {
        String res = mt.maximumTime("2?:?0");
        log.info("{}", res);
        Assert.assertEquals("23:50", res);
    }

    @Test
    public void match() {
        boolean res = mt.match("2?:?0", "23:50");
        Assert.assertTrue(res);
    }
}