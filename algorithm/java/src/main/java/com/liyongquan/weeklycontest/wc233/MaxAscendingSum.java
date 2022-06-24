package com.liyongquan.weeklycontest.wc233;

public class MaxAscendingSum {
    public int maxAscendingSum(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }
        int sum = nums[0];
        int idx = 1;
        int max = 0;
        while (idx < len) {
            if (nums[idx] > nums[idx - 1]) {
                sum += nums[idx];
            } else {
                max = Math.max(sum, max);
                sum = nums[idx];
            }
            idx++;
        }
        return max;
    }
}
