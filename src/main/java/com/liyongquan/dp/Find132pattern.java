package com.liyongquan.dp;

import java.util.Stack;

/**
 * 456. 132 模式
 * <p>
 * 给你一个整数数组 nums ，数组中共有 n 个整数。132 模式的子序列 由三个整数 nums[i]、nums[j] 和 nums[k] 组成，并同时满足：i < j < k 和 nums[i] < nums[k] < nums[j] 。
 * <p>
 * 如果 nums 中存在 132 模式的子序列 ，返回 true ；否则，返回 false 。
 * <p>
 *  
 * <p>
 * 进阶：很容易想到时间复杂度为 O(n^2) 的解决方案，你可以设计一个时间复杂度为 O(n logn) 或 O(n) 的解决方案吗？
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3,4]
 * 输出：false
 * 解释：序列中不存在 132 模式的子序列。
 * 示例 2：
 * <p>
 * 输入：nums = [3,1,4,2]
 * 输出：true
 * 解释：序列中有 1 个 132 模式的子序列： [1, 4, 2] 。
 * 示例 3：
 * <p>
 * 输入：nums = [-1,3,2,0]
 * 输出：true
 * 解释：序列中有 3 个 132 模式的的子序列：[-1, 3, 2]、[-1, 3, 0] 和 [-1, 2, 0] 。
 *  
 * <p>
 * 提示：
 * <p>
 * n == nums.length
 * 1 <= n <= 104
 * -109 <= nums[i] <= 109
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/132-pattern
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Find132pattern {
    /**
     * 先来个暴力
     * <p>
     * 超时
     *
     * @param nums
     * @return
     */
    public boolean find132pattern(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len - 2; i++) {
            for (int j = i + 1; j < len - 1; j++) {
                for (int k = j + 1; k < len; k++) {
                    if (nums[i] < nums[k] && nums[k] < nums[j]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    /**
     * 我们假设固定j点，那么需要找到i<j的最小值min{nums[i]}
     * k>j的最大值max{nums[k]},其中k>j,且nums[k]<nums[j]
     * <p>
     * 暴力优化2
     * <p>
     * 时间复杂度O(n^2)
     *
     * @param nums
     * @return
     */
    public boolean find132pattern2(int[] nums) {
        int len = nums.length;
        if (len < 3) {
            return false;
        }
        //左边的最小值用一个变量维护，减少了重新计算的过程
        int min = nums[0];
        for (int j = 1; j < len - 1; j++) {
            if (min < nums[j]) {
                //查找右边的第二大的数字
                for (int k = j + 1; k < len; k++) {
                    if (nums[k] > min && nums[k] < nums[j]) {
                        return true;
                    }
                }
            }
            min = Math.min(min, nums[j]);
        }
        return false;
    }

    /**
     * 单调栈解法，这个是真的难想
     * <p>
     * 用一个单调递减的栈来获得右边的次小的值,栈底的元素是最大值，栈顶的元素是最小值
     *
     * @param nums
     * @return
     */
    public boolean find132pattern2(int[] nums) {
        int len = nums.length;
        if (len < 3) {
            return false;
        }
        //提前计算左边的最小值
        int[] left = new int[len];
        left[1] = nums[0];
        for (int i = 2; i < len; i++) {
            left[i] = Math.min(left[i - 1], nums[i - 1]);
        }
        //单调栈
        Stack<Integer> stack = new Stack<>();
        stack.push(nums[len - 1]);
        for (int i = len - 2; i >= 1; i--) {
            /*if (stack.peek() < nums[i]) {
                int lastPop = 0;
                while (!stack.isEmpty() && stack.peek() <= nums[i]) {
                    lastPop = stack.pop();
                }
                left[i]<nums[i]&&nums[i]
            }*/
            //TODO:还没想通...
            stack.push(nums[i]);
        }
        return false;
    }

}
