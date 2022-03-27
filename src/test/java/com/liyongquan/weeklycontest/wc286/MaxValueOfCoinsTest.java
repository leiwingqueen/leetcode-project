package com.liyongquan.weeklycontest.wc286;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@Slf4j
public class MaxValueOfCoinsTest {
    private MaxValueOfCoins coins = new MaxValueOfCoins();

    /**
     * [[1,100,3],[7,8,9]]
     * 2
     */
    @Test
    public void maxValueOfCoins() {
        List<List<Integer>> build = build(new int[][]{
                {1, 100, 3},
                {7, 8, 9}
        });
        int r = coins.maxValueOfCoins(build, 2);
        Assert.assertEquals(101, r);
    }


    private List<List<Integer>> build(int[][] matrix) {
        List<List<Integer>> res = new ArrayList<>();
        for (int[] arr : matrix) {
            List<Integer> r = new ArrayList<>();
            for (int i : arr) {
                r.add(i);
            }
            res.add(r);
        }
        return res;
    }
}