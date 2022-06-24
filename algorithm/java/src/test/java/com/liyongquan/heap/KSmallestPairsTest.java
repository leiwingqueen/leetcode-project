package com.liyongquan.heap;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class KSmallestPairsTest {
    private KSmallestPairs pairs = new KSmallestPairs();

    /**
     * [1,1,2]
     * [1,2,3]
     * 10
     */
    @Test
    public void kSmallestPairs() {
        List<List<Integer>> lists = pairs.kSmallestPairs2(new int[]{1, 1, 2}, new int[]{1, 2, 3}, 10);
        for (List<Integer> list : lists) {
            System.out.println("[" + list.get(0) + "," + list.get(1) + "]");
        }
    }

    /**
     * [1,7,11]
     * [2,4,6]
     * 3
     */
    @Test
    public void test() {
        List<List<Integer>> lists = pairs.kSmallestPairs2(new int[]{1, 7, 11}, new int[]{2, 4, 6}, 3);
        for (List<Integer> list : lists) {
            System.out.println("[" + list.get(0) + "," + list.get(1) + "]");
        }
    }
}