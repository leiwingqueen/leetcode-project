package com.liyongquan.bfs;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class ShortestPathTest {
    private ShortestPath sp = new ShortestPath();

    @Test
    public void shortestPath() {
        int[][] grid = {
                {0, 1, 1},
                {1, 1, 1},
                {1, 0, 0},
        };
        int res = sp.shortestPath(grid, 1);
        log.info("{}", res);
        Assert.assertEquals(-1, res);
    }
}