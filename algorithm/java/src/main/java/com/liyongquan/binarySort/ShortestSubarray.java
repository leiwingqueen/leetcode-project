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

import com.liyongquan.bit.HammingDistance;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class ShortestSubarray {
    /**
     * 暴力。时间复杂度O(n^2)
     * <p>
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

    public int shortestSubarray2(int[] nums, int k) {
        int sum = 0;
        int n = nums.length;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(0, 0);
        int res = -1;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            SortedMap<Integer, Integer> sub = map.headMap(sum - k + 1);
            int mx = -1;
            for (Map.Entry<Integer, Integer> entry : sub.entrySet()) {
                mx = Math.max(mx, entry.getValue());
            }
            if (mx >= 0 && (res < 0 || (i - mx + 1) < res)) {
                res = i - mx + 1;
            }
            map.put(sum, i + 1);
        }
        return res;
    }
}
