package com.liyongquan.weeklycontest.wc282;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class MinimumFinishTimeTest {
    private MinimumFinishTime mft = new MinimumFinishTime();

    /**
     * [[2,3],[3,4]]
     * 5
     * 4
     */
    @Test
    public void minimumFinishTime() {
        int res = mft.minimumFinishTime(new int[][]{
                {2, 3},
                {3, 4}
        }, 5, 4);
        Assert.assertEquals(21, res);
    }
}