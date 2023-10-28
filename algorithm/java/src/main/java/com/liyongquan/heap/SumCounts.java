package com.liyongquan.heap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// 给你一个下标从 0 开始的整数数组 nums 。
//
//定义 nums 一个子数组的 不同计数 值如下：
//
//令 nums[i..j] 表示 nums 中所有下标在 i 到 j 范围内的元素构成的子数组（满足 0 <= i <= j < nums.length ），那么我们称子数组 nums[i..j] 中不同值的数目为 nums[i..j] 的不同计数。
//请你返回 nums 中所有子数组的 不同计数 的 平方 和。
//
//由于答案可能会很大，请你将它对 109 + 7 取余 后返回。
//
//子数组指的是一个数组里面一段连续 非空 的元素序列。
//
//
//
//示例 1：
//
//输入：nums = [1,2,1]
//输出：15
//解释：六个子数组分别为：
//[1]: 1 个互不相同的元素。
//[2]: 1 个互不相同的元素。
//[1]: 1 个互不相同的元素。
//[1,2]: 2 个互不相同的元素。
//[2,1]: 2 个互不相同的元素。
//[1,2,1]: 2 个互不相同的元素。
//所有不同计数的平方和为 12 + 12 + 12 + 22 + 22 + 22 = 15 。
//示例 2：
//
//输入：nums = [2,2]
//输出：3
//解释：三个子数组分别为：
//[2]: 1 个互不相同的元素。
//[2]: 1 个互不相同的元素。
//[2,2]: 1 个互不相同的元素。
//所有不同计数的平方和为 12 + 12 + 12 = 3 。
//
//
//提示：
//
//1 <= nums.length <= 105
//1 <= nums[i] <= 105

public class SumCounts {
    public int sumCounts(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> idxMap = new HashMap<>();
        int[] dp = new int[n];
        dp[0] = 1;
        idxMap.put(nums[0], 0);
        int[] preSum = new int[n];
        preSum[0] = 1;
        Set<Integer> set = new HashSet<>();
        set.add(nums[0]);
        for (int i = 1; i < n; i++) {
            dp[i] = dp[i - 1] * 2;
            if (idxMap.containsKey(nums[i])) {
                Integer idx = idxMap.get(nums[i]);
                dp[i] -= dp[idx];
                preSum[i] = preSum[i - 1] + idx;
            } else {
                dp[i] += (i + 1) + 2 * preSum[i - 1];
                preSum[i] = preSum[i - 1] + i;
            }
            idxMap.put(nums[i], i);
            set.add(nums[i]);
            // preSum[i] = preSum[i - 1] + set.size();
        }
        return 0;
    }
}
