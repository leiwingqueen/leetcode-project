package com.liyongquan.dp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class CheckSubarraySumTest {
    private CheckSubarraySum cs = new CheckSubarraySum();

    /**
     * [0,0]
     * 0
     */
    @Test
    public void checkSubarraySum2() {
        boolean res = cs.checkSubarraySum2(new int[]{0, 0}, 0);
        log.info("{}", res);
        Assert.assertEquals(true, res);
    }
}