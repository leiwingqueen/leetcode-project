package com.liyongquan.dp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class MinCost2Test {
    private MinCost2 minCost2 = new MinCost2();

    @Test
    public void minCostII() {
        int res = minCost2.minCostII(new int[][]{{1, 3}});
        log.info("{}", res);
        Assert.assertEquals(1, res);
    }
}