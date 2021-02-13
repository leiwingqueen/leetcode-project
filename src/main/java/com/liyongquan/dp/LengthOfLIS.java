package com.liyongquan.dp;

/**
 * 300. 最长递增子序列
 * <p>
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * <p>
 * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 * <p>
 *  
 * 示例 1：
 * <p>
 * 输入：nums = [10,9,2,5,3,7,101,18]
 * 输出：4
 * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
 * 示例 2：
 * <p>
 * 输入：nums = [0,1,0,3,2,3]
 * 输出：4
 * 示例 3：
 * <p>
 * 输入：nums = [7,7,7,7,7,7,7]
 * 输出：1
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 2500
 * -104 <= nums[i] <= 104
 *  
 * <p>
 * 进阶：
 * <p>
 * 你可以设计时间复杂度为 O(n2) 的解决方案吗？
 * 你能将算法的时间复杂度降低到 O(n log(n)) 吗?
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-increasing-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LengthOfLIS {
    /**
     * dp解法
     * <p>
     * 这个dp表达式我最后还是没想到，看了解题发现自己还是太幼稚了。
     * <p>
     * 本质上这种问题都可以转化成选或者不选的问题
     * <p>
     * 我们先定义f(n)为前n个元素，并且以A[n-1]结尾的子序列的最大长度
     * <p>
     * 我们有f(n)=max{f(i)}+1,其中 1<=i<=n-1,A[i-1]<A[n-1]
     * <p>
     * 最终的结果为max{f(n)},1<=n<=len
     *
     * 时间复杂度O(n^2)
     *
     * 空间复杂度O(n)
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }
        //dp初始化
        int[] dp = new int[len];
        dp[0] = 1;
        int res = 1;
        //dp迭代
        for (int i = 1; i < len; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    max = Math.max(max, dp[j]);
                }
            }
            dp[i] = max + 1;
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
