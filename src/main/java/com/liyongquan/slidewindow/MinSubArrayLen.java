package com.liyongquan.slidewindow;

/**
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的 连续 子数组，并返回其长度。如果不存在符合条件的子数组，返回 0。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：s = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 *  
 * <p>
 * 进阶：
 * <p>
 * 如果你已经完成了 O(n) 时间复杂度的解法, 请尝试 O(n log n) 时间复杂度的解法。
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-size-subarray-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MinSubArrayLen {
    /**
     * 套用滑动窗口的模板
     * <p>
     * 问题，如何证明滑动窗口的时间复杂度是O(n)?
     * <p>
     * 对于任意一个指针，只会往一个方向移动。两个指针的最坏的时间复杂度是O(2n)
     * <p>
     * 空间复杂度O(1)
     *
     * @param s
     * @param nums
     * @return
     */
    public int minSubArrayLen(int s, int[] nums) {
        int l = 0, r = 0, sum = 0, min = nums.length + 1;
        while (r < nums.length) {
            //右边界移动
            while (sum < s && r < nums.length) {
                sum += nums[r];
                r++;
            }
            //判断是否有达解
            if (sum < s) {
                return min > nums.length ? 0 : min;
            }
            //左边界移动
            while (sum >= s) {
                sum -= nums[l];
                l++;
            }
            //更新结果
            int len = r - l + 1;
            min = Math.min(min, len);
        }
        return min > nums.length ? 0 : min;
    }

    /**
     * 滑动窗口的写法还能简化一下
     *
     * @param s
     * @param nums
     * @return
     */
    public int minSubArrayLen2(int s, int[] nums) {
        int l = 0, r = 0, sum = 0, min = nums.length + 1;
        while (r < nums.length) {
            //右边界移动
            sum += nums[r];
            r++;
            //左边界移动
            while (sum >= s) {
                //更新结果
                int len = r - l;
                min = Math.min(min, len);
                sum -= nums[l];
                l++;
            }
        }
        return min > nums.length ? 0 : min;
    }

    //还有一种方案是前缀树+二分查找的方案，也是比较有意思
}
