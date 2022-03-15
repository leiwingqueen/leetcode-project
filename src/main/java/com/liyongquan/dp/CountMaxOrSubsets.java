package com.liyongquan.dp;

//2044. 统计按位或能得到最大值的子集数目
//给你一个整数数组 nums ，请你找出 nums 子集 按位或 可能得到的 最大值 ，并返回按位或能得到最大值的 不同非空子集的数目 。
//
//如果数组 a 可以由数组 b 删除一些元素（或不删除）得到，则认为数组 a 是数组 b 的一个 子集 。如果选中的元素下标位置不一样，则认为两个子集 不同 。
//
//对数组 a 执行 按位或 ，结果等于 a[0] OR a[1] OR ... OR a[a.length - 1]（下标从 0 开始）。
//
// 
//
//示例 1：
//
//输入：nums = [3,1]
//输出：2
//解释：子集按位或能得到的最大值是 3 。有 2 个子集按位或可以得到 3 ：
//- [3]
//- [3,1]
//示例 2：
//
//输入：nums = [2,2,2]
//输出：7
//解释：[2,2,2] 的所有非空子集的按位或都可以得到 2 。总共有 23 - 1 = 7 个子集。
//示例 3：
//
//输入：nums = [3,2,1,5]
//输出：6
//解释：子集按位或可能的最大值是 7 。有 6 个子集按位或可以得到 7 ：
//- [3,5]
//- [3,1,5]
//- [3,2,5]
//- [3,2,1,5]
//- [2,5]
//- [2,1,5]
// 
//
//提示：
//
//1 <= nums.length <= 16
//1 <= nums[i] <= 105
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/count-number-of-maximum-bitwise-or-subsets
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

public class CountMaxOrSubsets {
    public int countMaxOrSubsets(int[] nums) {
        int or = 0;
        for (int num : nums) {
            or |= num;
        }
        return dfs(nums, 0, or, 0);
    }

    private int dfs(int[] nums, int or, int mx, int idx) {
        if (idx == nums.length) {
            if (or == mx) {
                return 1;
            } else {
                return 0;
            }
        }
        int sum = 0;
        //不选
        int sub = dfs(nums, or, mx, idx + 1);
        sum += sub;
        if ((or | nums[idx]) != or) {
            sum += dfs(nums, or | nums[idx], mx, idx + 1);
        } else {
            sum += sub;
        }
        return sum;
    }

    /**
     * 既然回溯可以，相当于穷举所有的可能性了。每个位选和不选分别用1和0表示，那么也遍历所有场景
     *
     * @param nums
     * @return
     */
    public int countMaxOrSubsets2(int[] nums) {
        int or = 0;
        for (int num : nums) {
            or |= num;
        }
        int cnt = 0;
        for (int i = 0; i < (1 << nums.length); i++) {
            int d = 0;
            for (int j = 0; j < nums.length; j++) {
                if ((i & (1 << j)) != 0) {
                    d |= nums[j];
                }
            }
            if (d == or) {
                cnt++;
            }
        }
        return cnt;
    }
}
