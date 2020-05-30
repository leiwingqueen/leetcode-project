package com.liyongquan.dp;

/**
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都围成一圈，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
 *
 * 示例 1:
 *
 * 输入: [2,3,2]
 * 输出: 3
 * 解释: 你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
 * 示例 2:
 *
 * 输入: [1,2,3,1]
 * 输出: 4
 * 解释: 你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/house-robber-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Robber2 {
    /**
     * 这个提示很关键
     * Since House[1] and House[n] are adjacent, they cannot be robbed together. Therefore, the problem becomes to rob either House[1]-House[n-1] or House[2]-House[n], depending on which choice offers more money. Now the problem has degenerated to the House Robber, which is already been solved.
     *
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        if (nums.length<=0) {
            return 0;
        }
        if (nums.length==1) {
            return nums[0];
        }
        return Math.max(rob1(nums,0,nums.length-2),rob1(nums,1,nums.length-1));
    }

    /**
     * 使用上一题的dp算法
     * @param nums
     * @param start
     * @param end
     * @return
     */
    private int rob1(int[] nums,int start,int end) {
        if (end-start<0) {
            return 0;
        }
        int f0 = 0, f1 = nums[start];
        for (int i = start+1; i <= end; i++) {
            int f2 = Math.max(nums[i] + f0, f1);
            f0 = f1;
            f1 = f2;
        }
        return f1;
    }
}
