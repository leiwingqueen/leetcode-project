package com.liyongquan.weeklycontest.wc295;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class MinimumObstaclesTest {
    private MinimumObstacles obstacles = new MinimumObstacles();

    @Test
    public void minimumObstacles() {
        int res = obstacles.minimumObstacles(new int[][]{
                {0, 1, 1}, {1, 1, 0}, {1, 1, 0}
        });
        Assert.assertEquals(2, res);
    }
}