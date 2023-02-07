package com.liyongquan.weeklycontest.wc331;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class minCapabilityTest {
    private minCapability capability = new minCapability();

    // [7,3,9,5]
    //2
    @Test
    public void minCapability() {
        int res = capability.minCapability(new int[]{7, 3, 9, 5}, 2);
        Assert.assertEquals(5, res);
    }

    @Test
    public void minCapability2() {
        int res = capability.minCapability(new int[]{2, 3, 5, 9}, 2);
        Assert.assertEquals(5, res);
    }
}