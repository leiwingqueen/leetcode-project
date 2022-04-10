package com.liyongquan.weeklycontest.wc288;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class MaximumBeautyTest {
    private MaximumBeauty mb = new MaximumBeauty();

    //[1,3,1,1]
    //7
    //6
    //12
    //1
    @Test
    public void maximumBeauty() {
        long res = mb.maximumBeauty(new int[]{1, 3, 1, 1}, 7, 6, 12, 1);
        Assert.assertEquals(14, res);
    }
}