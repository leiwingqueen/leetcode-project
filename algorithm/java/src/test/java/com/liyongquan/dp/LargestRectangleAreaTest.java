package com.liyongquan.dp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class LargestRectangleAreaTest {
    private LargestRectangleArea lra = new LargestRectangleArea();

    @Test
    public void largestRectangleArea4() {
        int res = lra.largestRectangleArea4(new int[]{2, 1, 5, 6, 2, 3});
        log.info("{}", res);
        Assert.assertEquals(10, res);
    }
}