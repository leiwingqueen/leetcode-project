package com.liyongquan.dp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class NumSubmatrixSumTargetTest {
    private NumSubmatrixSumTarget target = new NumSubmatrixSumTarget();

    @Test
    public void numSubmatrixSumTarget() {
        int[][] matrix = {
                {0, 1, 0}, {1, 1, 1}, {0, 1, 0}
        };
        int res = target.numSubmatrixSumTarget2(matrix, 0);
        log.info("{}", res);
        Assert.assertEquals(4, res);
    }
}