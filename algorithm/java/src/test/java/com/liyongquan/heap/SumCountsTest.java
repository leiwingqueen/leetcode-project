package com.liyongquan.heap;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class SumCountsTest {
    private SumCounts sc = new SumCounts();

    // [1,2,1]
    @Test
    public void sumCounts() {
        int res = sc.sumCounts(new int[]{1, 2, 1});
        System.out.println(res);
        Assert.assertEquals(15, res);
    }
}