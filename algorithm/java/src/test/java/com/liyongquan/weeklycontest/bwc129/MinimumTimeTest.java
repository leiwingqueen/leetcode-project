package com.liyongquan.weeklycontest.bwc129;

import junit.framework.TestCase;
import org.junit.Assert;

public class MinimumTimeTest extends TestCase {
    private MinimumTime minimumTime = new MinimumTime();

    // 3
    //[[0,1,2],[1,2,1],[0,2,4]]
    //[1,3,5]
    public void testMinimumTime() {
        int[] res = minimumTime.minimumTime(3, new int[][]{
                {0, 1, 2}, {1, 2, 1}, {0, 2, 4},
        }, new int[]{1, 3, 5});
        Assert.assertArrayEquals(new int[]{0,2,3},res);
    }
}