package com.liyongquan.heap;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class CountSubarraysTest {

    @Test
    public void countSubarrays() {
        CountSubarrays cs = new CountSubarrays();
        long res = cs.countSubarrays(new int[]{1, 2, 3}, 0);
        Assert.assertEquals(3, res);
    }
}