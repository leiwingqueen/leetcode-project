package com.liyongquan.weeklycontest.bwc78;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

@Slf4j
public class LargestVarianceTest {
    private LargestVariance lv = new LargestVariance();

    @Test
    public void largestVariance2() {
        int res = lv.largestVariance2("aababbb");
        Assert.assertEquals(3, res);
    }
}