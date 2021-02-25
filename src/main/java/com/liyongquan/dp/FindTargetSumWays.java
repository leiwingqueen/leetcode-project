package com.liyongquan.dp;

/**
 * 494. 目标和
 * <p>
 * 给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号 + 和 -。对于数组中的任意一个整数，你都可以从 + 或 -中选择一个符号添加在前面。
 * <p>
 * 返回可以使最终数组和为目标数 S 的所有添加符号的方法数。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：nums: [1, 1, 1, 1, 1], S: 3
 * 输出：5
 * 解释：
 * <p>
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 * <p>
 * 一共有5种方法让最终目标和为3。
 *  
 * <p>
 * 提示：
 * <p>
 * 数组非空，且长度不会超过 20 。
 * 初始的数组的和不会超过 1000 。
 * 保证返回的最终结果能被 32 位整数存下。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/target-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindTargetSumWays {
    public static final int MAX_SUM = 1000;

    /**
     * 这道题本身类似一个背包问题。你给每个数字加符号，最终只有两种情况，加正号或者负号
     * <p>
     * 我们假设f(i,s)为前i个数字，总和为s的可能性
     * <p>
     * f(i,s)=f(i-1,s-A[i])+f(i-1,s+A[i])
     * <p>
     * 0的场景还需要特殊处理
     * <p>
     * 由于s有可能是负数，我们需要把负数转换成正数，统一把纵坐标右移即可。
     * <p>
     * 而且题意有
     * 初始的数组的和不会超过 1000 。
     * 则我们知道和的范围为[-1000,1000]，右移动1000位即可
     * <p>
     * 时间复杂度O(2000*n)
     * 空间复杂度O(2000*n)
     *
     * @param nums
     * @param S
     * @return
     */
    public int findTargetSumWays(int[] nums, int S) {
        int len = nums.length;
        int col = 2 * MAX_SUM + 1;
        if (S > MAX_SUM || S < -MAX_SUM) {
            return 0;
        }
        int[][] dp = new int[len][col];
        //初始化
        for (int i = 0; i < col; i++) {
            dp[0][i] = Math.abs(i - MAX_SUM) == nums[0] ? 1 : 0;
        }
        //dp迭代
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < col; j++) {
                if (j - nums[i] >= 0) {
                    dp[i][j] += dp[i - 1][j - nums[i]];
                }
                if (j + nums[i] < col) {
                    dp[i][j] += dp[i - 1][j + nums[i]];
                }
            }
        }
        return dp[len - 1][S + MAX_SUM];
    }
}
