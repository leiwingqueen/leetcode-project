package com.liyongquan.array;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * 128. 最长连续序列
 * <p>
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * <p>
 *  
 * <p>
 * 进阶：你可以设计并实现时间复杂度为 O(n) 的解决方案吗？
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 * 示例 2：
 * <p>
 * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
 * 输出：9
 *  
 * <p>
 * 提示：
 * <p>
 * 0 <= nums.length <= 104
 * -109 <= nums[i] <= 109
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-consecutive-sequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
@Slf4j
public class LongestConsecutive {
    /**
     * 排序，然后用滑动窗口
     * <p>
     * 时间复杂度O(nlog(n))
     * <p>
     * 不通过，还是有场景没覆盖
     *
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        int len = nums.length;
        if (len <= 1) {
            return len;
        }
        Arrays.sort(nums);
        int l = 0, r = 0;
        int res = 0;
        while (r < len) {
            r++;
            if (nums[l] + r - l - 1 != nums[r - 1]) {
                l = r - 1;
            } else {
                res = Math.max(res, r - l);
            }
        }
        return res;
    }

    /**
     * dp解法
     * <p>
     * 我们定义f(n)是前n个数字中的连续最长子序列，且nums[n-1]也选中
     * 则有：
     * f(n)=f(i)+1,其中i<n且nums[i-1]=nums[n-1]-1
     *
     * 最终结果为：max{f(i)},0<=i<n
     *
     * 时间复杂度O(nlog(n))
     *
     * @param nums
     * @return
     */
    public int longestConsecutive2(int[] nums) {
        int len = nums.length;
        if (len <= 1) {
            return len;
        }
        Arrays.sort(nums);
        int[] dp = new int[len];
        int res = 0;
        dp[0] = 1;
        for (int i = 1; i < len; i++) {
            //找到第一个和i不等的坐标
            int j = i - 1;
            while (j >= 0 && nums[j] == nums[i]) {
                j--;
            }
            if (j < 0 || nums[j] != nums[i] - 1) {
                dp[i] = 1;
            } else {
                dp[i] = dp[j] + 1;
            }
            res = Math.max(dp[i], res);
        }
        return res;
    }

}
