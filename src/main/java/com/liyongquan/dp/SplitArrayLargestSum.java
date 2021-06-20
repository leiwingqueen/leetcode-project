package com.liyongquan.dp;

/**
 * 410. 分割数组的最大值
 * <p>
 * 给定一个非负整数数组 nums 和一个整数 m ，你需要将这个数组分成 m 个非空的连续子数组。
 * <p>
 * 设计一个算法使得这 m 个子数组各自和的最大值最小。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [7,2,5,10,8], m = 2
 * 输出：18
 * 解释：
 * 一共有四种方法将 nums 分割为 2 个子数组。 其中最好的方式是将其分为 [7,2,5] 和 [10,8] 。
 * 因为此时这两个子数组各自的和的最大值为18，在所有情况中最小。
 * 示例 2：
 * <p>
 * 输入：nums = [1,2,3,4,5], m = 2
 * 输出：9
 * 示例 3：
 * <p>
 * 输入：nums = [1,4,4], m = 3
 * 输出：4
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 1000
 * 0 <= nums[i] <= 106
 * 1 <= m <= min(50, nums.length)
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/split-array-largest-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SplitArrayLargestSum {
    /**
     * f(n,m)=min(max{f(n-k,m-1),A[k]+A[k+1]+...A[n-1]}),1<=k<n
     * <p>
     * 时间复杂度O(n^2*m)
     *
     * @param nums
     * @param m
     * @return
     */
    public int splitArray(int[] nums, int m) {
        int len = nums.length;
        //计算前缀和
        int[] preSum = new int[len + 1];
        for (int i = 1; i <= len; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }
        //初始化
        int[][] dp = new int[len + 1][m + 1];
        for (int i = 1; i <= len; i++) {
            dp[i][1] = preSum[i];
        }
        for (int i = 2; i <= len; i++) {
            for (int j = 2; j <= Math.min(i, m); j++) {
                //这里的j<=i
                int min = Integer.MAX_VALUE;
                for (int k = i - 1; k >= j - 1; k--) {
                    int sub = Math.max(dp[k][j - 1], preSum[i] - preSum[k]);
                    min = Math.min(min, sub);
                }
                dp[i][j] = min;
            }
        }
        return dp[len][m];
    }
}
