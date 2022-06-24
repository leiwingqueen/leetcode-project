package com.liyongquan.weeklycontest.bwc50;

public class GetMaximumXor {
    public int[] getMaximumXor(int[] nums, int maximumBit) {
        int len = nums.length;
        int[] preSum = new int[len];
        preSum[0] = nums[0];
        for (int i = 1; i < len; i++) {
            preSum[i] = preSum[i - 1] ^ nums[i];
        }
        int[] res = new int[len];
        int mask = (1 << maximumBit) - 1;
        for (int i = 0; i < len; i++) {
            res[i] = (preSum[len - i - 1] & mask) ^ mask;
        }
        return res;
    }
}
