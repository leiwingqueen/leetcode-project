package com.liyongquan.array;

//给定一个整数数组 nums ，返回满足下面条件的 非空、连续 子数组的数目：
//
//子数组 是数组的 连续 部分。
//子数组最左边的元素不大于子数组中的其他元素 。
// 
//
//示例 1：
//
//输入：nums = [1,4,2,5,3]
//输出：11
//解释：有 11 个有效子数组，分别是：[1],[4],[2],[5],[3],[1,4],[2,5],[1,4,2],[2,5,3],[1,4,2,5],[1,4,2,5,3] 。
//示例 2：
//
//输入：nums = [3,2,1]
//输出：3
//解释：有 3 个有效子数组，分别是：[3],[2],[1] 。
//示例 3：
//
//输入：nums = [2,2,2]
//输出：6
//解释：有 6 个有效子数组，分别为是：[2],[2],[2],[2,2],[2,2],[2,2,2] 。
// 
//
//提示：
//
//1 <= nums.length <= 5 * 104
//0 <= nums[i] <= 105
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/number-of-valid-subarrays
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import java.util.Stack;

public class ValidSubarrays {
    /**
     * 先来一个最简单的暴力
     * <p>
     * 时间复杂度O(n^2)
     *
     * @param nums
     * @return
     */
    public int validSubarrays(int[] nums) {
        int cnt = 0;
        for (int i = 0; i < nums.length; i++) {
            int j = i + 1;
            while (j < nums.length && nums[j] >= nums[i]) {
                j++;
            }
            cnt += j - i;
        }
        return cnt;
    }

    /**
     * 简单优化
     * <p>
     * 结果耗时更长
     *
     * @param nums
     * @return
     */
    public int validSubarrays2(int[] nums) {
        int cnt = 0;
        int l = 0, r = 0;
        while (l < nums.length) {
            //小于前面的数字，则右边界肯定会更大
            if (l > 0 && nums[l] > nums[l - 1]) {
                r = l + 1;
            }
            while (r < nums.length && nums[r] >= nums[l]) {
                r++;
            }
            cnt += r - l;
            l++;
        }
        return cnt;
    }

    /**
     * 单调栈，一次遍历
     * [1,2,7,11,3]
     *
     * @param nums
     * @return
     */
    public int validSubarray3(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int cnt = 0;
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                cnt += i - stack.pop();
            }
            stack.add(i);
        }
        while (stack.size() > 0) {
            cnt += nums.length - stack.pop();
        }
        return cnt;
    }
}
