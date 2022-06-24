package com.liyongquan.dp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author liyongquan
 * @date 2021/11/11
 */
@Slf4j
public class KInversePairsTest {
    private KInversePairs kp = new KInversePairs();

    @Test
    public void kInversePairs2() {
        int r = kp.kInversePairs2(3, 1);
        Assert.assertEquals(2, r);
    }
}