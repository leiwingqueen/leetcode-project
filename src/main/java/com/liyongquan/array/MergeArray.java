package com.liyongquan.array;

/**
 * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
 * <p>
 *  
 * <p>
 * 说明:
 * <p>
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 *  
 * <p>
 * 示例:
 * <p>
 * 输入:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 * <p>
 * 输出: [1,2,2,3,5,6]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MergeArray {
    /**
     * 原地算法，只需要额外O(1)的空间
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = 0, p2 = 0, tail = m - 1;
        while (p2 < n) {
            if (p1 > tail) {
                nums1[p1] = nums2[p2];
                p1++;
                p2++;
            } else if (nums1[p1] <= nums2[p2]) {
                p1++;
            } else {
                for (int i = tail; i >= p1; i--) {
                    nums1[i + 1] = nums1[i];
                }
                nums1[p1] = nums2[p2];
                p1++;
                p2++;
                tail++;
            }
        }
    }
}
