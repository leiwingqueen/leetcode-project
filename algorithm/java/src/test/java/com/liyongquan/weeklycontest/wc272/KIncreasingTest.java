package com.liyongquan.weeklycontest.wc272;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class KIncreasingTest {
    private KIncreasing kIncreasing = new KIncreasing();

    @Test
    public void kIncreasing() {
        int res = kIncreasing.kIncreasing(new int[]{12, 6, 12, 6, 14, 2, 13, 17, 3, 8, 11, 7, 4, 11, 18, 8, 8, 3}, 1);
        Assert.assertEquals(12, res);
    }

    //[12,6,12,6,14,2,13,17,3,8,11,7,4,11,18,8,8,3]
    //1
    @Test
    public void test2() {
        int res = kIncreasing.kIncreasing(new int[]{12, 6, 12, 6, 14, 2, 13, 17, 3, 8, 11, 7, 4, 11, 18, 8, 8, 3}, 1);
        Assert.assertEquals(12, res);
    }
}