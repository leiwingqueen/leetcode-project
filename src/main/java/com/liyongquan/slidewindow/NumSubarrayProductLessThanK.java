package com.liyongquan.slidewindow;

import java.nio.channels.NotYetBoundException;

/**
 * 给定一个正整数数组 nums。
 * <p>
 * 找出该数组内乘积小于 k 的连续的子数组的个数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [10,5,2,6], k = 100
 * 输出: 8
 * 解释: 8个乘积小于100的子数组分别为: [10], [5], [2], [6], [10,5], [5,2], [2,6], [5,2,6]。
 * 需要注意的是 [10,5,2] 并不是乘积小于100的子数组。
 * 说明:
 * <p>
 * 0 < nums.length <= 50000
 * 0 < nums[i] < 1000
 * 0 <= k < 10^6
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subarray-product-less-than-k
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NumSubarrayProductLessThanK {
    /**
     * 滑动窗口算法
     * <p>
     * 时间复杂度O(n)，空间复杂度O(1)
     *
     * @param nums
     * @param k
     * @return
     */
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1) {
            return 0;
        }
        int l = 0, r = 0, count = 0;
        int mul = 1;
        while (l < nums.length) {
            //右边界移动
            while (mul < k && r < nums.length) {
                mul *= nums[r];
                r++;
            }
            //到达右边界
            if (mul >= k) {
                mul /= nums[r - 1];
                r--;
            }
            count += r - l;
            //移动后发现一个结点都不能满足，直接调过
            if (l == r) {
                l++;
                r++;
            } else {
                //左边界移动，假设l1<l2,我们假设[l1,r)的乘积<k，那么我们可以认为[l2,r)的乘积也必然小于k。所以我们的右指针只需要继续向右移动即可。
                mul /= nums[l];
                l++;
            }
        }
        return count;
    }

    /**
     * 先来个暴力一顿狂撸
     * <p>
     * 不通过，数据溢出
     *
     * @param nums
     * @param k
     * @return
     */
    public int numSubarrayProductLessThanK2(int[] nums, int k) {
        int len = nums.length;
        //前缀乘？
        long[] prefixSum = new long[len + 1];
        prefixSum[0] = 1;
        for (int i = 1; i <= len; i++) {
            prefixSum[i] = prefixSum[i - 1] * nums[i - 1];
        }
        //穷举所有子串
        int res = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j <= len; j++) {
                long mul = prefixSum[j] / prefixSum[i];
                if (mul < k) {
                    res++;
                }
            }
        }
        return res;
    }

    /**
     * 双指针解法2
     *
     * @param nums
     * @param k
     * @return
     */
    public int numSubarrayProductLessThanK3(int[] nums, int k) {
        int len = nums.length;
        int l = 0, r = 0;
        int window = 1;
        int res = 0;
        while (r < len) {
            window *= nums[r];
            r++;
            while (l < r && window >= k) {
                window /= nums[l];
                l++;
            }
            //[l,r)是满足<k的，我们假设固定右边界，可能的组合为r-l
            res += r - l;
        }
        return res;
    }
}
