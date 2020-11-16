package com.liyongquan.dp;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class BestLineTest {
    private BestLine bl = new BestLine();

    /**
     * 输入： [[0,0],[1,1],[1,0],[2,0]]
     * 输出： [0,2]
     */
    @Test
    public void bestLine() {
        int[][] line = new int[][]{
                {0, 0},
                {1, 1},
                {1, 0},
                {2, 0}
        };
        int[] ints = bl.bestLine2(line);
        for (int i : ints) {
            System.out.print(i + ",");
        }
        Assert.assertArrayEquals(new int[]{0, 2}, ints);
    }
}