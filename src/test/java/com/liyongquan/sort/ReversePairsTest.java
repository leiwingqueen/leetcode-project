package com.liyongquan.sort;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class ReversePairsTest {
    private ReversePairs rp = new ReversePairs();

    @Test
    public void reversePairs() {
        int res = rp.reversePairs(new int[]{1, 3, 2, 3, 1});
        log.info("{}", res);
        Assert.assertEquals(4, res);
    }
}