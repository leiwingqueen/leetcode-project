package com.liyongquan.array;

import java.util.*;

/**
 * 496. 下一个更大元素 I
 * <p>
 * 给你两个 没有重复元素 的数组 nums1 和 nums2 ，其中nums1 是 nums2 的子集。
 * <p>
 * 请你找出 nums1 中每个元素在 nums2 中的下一个比其大的值。
 * <p>
 * nums1 中数字 x 的下一个更大元素是指 x 在 nums2 中对应位置的右边的第一个比 x 大的元素。如果不存在，对应位置输出 -1 。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums1 = [4,1,2], nums2 = [1,3,4,2].
 * 输出: [-1,3,-1]
 * 解释:
 * 对于 num1 中的数字 4 ，你无法在第二个数组中找到下一个更大的数字，因此输出 -1 。
 * 对于 num1 中的数字 1 ，第二个数组中数字1右边的下一个较大数字是 3 。
 * 对于 num1 中的数字 2 ，第二个数组中没有下一个更大的数字，因此输出 -1 。
 * 示例 2:
 * <p>
 * 输入: nums1 = [2,4], nums2 = [1,2,3,4].
 * 输出: [3,-1]
 * 解释:
 *     对于 num1 中的数字 2 ，第二个数组中的下一个较大数字是 3 。
 * 对于 num1 中的数字 4 ，第二个数组中没有下一个更大的数字，因此输出 -1 。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= nums1.length <= nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 104
 * nums1和nums2中所有整数 互不相同
 * nums1 中的所有整数同样出现在 nums2 中
 *  
 * <p>
 * 进阶：你可以设计一个时间复杂度为 O(nums1.length + nums2.length) 的解决方案吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/next-greater-element-i
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NextGreaterElement {
    /**
     * 暴力解法
     * <p>
     * 时间复杂度O(nums1.length*nums2.length)
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            map.put(nums2[i], i);
        }
        int len = nums1.length;
        int[] res = new int[len];
        for (int i = 0; i < len; i++) {
            Integer idx = map.get(nums1[i]);
            while (idx < nums2.length && nums2[idx] <= nums1[i]) {
                idx++;
            }
            res[i] = idx == nums2.length ? -1 : nums2[idx];
        }
        return res;
    }

    /**
     * 单调栈
     * <p>
     * 时间复杂度O(len1+len2)
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] nextGreaterElement2(int[] nums1, int[] nums2) {
        //存放对应的位置
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            map.put(nums2[i], i);
        }
        //构造单调栈(每个数字最多被poll一次，所以时间复杂度是O(len2))
        int len1 = nums1.length, len2 = nums2.length;
        Deque<Integer> deque = new LinkedList<>();
        int[] next = new int[len2];
        Arrays.fill(next, -1);
        for (int i = 0; i < len2; i++) {
            while (!deque.isEmpty() && nums2[deque.peekLast()] < nums2[i]) {
                next[deque.pollLast()] = nums2[i];
            }
            deque.offerLast(i);
        }
        //构造输出结果
        int[] res = new int[len1];
        for (int i = 0; i < len1; i++) {
            Integer idx = map.get(nums1[i]);
            res[i] = next[idx];
        }
        return res;
    }
}
