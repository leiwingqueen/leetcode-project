package com.liyongquan.array;

/**
 * 4. 寻找两个正序数组的中位数
 * <p>
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 解释：合并数组 = [1,2,3] ，中位数 2
 * 示例 2：
 * <p>
 * 输入：nums1 = [1,2], nums2 = [3,4]
 * 输出：2.50000
 * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 * 示例 3：
 * <p>
 * 输入：nums1 = [0,0], nums2 = [0,0]
 * 输出：0.00000
 * 示例 4：
 * <p>
 * 输入：nums1 = [], nums2 = [1]
 * 输出：1.00000
 * 示例 5：
 * <p>
 * 输入：nums1 = [2], nums2 = []
 * 输出：2.00000
 *  
 * <p>
 * 提示：
 * <p>
 * nums1.length == m
 * nums2.length == n
 * 0 <= m <= 1000
 * 0 <= n <= 1000
 * 1 <= m + n <= 2000
 * -106 <= nums1[i], nums2[i] <= 106
 *  
 * <p>
 * 进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindMedianSortedArrays {
    /**
     * 归并排序
     * <p>
     * 类似归并排序，然后找到位于(m+n)/2位置上的数字
     * 奇数 (m+n)/2
     * 偶数 (m+n)/2-1,(m+n)/2
     * <p>
     * 时间复杂度O(m+n)
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length, len2 = nums2.length;
        if (len1 == 0 && len2 == 0) {
            return 0;
        }
        int idx1 = 0, idx2 = 0, idx = 0;
        //偶数
        if ((len1 + len2) % 2 == 0) {
            int pre = 0, cur = 0;
            while (idx <= (len1 + len2) / 2) {
                pre = cur;
                if (idx2 == len2 || (idx1 < len1 && nums1[idx1] <= nums2[idx2])) {
                    cur = nums1[idx1];
                    idx1++;
                } else {
                    cur = nums2[idx2];
                    idx2++;
                }
                idx++;
            }
            return (pre + cur) / 2.0;
        } else {
            //奇数
            int middle = 0;
            while (idx <= (len1 + len2) / 2) {
                if (idx2 == len2 || (idx1 < len1 && nums1[idx1] <= nums2[idx2])) {
                    middle = nums1[idx1];
                    idx1++;
                } else {
                    middle = nums2[idx2];
                    idx2++;
                }
                idx++;
            }
            return middle;
        }

    }
}
