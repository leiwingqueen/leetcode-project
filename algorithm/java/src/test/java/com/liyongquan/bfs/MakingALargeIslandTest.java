package com.liyongquan.bfs;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class MakingALargeIslandTest {
    private MakingALargeIsland ma = new MakingALargeIsland();

    @Test
    public void largestIsland() {
        int[][] grid = {
                {1, 0},
                {0, 1}
        };
        int res = ma.largestIsland(grid);
        log.info("{}", res);
        Assert.assertEquals(3, res);
    }

    @Test
    public void testLargestIsland() {
        int[][] grid = {
                {1, 1},
                {1, 0}
        };
        int res = ma.largestIsland2(grid);
        log.info("{}", res);
        Assert.assertEquals(4, res);
    }
}