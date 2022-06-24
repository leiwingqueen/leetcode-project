package com.liyongquan.array;

import org.junit.Test;

import java.util.List;

public class PermutationsTest {
    private Permutations pm = new Permutations();

    @Test
    public void permute() {
        List<List<Integer>> permute = pm.permute(new int[]{1, 2, 3});
        for (List<Integer> list : permute) {
            System.out.println("==============");
            for (Integer integer : list) {
                System.out.println(integer);
            }
        }
    }
}