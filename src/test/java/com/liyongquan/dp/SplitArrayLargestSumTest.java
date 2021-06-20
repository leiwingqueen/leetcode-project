package com.liyongquan.dp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class SplitArrayLargestSumTest {
    private SplitArrayLargestSum ls = new SplitArrayLargestSum();

    /**
     * [7,2,5,10,8]
     * 2
     */
    @Test
    public void splitArray() {
        int res = ls.splitArray(new int[]{7, 2, 5, 10, 8}, 2);
        log.info("{}", res);
        Assert.assertEquals(18, res);
    }
}