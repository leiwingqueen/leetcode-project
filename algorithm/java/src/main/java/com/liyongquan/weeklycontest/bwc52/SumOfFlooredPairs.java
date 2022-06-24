package com.liyongquan.weeklycontest.bwc52;

import java.util.Arrays;

public class SumOfFlooredPairs {
    /**
     * 不通过
     *
     * @param nums
     * @return
     */
    public int sumOfFlooredPairs(int[] nums) {
        Arrays.sort(nums);
        long sum = 0;
        int mod = 1000000007;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                sum += nums[i] / nums[j];
                sum %= mod;
            }
        }
        sum = (sum + nums.length) % mod;
        return (int) sum;
    }
}
