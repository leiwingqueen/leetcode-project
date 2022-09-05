package com.liyongquan.weeklycontest.wc309;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class LongestNiceSubarrayTest {
    private LongestNiceSubarray subarray = new LongestNiceSubarray();

    @Test
    public void longestNiceSubarray() {
        int res = subarray.longestNiceSubarray(new int[]{744437702, 379056602, 145555074, 392756761, 560864007, 934981918, 113312475, 1090, 16384, 33, 217313281, 117883195, 978927664});
        Assert.assertEquals(3, res);
    }
}