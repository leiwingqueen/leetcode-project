package com.liyongquan.weeklycontest.wc304;

import java.util.Arrays;

public class MinimumOperations {
    public int minimumOperations(int[] nums) {
        Arrays.sort(nums);
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                int tmp = nums[i];
                for (int j = i; j < nums.length; j++) {
                    nums[j] -= tmp;
                }
                res++;
            }
        }
        return res;
    }
}
