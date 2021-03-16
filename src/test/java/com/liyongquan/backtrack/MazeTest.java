package com.liyongquan.backtrack;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class MazeTest {
    private Maze maze=new Maze();

    @Test
    public void hasPath() {
        int[][] matrix = {
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0},
                {1, 1, 0, 1, 1},
                {0, 0, 0, 0, 0}
        };
        boolean res = maze.hasPath(matrix, new int[]{0, 4}, new int[]{4, 4});
        Assert.assertEquals(true,res);
    }
}