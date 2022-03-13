package com.liyongquan.weeklycontest.wc284;

public class MaximumTop {
    public int maximumTop(int[] nums, int k) {
        if (k == 0) {
            return nums[0];
        }
        int len = nums.length;
        if (len == 1) {
            return k % 2 == 0 ? nums[0] : -1;
        }
        if (k - 1 >= len) {
            int max = 0;
            for (int i = 0; i < len; i++) {
                max = Math.max(max, nums[i]);
            }
            return max;
        } else {
            //尽可能多pop数字
            int lMax = 0;
            for (int i = 0; i < k - 1; i++) {
                lMax = Math.max(lMax, nums[i]);
            }
            if (k < len && lMax < nums[k]) {
                lMax = nums[k];
            }
            return lMax;
        }
    }
}
