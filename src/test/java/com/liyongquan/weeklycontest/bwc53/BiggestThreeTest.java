package com.liyongquan.weeklycontest.bwc53;

import com.liyongquan.tree.TreeNode;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class BiggestThreeTest {
    private BiggestThree biggestThree = new BiggestThree();

    @Test
    public void getBiggestThree() {
        int[][] matrix = {
                {3, 4, 5, 1, 3}, {3, 3, 4, 2, 3}, {20, 30, 200, 40, 10}, {1, 5, 5, 4, 1}, {4, 3, 2, 2, 5}
        };
        int[] three = this.biggestThree.getBiggestThree(matrix);
        for (int num : three) {
            log.info("{}", num);
        }
    }

    @Test
    public void test2() {
        int[][] matrix = {
                {20, 17, 9, 13, 5, 2, 9, 1, 5},
                {14, 9, 9, 9, 16, 18, 3, 4, 12},
                {18, 15, 10, 20, 19, 20, 15, 12, 11},
                {19, 16, 19, 18, 8, 13, 15, 14, 11},
                {4, 19, 5, 2, 19, 17, 7, 2, 2}
        };
        int[] three = this.biggestThree.getBiggestThree(matrix);
        for (int num : three) {
            log.info("{}", num);
        }
        Assert.assertArrayEquals(new int[]{107, 103, 102}, three);
    }
}