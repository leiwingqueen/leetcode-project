package com.liyongquan.bit;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class CountTripletsTest {
    private CountTriplets ct = new CountTriplets();

    @Test
    public void countTriplets2() {
        int[] arr = {2, 3, 1, 6, 7};
        int res = ct.countTriplets2(arr);
        log.info("{}", res);
        Assert.assertEquals(4, res);
    }
}