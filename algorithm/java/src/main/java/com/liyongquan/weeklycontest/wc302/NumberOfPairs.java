package com.liyongquan.weeklycontest.wc302;

import java.util.Arrays;

public class NumberOfPairs {
    public int[] numberOfPairs(int[] nums) {
        Arrays.sort(nums);
        int cnt = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                cnt++;
                i++;
            }
        }
        return new int[]{cnt, nums.length - 2 * cnt};
    }
}
