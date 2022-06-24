package com.liyongquan.slidewindow;

import java.util.Arrays;

/**
 * 给你一个整数数组 nums 和整数 k ，返回最大和 sum ，满足存在 i < j 使得 nums[i] + nums[j] = sum 且 sum < k 。如果没有满足此等式的 i,j 存在，则返回 -1 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [34,23,1,24,75,33,54,8], k = 60
 * 输出：58
 * 解释：
 * 34 和 24 相加得到 58，58 小于 60，满足题意。
 * 示例 2：
 * <p>
 * 输入：nums = [10,20,30], k = 15
 * 输出：-1
 * 解释：
 * 我们无法找到和小于 15 的两个元素。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 100
 * 1 <= nums[i] <= 1000
 * 1 <= k <= 2000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum-less-than-k
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class TwoSumLessThanK {
    public int twoSumLessThanK(int[] nums, int k) {
        if (nums.length <= 1) {
            return -1;
        }
        //排序
        Arrays.sort(nums);
        int max = -1;
        for (int i = 0; i < nums.length - 1 && nums[i] <= k / 2; i++) {
            int s = k - nums[i];
            //二分查找找到另外一个数字
            int l = i + 1, r = nums.length - 1;
            //没有可行解
            if (nums[l] >= s) {
                continue;
            }
            while (l < r && nums[l] < s) {
                int middle = (l + r) / 2;
                if (nums[middle] >= s) {
                    r = middle - 1;
                } else {
                    l = middle + 1;
                }
            }
            int m = nums[l] < s ? nums[l] : nums[l - 1];
            max = Math.max(nums[i] + m, max);
        }
        return max;
    }
}
