package com.liyongquan.weeklycontest.bwc53;

import java.util.Arrays;

public class MinPairSum {
    /**
     * 贪心
     *
     * @param nums
     * @return
     */
    public int minPairSum(int[] nums) {
        Arrays.sort(nums);
        int max = 0;
        for (int i = 0; i < nums.length / 2; i++) {
            max = Math.max(nums[i] + nums[nums.length - i - 1], max);
        }
        return max;
    }
}
