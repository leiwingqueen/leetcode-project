package com.liyongquan.weeklycontest.wc302;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class MinOperationsTest {
    private MinOperations minOperations = new MinOperations();

    @Test
    public void minOperations() {
        int res = minOperations.minOperations(new int[]{4, 3, 6}, new int[]{8, 2, 6, 10});
        Assert.assertEquals(-1, res);
    }
}