package com.liyongquan.backtrack;

//给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
//
// 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
//
//
//
//
//
// 示例 1：
//
//
//输入：nums = [1,2,2]
//输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
//
//
// 示例 2：
//
//
//输入：nums = [0]
//输出：[[],[0]]
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 10
// -10 <= nums[i] <= 10
//
//
//
// Related Topics 数组 回溯算法
// 👍 451 👎 0

import java.util.*;

public class SubsetsWithDup {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        backtrace(nums, new LinkedList<>(), 0, nums.length, res);
        return res;
    }

    private void backtrace(int[] nums, LinkedList<Integer> path, int idx, int len, List<List<Integer>> res) {
        //这里一定要用深拷贝
        List<Integer> r = new LinkedList<>();
        for (Integer num : path) {
            r.add(num);
        }
        res.add(r);
        if (idx == len) {
            return;
        }
        //避免选择重复的子集
        Set<Integer> set = new HashSet<>();
        for (int i = idx; i < len; i++) {
            if (set.contains(nums[i])) {
                continue;
            }
            set.add(nums[i]);
            //使用交换可以更简单地划分成两个部分
            swap(nums, i, idx);
            path.add(nums[idx]);
            backtrace(nums, path, idx + 1, len, res);
            //还原现场
            path.pollLast();
            swap(nums, idx, i);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
