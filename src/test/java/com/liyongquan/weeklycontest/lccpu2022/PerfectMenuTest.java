package com.liyongquan.weeklycontest.lccpu2022;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class PerfectMenuTest {
    private PerfectMenu pm = new PerfectMenu();

    /**
     * meterials = [10,10,10,10,10]
     * cookbooks = [[1,1,1,1,1],[3,3,3,3,3],[10,10,10,10,10]]
     * attribute = [[5,5],[6,6],[10,10]]
     * limit = 1
     */
    @Test
    public void perfectMenu() {
        int res = pm.perfectMenu(new int[]{10, 10, 10, 10, 10}, new int[][]{{1, 1, 1, 1, 1}, {3, 3, 3, 3, 3}, {10, 10, 10, 10, 10}}, new int[][]{
                {5, 5}, {6, 6}, {10, 10}
        }, 1);
        Assert.assertEquals(11, res);
    }
}