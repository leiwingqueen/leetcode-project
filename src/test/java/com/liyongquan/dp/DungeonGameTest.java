package com.liyongquan.dp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class DungeonGameTest {
    private DungeonGame dg = new DungeonGame();

    @Test
    public void calculateMinimumHP() {
        int[][] matrix = {
                {-2, -3, 3},
                {-5, -10, 1},
                {10, 30, -5}
        };
        int res = dg.calculateMinimumHP(matrix);
        log.info("{}", res);
        Assert.assertEquals(7, res);
    }

    @Test
    public void test2() {
        int[][] matrix = {
                {0, -3},
        };
        int res = dg.calculateMinimumHP(matrix);
        log.info("{}", res);
        Assert.assertEquals(4, res);
    }

    /**
     * [[1,-3,3],[0,-2,0],[-3,-3,-3]]
     */
    @Test
    public void test3() {
        int[][] matrix = {
                {1, -3, 3},
                {0, -2, 0},
                {-3, -3, -3}
        };
        int res = dg.calculateMinimumHP(matrix);
        log.info("{}", res);
        Assert.assertEquals(3, res);
    }
}