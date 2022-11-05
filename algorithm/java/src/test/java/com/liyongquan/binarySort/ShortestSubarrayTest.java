package com.liyongquan.binarySort;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class ShortestSubarrayTest {
    private ShortestSubarray shortestSubarray = new ShortestSubarray();

    @Test
    public void shortestSubarray2() {
        int res = shortestSubarray.shortestSubarray2(new int[]{1}, 1);
        log.info("{}", res);
    }

    // [17,85,93,-45,-21]
    //150
    @Test
    public void test2() {
        int res = shortestSubarray.shortestSubarray2(new int[]{17, 85, 93, -45, -21}, 150);
        Assert.assertEquals(2, res);
    }
}