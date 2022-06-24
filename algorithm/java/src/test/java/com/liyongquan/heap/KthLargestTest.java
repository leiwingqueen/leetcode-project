package com.liyongquan.heap;

import org.junit.Test;

import static org.junit.Assert.*;

public class KthLargestTest {
    /**
     * ["KthLargest","add","add","add","add","add"]
     * [[1,[]],[-3],[-2],[-4],[0],[4]]
     */
    @Test
    public void test() {
        KthLargest kl = new KthLargest(1, new int[]{});
        int i = kl.add(-3);
        batchAdd(kl, new int[]{-3, -2, -4, 0, 4});
    }

    private void batchAdd(KthLargest kl, int[] array) {
        for (int num : array) {
            int res = kl.add(num);
            System.out.println(res);
        }
    }

}