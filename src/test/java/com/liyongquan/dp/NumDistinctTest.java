package com.liyongquan.dp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class NumDistinctTest {
    private NumDistinct nd = new NumDistinct();

    /**
     * s = "rabbbit", t = "rabbit"
     */
    @Test
    public void numDistinct2() {
        int res = nd.numDistinct5("rabbbit", "rabbit");
        log.info("{}", res);
        Assert.assertEquals(3, res);
    }
}