package com.liyongquan.dp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class MaximumRequestsTest {
    private MaximumRequests mr = new MaximumRequests();

    /**
     * 3
     * [[0,0],[1,2],[2,1]]
     */
    @Test
    public void maximumRequests() {
        int res = mr.maximumRequests(3, new int[][]{
                {0, 0},
                {1, 2},
                {2, 1}
        });
        Assert.assertEquals(3, res);
    }
}