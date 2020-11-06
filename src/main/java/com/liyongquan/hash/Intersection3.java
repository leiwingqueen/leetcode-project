package com.liyongquan.hash;

import java.util.*;

/**
 * 给定两个数组，编写一个函数来计算它们的交集。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2]
 * 示例 2：
 * <p>
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[9,4]
 *  
 * <p>
 * 说明：
 * <p>
 * 输出结果中的每个元素一定是唯一的。
 * 我们可以不考虑输出结果的顺序。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/intersection-of-two-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Intersection3 {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> s1 = new HashSet<>(), s2 = new HashSet<>();
        for (int i : nums1) {
            s1.add(i);
        }
        for (int i : nums2) {
            s2.add(i);
        }
        List<Integer> list = new LinkedList<>();
        for (Integer i : s1) {
            if (s2.contains(i)) {
                list.add(i);
            }
        }
        int[] r = new int[list.size()];
        int idx = 0;
        for (Integer num : list) {
            r[idx++] = num;
        }
        return r;
    }
}
