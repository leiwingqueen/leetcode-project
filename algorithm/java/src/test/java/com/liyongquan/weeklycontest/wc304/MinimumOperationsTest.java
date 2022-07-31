package com.liyongquan.weeklycontest.wc304;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class MinimumOperationsTest {
    private MinimumOperations operations = new MinimumOperations();

    @Test
    public void minimumOperations() {
        int res = operations.minimumOperations(new int[]{1, 5, 0, 3, 5});
        Assert.assertEquals(3, res);
    }
}