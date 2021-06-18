package com.liyongquan.math;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class SmallestGoodBaseTest {
    private SmallestGoodBase sgb = new SmallestGoodBase();

    @Test
    public void smallestGoodBase() {
        String res = sgb.smallestGoodBase2("13");
        log.info("{}", res);
        Assert.assertEquals("3", res);
    }
}