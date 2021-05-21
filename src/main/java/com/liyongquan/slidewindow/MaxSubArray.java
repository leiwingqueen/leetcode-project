package com.liyongquan.slidewindow;

/**
 * 给定一个整数数组，找出总和最大的连续数列，并返回总和。
 * <p>
 * 示例：
 * <p>
 * 输入： [-2,1,-3,4,-1,2,1,-5,4]
 * 输出： 6
 * 解释： 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * 进阶：
 * <p>
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 * <p>
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/contiguous-sequence-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaxSubArray {
    /**
     * 暴力+前缀和
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int len = nums.length;
        int[] pre = new int[len + 1];
        for (int i = 1; i <= len; i++) {
            pre[i] = pre[i - 1] + nums[i - 1];
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j <= len; j++) {
                int sum = pre[j] - pre[i];
                max = Math.max(max, sum);
            }
        }
        return max;
    }

    /**
     * 我们可以找到前缀和最大的j，并且找到j前面最小的前缀和
     * <p>
     * 时间复杂度O(n)
     * 空间复杂度O(n)
     *
     * @param nums
     * @return
     */
    public int maxSubArray2(int[] nums) {
        int len = nums.length;
        int[] pre = new int[len + 1];
        int max = Integer.MIN_VALUE;
        int min = 0;
        for (int i = 1; i <= len; i++) {
            pre[i] = pre[i - 1] + nums[i - 1];
            max = Math.max(pre[i] - min, max);
            min = Math.min(min, pre[i]);
        }
        return max;
    }
}
