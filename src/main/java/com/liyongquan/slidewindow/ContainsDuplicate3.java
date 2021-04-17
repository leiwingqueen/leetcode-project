package com.liyongquan.slidewindow;

import java.util.Map;
import java.util.TreeMap;

/**
 * 220. 存在重复元素 III
 * <p>
 * 给你一个整数数组 nums 和两个整数 k 和 t 。请你判断是否存在 两个不同下标 i 和 j，使得 abs(nums[i] - nums[j]) <= t ，同时又满足 abs(i - j) <= k 。
 * <p>
 * 如果存在则返回 true，不存在返回 false。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3,1], k = 3, t = 0
 * 输出：true
 * 示例 2：
 * <p>
 * 输入：nums = [1,0,1,1], k = 1, t = 2
 * 输出：true
 * 示例 3：
 * <p>
 * 输入：nums = [1,5,9,1,5,9], k = 2, t = 3
 * 输出：false
 *  
 * <p>
 * 提示：
 * <p>
 * 0 <= nums.length <= 2 * 104
 * -231 <= nums[i] <= 231 - 1
 * 0 <= k <= 104
 * 0 <= t <= 231 - 1
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/contains-duplicate-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ContainsDuplicate3 {
    /**
     * 滑动窗口+有序集合
     * <p>
     * 时间复杂度O(nlog(n))
     *
     * @param nums
     * @param k
     * @param t
     * @return
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        int len = nums.length;
        if (len == 0 || k == 0) {
            return false;
        }
        int l = 0, r = 0;
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        while (r < len) {
            //查找左边窗口的最小值,时间复杂度O(log(n))
            Integer ceilingKey = treeMap.ceilingKey(nums[r]);
            Integer floorKey = treeMap.floorKey(nums[r]);
            long min = Long.MAX_VALUE;
            if (ceilingKey != null) {
                min = ceilingKey.longValue() - nums[r];
            }
            if (floorKey != null) {
                min = Math.min(min, nums[r] - floorKey.longValue());
            }
            if (min <= t) {
                return true;
            }
            //窗口右移
            treeMap.put(nums[r], treeMap.getOrDefault(nums[r], 0) + 1);
            r++;
            if (r - l > k) {
                //窗口向左移动
                Integer cnt = treeMap.get(nums[l]);
                if (cnt == 1) {
                    treeMap.remove(nums[l]);
                } else {
                    treeMap.put(nums[l], cnt - 1);
                }
                l++;
            }
        }
        return false;
    }
}
