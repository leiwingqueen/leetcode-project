package com.liyongquan.bfs;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class SnakesAndLaddersTest {
    private SnakesAndLadders sal = new SnakesAndLadders();

    @Test
    public void snakesAndLadders() {
        int[][] board = {
                {-1, -1, -1, 46, 47, -1, -1, -1},
                {51, -1, -1, 63, -1, 31, 21, -1},
                {-1, -1, 26, -1, -1, 38, -1, -1},
                {-1, -1, 11, -1, 14, 23, 56, 57},
                {11, -1, -1, -1, 49, 36, -1, 48},
                {-1, -1, -1, 33, 56, -1, 57, 21},
                {-1, -1, -1, -1, -1, -1, 2, -1},
                {-1, -1, -1, 8, 3, -1, 6, 56}
        };
        int res = sal.snakesAndLadders(board);
        log.info("{}", res);
        Assert.assertEquals(4, res);
    }
}