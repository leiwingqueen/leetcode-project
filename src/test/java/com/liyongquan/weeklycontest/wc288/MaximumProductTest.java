package com.liyongquan.weeklycontest.wc288;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class MaximumProductTest {
    private MaximumProduct mp = new MaximumProduct();

    //[24,5,64,53,26,38]
    //54
    @Test
    public void maximumProduct() {
        int res = mp.maximumProduct(new int[]{24, 5, 64, 53, 26, 38}, 54);
        Assert.assertEquals(180820950, res);
    }
}