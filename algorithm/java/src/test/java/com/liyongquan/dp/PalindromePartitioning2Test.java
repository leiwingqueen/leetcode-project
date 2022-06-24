package com.liyongquan.dp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class PalindromePartitioning2Test {
    private PalindromePartitioning2 pp = new PalindromePartitioning2();

    @Test
    public void minCut() {
        int res = pp.minCut("aab");
        log.info("{}", res);
        Assert.assertEquals(1, res);
    }
}