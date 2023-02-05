package com.liyongquan.weeklycontest.wc331;

// 沿街有一排连续的房屋。每间房屋内都藏有一定的现金。现在有一位小偷计划从这些房屋中窃取现金。
//
//由于相邻的房屋装有相互连通的防盗系统，所以小偷 不会窃取相邻的房屋 。
//
//小偷的 窃取能力 定义为他在窃取过程中能从单间房屋中窃取的 最大金额 。
//
//给你一个整数数组 nums 表示每间房屋存放的现金金额。形式上，从左起第 i 间房屋中放有 nums[i] 美元。
//
//另给你一个整数数组 k ，表示窃贼将会窃取的 最少 房屋数。小偷总能窃取至少 k 间房屋。
//
//返回小偷的 最小 窃取能力。
//
//
//
//示例 1：
//
//输入：nums = [2,3,5,9], k = 2
//输出：5
//解释：
//小偷窃取至少 2 间房屋，共有 3 种方式：
//- 窃取下标 0 和 2 处的房屋，窃取能力为 max(nums[0], nums[2]) = 5 。
//- 窃取下标 0 和 3 处的房屋，窃取能力为 max(nums[0], nums[3]) = 9 。
//- 窃取下标 1 和 3 处的房屋，窃取能力为 max(nums[1], nums[3]) = 9 。
//因此，返回 min(5, 9, 9) = 5 。
//示例 2：
//
//输入：nums = [2,7,9,3,1], k = 2
//输出：2
//解释：共有 7 种窃取方式。窃取能力最小的情况所对应的方式是窃取下标 0 和 4 处的房屋。返回 max(nums[0], nums[4]) = 2 。
//
//
//提示：
//
//1 <= nums.length <= 105
//1 <= nums[i] <= 109
//1 <= k <= (nums.length + 1)/2

public class minCapability {
    public int minCapability(int[] nums, int k) {
        int n = nums.length;
        int[][] dp = new int[n][k + 1];
        dp[0][1] = nums[0];
        for (int i = 2; i <= k; i++) {
            dp[0][i] = -1;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= k; j++) {
                if (j > (i + 2) / 2) {
                    dp[i][j] = -1;
                } else {
                    dp[i][j] = dp[i - 1][j];
                    if (i - 2 >= 0 && dp[i - 2][j - 1] >= 0) {
                        int sub = Math.max(nums[i], dp[i - 2][j - 1]);
                        if (dp[i][j] < 0 || sub < dp[i][j]) {
                            dp[i][j] = sub;
                        }
                    }
                }
            }
        }
        return dp[n - 1][k];
    }
}
