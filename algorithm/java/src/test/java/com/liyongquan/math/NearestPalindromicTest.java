package com.liyongquan.math;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class NearestPalindromicTest {
    private NearestPalindromic np = new NearestPalindromic();

    @Test
    public void nearestPalindromic() {
        String res = np.nearestPalindromic("1213");
        Assert.assertEquals("1221", res);
    }

    @Test
    public void testNearestPalindromic() {
        String res = np.nearestPalindromic("45654");
        Assert.assertEquals("45554", res);
    }
}