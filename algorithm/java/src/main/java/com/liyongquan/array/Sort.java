package com.liyongquan.array;

import java.util.Arrays;

public class Sort {
    public int[] sortArray(int[] nums) {
        int[] out =new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            out[i]=nums[i];
        }
        Arrays.sort(out);
        return out;
    }

    public static void main(String[] args) {
        int[] array=new int[]{5,2,3,1};
        Sort sort=new Sort();
        int[] ints = sort.sortArray(array);
        for (int i : ints) {
            System.out.println(i);
        }
    }
}
