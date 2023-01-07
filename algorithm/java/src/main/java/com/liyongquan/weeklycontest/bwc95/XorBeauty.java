package com.liyongquan.weeklycontest.bwc95;

public class XorBeauty {
    public int xorBeauty(int[] nums) {
        int n = nums.length;
        int xor = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    xor ^= (nums[i] | nums[j]) & nums[k];
                }
            }
        }
        return xor;
    }
}
