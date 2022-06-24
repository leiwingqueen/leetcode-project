package com.liyongquan.array;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class Permutations2Test {
    private Permutations2 pm = new Permutations2();

    @Test
    public void permuteUnique() {
        List<List<Integer>> lists = pm.permuteUnique(new int[]{1, 1, 2});
        for (List<Integer> list : lists) {
            System.out.println("==============");
            for (Integer integer : list) {
                System.out.println(integer);
            }
        }
    }
}