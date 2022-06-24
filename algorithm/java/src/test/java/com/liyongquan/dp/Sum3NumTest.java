package com.liyongquan.dp;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class Sum3NumTest {
    private Sum3Num sum3Num = new Sum3Num();

    @Test
    public void threeSum() {
        List<List<Integer>> lists = sum3Num.threeSum(new int[]{-1, 0, 1, 2, -1, -4});
        for (List<Integer> list : lists) {
            for (Integer i : list) {
                System.out.print(i + ",");
            }
            System.out.println();
        }
    }
}