package com.liyongquan.bfs;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class NumEnclavesTest {
    private NumEnclaves ne = new NumEnclaves();

    @Test
    public void numEnclaves() {
        int[][] matrix = {
                {0, 1, 1, 0},
                {0, 0, 1, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 0}
        };
        int res = ne.numEnclaves(matrix);
        Assert.assertEquals(0, res);
    }

    @Test
    public void test2() {
        int[][] matrix = {
                {0, 0, 0, 0},
                {1, 0, 1, 0},
                {0, 1, 1, 0},
                {0, 0, 0, 0}
        };
        int res = ne.numEnclaves(matrix);
        Assert.assertEquals(3, res);
    }
}