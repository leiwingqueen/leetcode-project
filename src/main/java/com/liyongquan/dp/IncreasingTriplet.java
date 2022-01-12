package com.liyongquan.dp;

public class IncreasingTriplet {
    /**
     * 先尝试最简单的暴力解法
     * <p>
     * 必然超时
     * <p>
     * 时间复杂度O(n^3)
     *
     * @param nums
     * @return
     */
    public boolean increasingTriplet(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len - 2; i++) {
            for (int j = i + 1; j < len - 1; j++) {
                if (nums[j] <= nums[i]) {
                    continue;
                }
                for (int k = j + 1; k < len; k++) {
                    if (nums[k] > nums[j]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
