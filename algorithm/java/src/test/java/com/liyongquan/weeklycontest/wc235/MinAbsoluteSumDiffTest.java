package com.liyongquan.weeklycontest.wc235;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class MinAbsoluteSumDiffTest {
    private MinAbsoluteSumDiff sumDiff = new MinAbsoluteSumDiff();

    /**
     * [5,4,7]
     * [10,8,10]
     */
    @Test
    public void minAbsoluteSumDiff() {
        int res = sumDiff.minAbsoluteSumDiff(new int[]{5, 4, 7}, new int[]{10, 8, 10});
        log.info("{}", res);
        Assert.assertEquals(9, res);
    }
}