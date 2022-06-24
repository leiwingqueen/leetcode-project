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

    /**
     * [1,2,4,7,8]
     * 2
     */
    @Test
    public void minimumTimeRequired4() {
        mtr = new MinimumTimeRequired();
        int res = mtr.minimumTimeRequired4(new int[]{1, 2, 4, 7, 8}, 2);
        log.info("{}", res);
    }
}