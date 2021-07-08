package com.liyongquan.slidewindow;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class NumSubarraysWithSumTest {
    private NumSubarraysWithSum ns = new NumSubarraysWithSum();

    @Test
    public void numSubarraysWithSum() {
        int res = ns.numSubarraysWithSum3(new int[]{1, 0, 1, 0, 1}, 2);
        log.info("{}", res);
        Assert.assertEquals(4, res);
    }
}