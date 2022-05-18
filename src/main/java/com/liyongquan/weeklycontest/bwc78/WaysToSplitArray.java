package com.liyongquan.weeklycontest.bwc78;

public class WaysToSplitArray {
    public int waysToSplitArray(int[] nums) {
        int len = nums.length;
        long[] prefixSum = new long[len + 1];
        for (int i = 0; i < len; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }
        int res = 0;
        for (int i = 0; i < len - 1; i++) {
            if (prefixSum[i + 1] >= prefixSum[len] - prefixSum[i + 1]) {
                res++;
            }
        }
        return res;
    }
}
