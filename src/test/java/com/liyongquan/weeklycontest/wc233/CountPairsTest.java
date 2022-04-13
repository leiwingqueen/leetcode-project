package com.liyongquan.weeklycontest.wc233;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class CountPairsTest {
    private CountPairs cp = new CountPairs();

    /**
     * [1,4,2,7]
     * 2
     * 6
     */
    @Test
    public void countPairs2() {
        int res = cp.countPairs2(new int[]{1, 4, 2, 7}, 2, 6);
        Assert.assertEquals(6, res);
    }
}