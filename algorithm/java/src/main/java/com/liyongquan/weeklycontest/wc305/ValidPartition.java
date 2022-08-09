package com.liyongquan.weeklycontest.wc305;

public class ValidPartition {
    public boolean validPartition(int[] nums) {
        int n = nums.length;
        if (n == 2) {
            return nums[0] == nums[1];
        }
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        dp[2] = nums[0] == nums[1];
        dp[3] = (nums[0] == nums[1] && nums[1] == nums[2])
                || (nums[1] == nums[0] + 1 && nums[2] == nums[1] + 1);
        for (int i = 4; i <= n; i++) {
            if (nums[i - 1] == nums[i - 2] && dp[i - 2]
                    || nums[i - 1] == nums[i - 2] && nums[i - 2] == nums[i - 3] && dp[i - 3]
                    || nums[i - 1] == nums[i - 2] + 1 && nums[i - 2] == nums[i - 3] + 1 && dp[i - 3]) {
                dp[i] = true;
            } else {
                dp[i] = false;
            }
        }
        return dp[n];
    }
}
