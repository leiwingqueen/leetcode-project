package com.liyongquan.dp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class MinimumTimeRequiredTest {
    private MinimumTimeRequired mtr = new MinimumTimeRequired();

    /**
     * [3,2,3]
     * 3
     */
    @Test
    public void minimumTimeRequired() {
        int res = mtr.minimumTimeRequired2(new int[]{3, 2, 3}, 3);
        log.info("{}", res);
        Assert.assertEquals(3, res);
    }
}