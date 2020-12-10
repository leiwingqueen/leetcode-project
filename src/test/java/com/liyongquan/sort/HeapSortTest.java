package com.liyongquan.sort;

import org.junit.Test;

import javax.sql.rowset.serial.SQLOutputImpl;

import static org.junit.Assert.*;

public class HeapSortTest {
    private HeapSort hs = new HeapSort();

    @Test
    public void sort() {
        int[] arr = new int[]{4, 6, 8, 5, 9};
        hs.sort(arr);
        for (int i : arr) {
            System.out.println(i);
        }
    }
}