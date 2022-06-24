package com.liyongquan.hash;

import java.util.*;

/**
 * 给定两个数组，编写一个函数来计算它们的交集。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出: [2,2]
 * 示例 2:
 * <p>
 * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出: [4,9]
 * 说明：
 * <p>
 * 输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致。
 * 我们可以不考虑输出结果的顺序。
 * 进阶:
 * <p>
 * 如果给定的数组已经排好序呢？你将如何优化你的算法？
 * 如果 nums1 的大小比 nums2 小很多，哪种方法更优？
 * 如果 nums2 的元素存储在磁盘上，磁盘内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/intersection-of-two-arrays-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Intersection {
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> m1 = new HashMap<>();
        Map<Integer, Integer> m2 = new HashMap<>();
        addMap(nums1, m1);
        addMap(nums2, m2);
        List<Integer> list = new ArrayList<>();
        m1.forEach((k, v) -> {
            if (m2.containsKey(k)) {
                int min = Math.min(v, m2.get(k));
                for (int i = 0; i < min; i++) {
                    list.add(k);
                }
            }
        });
        int[] result = new int[list.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    private void addMap(int[] nums1, Map<Integer, Integer> m1) {
        for (int i : nums1) {
            if (!m1.containsKey(i)) {
                m1.put(i, 1);
            } else {
                m1.put(i, m1.get(i) + 1);
            }
        }
    }

    /**
     * 时间复杂度O(m+n)
     * 空间复杂度O(m)
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersect2(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>(nums1.length);
        for (int i = 0; i < nums1.length; i++) {
            int count = map.getOrDefault(nums1[i], 0) + 1;
            map.put(nums1[i], count);
        }
        int[] result = new int[map.size()];
        int size = 0;
        for (int i = 0; i < nums2.length; i++) {
            int count = map.getOrDefault(nums2[i], 0);
            if (count > 0) {
                result[size++] = nums2[i];
                count--;
                map.put(nums2[i], count);
            }
        }
        return Arrays.copyOfRange(result, 0, size);
    }
}
