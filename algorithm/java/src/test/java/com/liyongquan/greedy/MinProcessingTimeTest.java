package com.liyongquan.greedy;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;

@Slf4j
public class MinProcessingTimeTest {
    private MinProcessingTime mpt = new MinProcessingTime();

    @Test
    public void minProcessingTime() {
        int res = mpt.minProcessingTime(Arrays.asList(8, 10), Arrays.asList(2, 2, 3, 1, 8, 7, 4, 5));
        Assert.assertEquals(16, res);
    }

    // processorTime = [10,20], tasks = [2,3,1,2,5,8,4,3]
    @Test
    public void t2() {
        int res = mpt.minProcessingTime(Arrays.asList(10, 20), Arrays.asList(2, 3, 1, 2, 5, 8, 4, 3));
        Assert.assertEquals(23, res);
    }
}