package com.liyongquan.slidewindow;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class NumSubarrayBoundedMaxTest {
    private NumSubarrayBoundedMax ns = new NumSubarrayBoundedMax();

    @Test
    public void numSubarrayBoundedMax() {
        int res = ns.numSubarrayBoundedMax(new int[]{2, 1, 4, 3}, 2, 3);
        log.info("{}", res);
        Assert.assertEquals(3, res);
    }
}