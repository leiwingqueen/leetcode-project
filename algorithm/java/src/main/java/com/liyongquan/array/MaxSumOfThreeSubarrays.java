package com.liyongquan.array;

//689. 三个无重叠子数组的最大和
//给你一个整数数组 nums 和一个整数 k ，找出三个长度为 k 、互不重叠、且 3 * k 项的和最大的子数组，并返回这三个子数组。
//
//以下标的数组形式返回结果，数组中的每一项分别指示每个子数组的起始位置（下标从 0 开始）。如果有多个结果，返回字典序最小的一个。
//
// 
//
//示例 1：
//
//输入：nums = [1,2,1,2,6,7,5,1], k = 2
//输出：[0,3,5]
//解释：子数组 [1, 2], [2, 6], [7, 5] 对应的起始下标为 [0, 3, 5]。
//也可以取 [2, 1], 但是结果 [1, 3, 5] 在字典序上更大。
//示例 2：
//
//输入：nums = [1,2,1,2,1,2,1,2,1], k = 2
//输出：[0,2,4]
// 
//
//提示：
//
//1 <= nums.length <= 2 * 104
//1 <= nums[i] < 216
//1 <= k <= floor(nums.length / 3)
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/maximum-sum-of-3-non-overlapping-subarrays
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

/**
 * @author liyongquan
 * @date 2021/12/8
 */
public class MaxSumOfThreeSubarrays {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int len = nums.length;
        //先计算前缀和，方便后面统计某个区间的值
        int[] prefixSum = new int[len + 1];

        for (int i = 1; i <= len; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }
        //假设我们只选择一个区间
        int[] dp1 = new int[len + 1];
        //要保存下对应的下标
        int[] idx1 = new int[len + 1];
        for (int i = k; i <= len; i++) {
            dp1[i] = dp1[i - 1];
            idx1[i] = idx1[i - 1];
            int p1 = prefixSum[i] - prefixSum[i - k];
            if (p1 > dp1[i]) {
                dp1[i] = p1;
                idx1[i] = i - k;
            }
        }
        //选择两个区间
        int[] dp2 = new int[len + 1];
        int[][] idx2 = new int[len + 1][2];
        for (int i = 2 * k; i <= len; i++) {
            dp2[i] = dp2[i - 1];
            idx2[i][0] = idx2[i - 1][0];
            idx2[i][1] = idx2[i - 1][1];
            int p2 = prefixSum[i] - prefixSum[i - k];
            if (p2 + dp1[i - k] > dp2[i]) {
                dp2[i] = p2 + dp1[i - k];
                idx2[i][0] = idx1[i - k];
                idx2[i][1] = i - k;
            }
        }
        //选择3个区间
        int[] dp3 = new int[len + 1];
        int[][] idx3 = new int[len + 1][3];
        for (int i = 3 * k; i <= len; i++) {
            dp3[i] = dp3[i - 1];
            idx3[i][0] = idx3[i - 1][0];
            idx3[i][1] = idx3[i - 1][1];
            idx3[i][2] = idx3[i - 1][2];
            int p3 = prefixSum[i] - prefixSum[i - k];
            if (p3 + dp2[i - k] > dp3[i]) {
                dp3[i] = p3 + dp2[i - k];
                idx3[i][0] = idx2[i - k][0];
                idx3[i][1] = idx2[i - k][1];
                idx3[i][2] = i - k;
            }
        }
        return idx3[len];
    }
}
