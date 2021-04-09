package com.liyongquan.weeklycontest.wc235;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class MinAbsoluteSumDiffTest {
    private MinAbsoluteSumDiff sumDiff = new MinAbsoluteSumDiff();

    @Test
    public void minAbsoluteSumDiff() {
        int res = sumDiff.minAbsoluteSumDiff(new int[]{1, 7, 5}, new int[]{2, 3, 5});
        log.info("{}", res);
        Assert.assertEquals(3, res);
    }
}