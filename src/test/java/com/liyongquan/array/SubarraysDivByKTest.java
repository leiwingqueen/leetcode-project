package com.liyongquan.array;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class SubarraysDivByKTest {
    private SubarraysDivByK sd = new SubarraysDivByK();

    /**
     * [4,5,0,-2,-3,1]
     * 5
     */
    @Test
    public void subarraysDivByK() {
        int res = sd.subarraysDivByK(new int[]{4, 5, 0, -2, -3, 1}, 5);
        log.info("{}", res);
        Assert.assertEquals(7, res);
    }
}