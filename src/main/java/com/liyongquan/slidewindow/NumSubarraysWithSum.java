package com.liyongquan.slidewindow;

//给你一个二元数组 nums ，和一个整数 goal ，请你统计并返回有多少个和为 goal 的 非空 子数组。
//
// 子数组 是数组的一段连续部分。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,0,1,0,1], goal = 2
//输出：4
//解释：
//如下面黑体所示，有 4 个满足题目要求的子数组：
//[1,0,1,0,1]
//[1,0,1,0,1]
//[1,0,1,0,1]
//[1,0,1,0,1]
//
//
// 示例 2：
//
//
//输入：nums = [0,0,0,0,0], goal = 0
//输出：15
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 3 * 104
// nums[i] 不是 0 就是 1
// 0 <= goal <= nums.length
//
// Related Topics 数组 哈希表 前缀和 滑动窗口
// 👍 134 👎 0

import java.util.HashMap;
import java.util.Map;

public class NumSubarraysWithSum {
    public int numSubarraysWithSum(int[] nums, int goal) {
        int l = 0, r = 0;
        int sum = 0;
        int cnt = 0;
        while (r < nums.length) {
            while (r < nums.length && sum < goal) {
                sum += nums[r];
                r++;
            }
            if (sum < goal) {
                break;
            }
            int l1 = l;
            int r1 = r;
            while (l < r1 && nums[l] == 0) {
                l++;
            }
            while (r < nums.length && nums[r] == 0) {
                r++;
            }
            cnt += (l - l1 + 1) * (r - r1 + 1);
        }
        return cnt;
    }

    /**
     * 稍微进行一些优化，可以勉强通过
     *
     * @param nums
     * @param goal
     * @return
     */
    public int numSubarraysWithSum2(int[] nums, int goal) {
        int len = nums.length;
        //前缀和
        int[] prefixSum = new int[len + 1];
        for (int i = 1; i <= len; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }
        int cnt = 0;
        for (int i = 0; i < len; i++) {
            for (int j = len; j > i; j--) {
                if (prefixSum[j] - prefixSum[i] < goal) {
                    break;
                }
                cnt += prefixSum[j] - prefixSum[i] == goal ? 1 : 0;
            }
        }
        return cnt;
    }

    /**
     * 思路跟两数之和有点像，但是需要转换一下
     *
     * @param nums
     * @param goal
     * @return
     */
    public int numSubarraysWithSum3(int[] nums, int goal) {
        int len = nums.length;
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int cnt = 0;
        for (int i = 1; i <= len; i++) {
            sum += nums[i - 1];
            cnt += map.getOrDefault(sum - goal, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return cnt;
    }
}
