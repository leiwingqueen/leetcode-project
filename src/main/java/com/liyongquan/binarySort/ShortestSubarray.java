package com.liyongquan.binarySort;

//返回 A 的最短的非空连续子数组的长度，该子数组的和至少为 K 。
//
// 如果没有和至少为 K 的非空子数组，返回 -1 。
//
//
//
//
//
//
// 示例 1：
//
// 输入：A = [1], K = 1
//输出：1
//
//
// 示例 2：
//
// 输入：A = [1,2], K = 4
//输出：-1
//
//
// 示例 3：
//
// 输入：A = [2,-1,2], K = 3
//输出：3
//
//
//
//
// 提示：
//
//
// 1 <= A.length <= 50000
// -10 ^ 5 <= A[i] <= 10 ^ 5
// 1 <= K <= 10 ^ 9
//
// Related Topics 队列 数组 二分查找 前缀和 滑动窗口 单调队列 堆（优先队列）
// 👍 287 👎 0

public class ShortestSubarray {
    /**
     * 暴力。时间复杂度O(n^2)
     *
     * 必然超时
     *
     * @param nums
     * @param k
     * @return
     */
    public int shortestSubarray(int[] nums, int k) {
        int len = nums.length;
        //前缀和
        int[] prefixSum = new int[len + 1];
        for (int i = 1; i <= len; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }
        for (int i = 1; i <= len; i++) {
            if (check(nums, k, i, prefixSum)) {
                return i;
            }
        }
        return -1;
    }

    private boolean check(int[] nums, int k, int arrLen, int[] prefixSum) {
        for (int i = 0; i <= nums.length - arrLen; i++) {
            int sum = prefixSum[i + arrLen] - prefixSum[i];
            if (sum >= k) {
                return true;
            }
        }
        return false;
    }
}
