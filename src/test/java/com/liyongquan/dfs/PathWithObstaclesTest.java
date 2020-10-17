package com.liyongquan.dfs;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class PathWithObstaclesTest {
    private PathWithObstacles pw = new PathWithObstacles();

    @Test
    public void test() {
        List<List<Integer>> result = pw.pathWithObstacles(new int[][]{
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
        });
        Assert.assertEquals(5, result.size());
    }

    @Test
    public void test2() {
        List<List<Integer>> lists = pw.pathWithObstacles2(new int[][]{
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1},
                {0, 0, 0, 1, 0},
                {0, 0, 0, 0, 0}
        });
    }
}