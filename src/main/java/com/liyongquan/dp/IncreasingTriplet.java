package com.liyongquan.dp;

//给你一个整数数组 nums ，判断这个数组中是否存在长度为 3 的递增子序列。
//
// 如果存在这样的三元组下标 (i, j, k) 且满足 i < j < k ，使得 nums[i] < nums[j] < nums[k] ，返回
//true ；否则，返回 false 。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,2,3,4,5]
//输出：true
//解释：任何 i < j < k 的三元组都满足题意
//
//
// 示例 2：
//
//
//输入：nums = [5,4,3,2,1]
//输出：false
//解释：不存在满足题意的三元组
//
// 示例 3：
//
//
//输入：nums = [2,1,5,0,4,6]
//输出：true
//解释：三元组 (3, 4, 5) 满足题意，因为 nums[3] == 0 < nums[4] == 4 < nums[5] == 6
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 5 * 10⁵
// -2³¹ <= nums[i] <= 2³¹ - 1
//
//
//
//
// 进阶：你能实现时间复杂度为 O(n) ，空间复杂度为 O(1) 的解决方案吗？
// Related Topics 贪心 数组 👍 452 👎 0

public class IncreasingTriplet {
    /**
     * 先尝试最简单的暴力解法
     * <p>
     * 必然超时
     * <p>
     * 时间复杂度O(n^3)
     *
     * @param nums
     * @return
     */
    public boolean increasingTriplet(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len - 2; i++) {
            for (int j = i + 1; j < len - 1; j++) {
                if (nums[j] <= nums[i]) {
                    continue;
                }
                for (int k = j + 1; k < len; k++) {
                    if (nums[k] > nums[j]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //TODO:dp解法
}
