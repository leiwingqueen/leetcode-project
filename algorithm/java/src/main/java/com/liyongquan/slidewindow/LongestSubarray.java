package com.liyongquan.slidewindow;

import java.util.Deque;
import java.util.LinkedList;
import java.util.TreeMap;

/**
 * 给你一个整数数组 nums ，和一个表示限制的整数 limit，请你返回最长连续子数组的长度，该子数组中的任意两个元素之间的绝对差必须小于或者等于 limit 。
 * <p>
 * 如果不存在满足条件的子数组，则返回 0 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [8,2,4,7], limit = 4
 * 输出：2
 * 解释：所有子数组如下：
 * [8] 最大绝对差 |8-8| = 0 <= 4.
 * [8,2] 最大绝对差 |8-2| = 6 > 4.
 * [8,2,4] 最大绝对差 |8-2| = 6 > 4.
 * [8,2,4,7] 最大绝对差 |8-2| = 6 > 4.
 * [2] 最大绝对差 |2-2| = 0 <= 4.
 * [2,4] 最大绝对差 |2-4| = 2 <= 4.
 * [2,4,7] 最大绝对差 |2-7| = 5 > 4.
 * [4] 最大绝对差 |4-4| = 0 <= 4.
 * [4,7] 最大绝对差 |4-7| = 3 <= 4.
 * [7] 最大绝对差 |7-7| = 0 <= 4.
 * 因此，满足题意的最长子数组的长度为 2 。
 * 示例 2：
 * <p>
 * 输入：nums = [10,1,2,4,7,2], limit = 5
 * 输出：4
 * 解释：满足题意的最长子数组是 [2,4,7,2]，其最大绝对差 |2-7| = 5 <= 5 。
 * 示例 3：
 * <p>
 * 输入：nums = [4,2,2,2,4,4,2,2], limit = 0
 * 输出：3
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LongestSubarray {
    /**
     * 毫无疑问是一道滑动窗口的题目，问题的难点在于如果更新窗口内数字的最大值和最小值。
     * <p>
     * 首先我们想到的应该是需要一个排序的数据结构。
     * 堆的结构不合适，因为我们需要删除非堆顶节点。
     * 这里考虑使用TreeMap，底层是红黑树，删除和查找效率都是O(log(n))
     * <p>
     * 时间复杂度O(nlog(n))
     *
     * @param nums
     * @param limit
     * @return
     */
    public int longestSubarray(int[] nums, int limit) {
        int len = nums.length;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int l = 0, r = 0;
        int res = 0;
        while (r < len) {
            //窗口右移
            map.put(nums[r], map.getOrDefault(nums[r], 0) + 1);
            r++;
            //注意这里first key和last key操作都是log(n)的时间效率
            while (Math.abs(map.lastKey() - map.firstKey()) > limit) {
                //窗口左移
                Integer cnt = map.get(nums[l]);
                if (cnt == 1) {
                    map.remove(nums[l]);
                } else {
                    map.put(nums[l], cnt - 1);
                }
                l++;
            }
            res = Math.max(res, r - l);
        }
        return res;
    }

    /**
     * 使用两个单调队列来维护最大值和最小值，时间复杂度降为O(n)
     *
     * @param nums
     * @param limit
     * @return
     */
    public int longestSubarray2(int[] nums, int limit) {
        int len = nums.length;
        Deque<Integer> maxQue = new LinkedList<>(), minQue = new LinkedList<>();
        int l = 0, r = 0;
        int res = 0;
        while (r < len) {
            //窗口右移
            while (!maxQue.isEmpty() && maxQue.peekLast() < nums[r]) {
                maxQue.pollLast();
            }
            while (!minQue.isEmpty() && minQue.peekLast() > nums[r]) {
                minQue.pollLast();
            }
            maxQue.offerLast(nums[r]);
            minQue.offerLast(nums[r]);
            r++;
            while (Math.abs(maxQue.peekFirst() - minQue.peekFirst()) > limit) {
                //窗口左移
                if (maxQue.peekFirst() == nums[l]) {
                    maxQue.pollFirst();
                }
                if (minQue.peekFirst() == nums[l]) {
                    minQue.pollFirst();
                }
                l++;
            }
            res = Math.max(res, r - l);
        }
        return res;
    }
}
