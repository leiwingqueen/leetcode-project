package com.liyongquan.math;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class CountPrimesTest {
    private CountPrimes cp = new CountPrimes();

    @Test
    public void countPrimes() {
        int i = cp.countPrimes(10);
        System.out.println(i);
        Assert.assertEquals(4, i);
    }

    @Test
    public void countPrimes2() {
        int i = cp.countPrimes2(10);
        Assert.assertEquals(4, i);
    }
}