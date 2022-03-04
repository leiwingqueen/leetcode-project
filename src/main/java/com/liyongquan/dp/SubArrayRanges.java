package com.liyongquan.dp;

//给你一个整数数组 nums 。nums 中，子数组的 范围 是子数组中最大元素和最小元素的差值。
//
//返回 nums 中 所有 子数组范围的 和 。
//
//子数组是数组中一个连续 非空 的元素序列。
//
// 
//
//示例 1：
//
//输入：nums = [1,2,3]
//输出：4
//解释：nums 的 6 个子数组如下所示：
//[1]，范围 = 最大 - 最小 = 1 - 1 = 0
//[2]，范围 = 2 - 2 = 0
//[3]，范围 = 3 - 3 = 0
//[1,2]，范围 = 2 - 1 = 1
//[2,3]，范围 = 3 - 2 = 1
//[1,2,3]，范围 = 3 - 1 = 2
//所有范围的和是 0 + 0 + 0 + 1 + 1 + 2 = 4
//示例 2：
//
//输入：nums = [1,3,3]
//输出：4
//解释：nums 的 6 个子数组如下所示：
//[1]，范围 = 最大 - 最小 = 1 - 1 = 0
//[3]，范围 = 3 - 3 = 0
//[3]，范围 = 3 - 3 = 0
//[1,3]，范围 = 3 - 1 = 2
//[3,3]，范围 = 3 - 3 = 0
//[1,3,3]，范围 = 3 - 1 = 2
//所有范围的和是 0 + 0 + 0 + 2 + 0 + 2 = 4
//示例 3：
//
//输入：nums = [4,-2,-3,4,1]
//输出：59
//解释：nums 中所有子数组范围的和是 59
// 
//
//提示：
//
//1 <= nums.length <= 1000
//-109 <= nums[i] <= 109
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/sum-of-subarray-ranges
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import java.util.Stack;

public class SubArrayRanges {
    public long subArrayRanges(int[] nums) {
        int n = nums.length;
        int[] leftMin = new int[n], rightMin = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                leftMin[i] = i;
            } else {
                leftMin[i] = stack.peek();
            }
            stack.add(i);
        }
        stack.clear();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                rightMin[i] = i;
            } else {
                rightMin[i] = stack.peek();
            }
            stack.add(i);
        }
        long sumMin = 0;
        for (int i = 0; i < n; i++) {
            sumMin += (i - leftMin[i]) * (rightMin[i] - i) * nums[i];
        }

        int[] leftMax = new int[n], rightMax = new int[n];
        stack.clear();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                leftMax[i] = i;
            } else {
                leftMax[i] = stack.peek();
            }
            stack.add(i);
        }
        stack.clear();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                rightMax[i] = i;
            } else {
                rightMax[i] = stack.peek();
            }
            stack.add(i);
        }

        long sumMax = 0;
        for (int i = 0; i < n; i++) {
            sumMax += (i - leftMax[i]) * (rightMax[i] - i) * nums[i];
        }
        return sumMax - sumMin;
    }
}
