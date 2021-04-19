package com.liyongquan.weeklycontest.bwc50;

public class MinOperations {
    public int minOperations(int[] nums) {
        int len = nums.length;
        int cnt = 0;
        for (int i = 1; i < len; i++) {
            if (nums[i] <= nums[i - 1]) {
                cnt += nums[i - 1] - nums[i] + 1;
                nums[i] = nums[i - 1] + 1;
            }
        }
        return cnt;
    }
}
