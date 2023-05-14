package com.liyongquan.heap;

import org.junit.Test;

import static org.junit.Assert.*;

public class RearrangeBarcodesTest {
    private RearrangeBarcodes rb = new RearrangeBarcodes();

    // [1,1,1,2,2,2]
    @Test
    public void rearrangeBarcodes() {
        int[] res = rb.rearrangeBarcodes(new int[]{1, 1, 1, 2, 2, 2});
        for (int re : res) {
            System.out.print(re + ",");
        }
    }
}