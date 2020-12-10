package com.liyongquan.sort;

import org.junit.Test;

import static org.junit.Assert.*;

public class HeapSort2Test {
    private HeapSort2 hs2 = new HeapSort2();

    @Test
    public void sort() {
        int[] arr = new int[]{4, 6, 8, 5, 9};
        hs2.sort(arr);
        for (int i : arr) {
            System.out.println(i);
        }
    }
}