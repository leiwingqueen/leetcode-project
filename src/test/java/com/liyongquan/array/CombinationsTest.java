package com.liyongquan.array;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CombinationsTest {
    private Combinations cb = new Combinations();

    @Test
    public void combine() {
        List<List<Integer>> combine = cb.combine(4, 2);
        for (List<Integer> list : combine) {
            System.out.println("====================");
            for (Integer integer : list) {
                System.out.println(integer);
            }
        }
    }
}