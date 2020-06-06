package com.liyongquan.array;

import org.junit.Test;

import static org.junit.Assert.*;

public class MergeArrayTest {
    private MergeArray mergeArray=new MergeArray();
    @Test
    public void merge() {
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        mergeArray.merge2(nums1,3,new int[]{2,5,6},3);
        for (int i = 0; i < nums1.length; i++) {
            System.out.println(nums1[i]);
        }
    }
}