package com.liyongquan.weeklycontest.spring2021;

import java.util.Arrays;

public class PurchasePlans {
    /**
     * 时间复杂度O(n^2)
     *
     * @param nums
     * @param target
     * @return
     */
    public int purchasePlans(int[] nums, int target) {
        int len = nums.length;
        int fn = nums[0] + nums[1] <= target ? 1 : 0;
        for (int i = 2; i < len; i++) {
            int cnt = 0;
            if (nums[i] < target) {
                for (int j = 0; j < i; j++) {
                    if (nums[j] <= target - nums[i]) {
                        cnt++;
                    }
                }
                cnt %= 1000000007;
            }
            fn = (fn + cnt) % 1000000007;
        }
        return fn;
    }

    /**
     * 稍微优化后勉强能过
     *
     * @param nums
     * @param target
     * @return
     */
    public int purchasePlans2(int[] nums, int target) {
        int len = nums.length;
        Arrays.sort(nums);
        int fn = nums[0] + nums[1] <= target ? 1 : 0;
        for (int i = 2; i < len; i++) {
            if (nums[i] < target) {
                int j = i - 1;
                while (j >= 0 && nums[j] > target - nums[i]) {
                    j--;
                }
                fn = (fn + j + 1) % 1000000007;
            }
        }
        return fn;
    }
}
