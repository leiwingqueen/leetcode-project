package com.liyongquan.weeklycontest.wc307;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class LargestPalindromicTest {
    private LargestPalindromic largestPalindromic = new LargestPalindromic();

    @Test
    public void largestPalindromic() {
        String res = largestPalindromic.largestPalindromic("00009");
        Assert.assertEquals("9", res);
    }

    @Test
    public void largestPalindromic2() {
        String res = largestPalindromic.largestPalindromic("00001105");
        Assert.assertEquals("1005001", res);
    }
}