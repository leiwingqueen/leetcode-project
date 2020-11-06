package com.liyongquan.bit;

import org.junit.Test;

import static org.junit.Assert.*;

public class SortByBitsTest {
    private SortByBits sb = new SortByBits();

    /**
     * [0,1,2,3,4,5,6,7,8]
     */
    @Test
    public void sortByBits() {
        int[] sort = sb.sortByBits(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8});
        for (int i : sort) {
            System.out.println(i);
        }
    }
}