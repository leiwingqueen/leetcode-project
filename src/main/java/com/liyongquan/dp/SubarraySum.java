package com.liyongquan.dp;

/**
 * 560. 和为K的子数组
 * <p>
 * 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
 * <p>
 * 示例 1 :
 * <p>
 * 输入:nums = [1,1,1], k = 2
 * 输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
 * 说明 :
 * <p>
 * 数组的长度为 [1, 20,000]。
 * 数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]。
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subarray-sum-equals-k
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SubarraySum {
    /**
     * 首先遍历所有的子串，对于每个子串的和我们可以使用dp来简化复杂度
     * <p>
     * 假设f(i,j)为下标范围为[i,j]的子串，我们有f(i,j)=f(i-1,j)-A[i-1]，或者f(i,j)=f(i+1,j)+A[i]
     * <p>
     * 两者的区别的从第一行开始扫描还是从最后一行开始扫描
     * <p>
     * 时间复杂度O(n^2)
     * 空间复杂度O(n^2)
     *
     * 不通过，由于数组的长度是万级别的，因为n^2的数量级是不能满足要求的
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {
        int len = nums.length;
        int[][] dp = new int[len][len];
        int res = 0;
        //dp初始化,i==j的场景
        for (int i = 0; i < len; i++) {
            dp[i][i] = nums[i];
            if (nums[i] == k) {
                res++;
            }
        }
        //遍历所有场景
        for (int i = len - 2; i >= 0; i--) {
            for (int j = i + 1; j < len; j++) {
                dp[i][j] = dp[i + 1][j] + nums[i];
                if (dp[i][j] == k) {
                    res++;
                }
            }
        }
        return res;
    }
}
