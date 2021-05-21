package com.liyongquan.dp;

import sun.nio.cs.ext.MacHebrew;

/**
 * 在两条独立的水平线上按给定的顺序写下 nums1 和 nums2 中的整数。
 * <p>
 * 现在，可以绘制一些连接两个数字 nums1[i] 和 nums2[j] 的直线，这些直线需要同时满足满足：
 * <p>
 *  nums1[i] == nums2[j]
 * 且绘制的直线不与任何其他连线（非水平线）相交。
 * 请注意，连线即使在端点也不能相交：每个数字只能属于一条连线。
 * <p>
 * 以这种方法绘制线条，并返回可以绘制的最大连线数。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：nums1 = [1,4,2], nums2 = [1,2,4]
 * 输出：2
 * 解释：可以画出两条不交叉的线，如上图所示。
 * 但无法画出第三条不相交的直线，因为从 nums1[1]=4 到 nums2[2]=4 的直线将与从 nums1[2]=2 到 nums2[1]=2 的直线相交。
 * 示例 2：
 * <p>
 * 输入：nums1 = [2,5,1,2,5], nums2 = [10,5,2,1,5,2]
 * 输出：3
 * 示例 3：
 * <p>
 * 输入：nums1 = [1,3,7,1,7,5], nums2 = [1,9,2,5,1]
 * 输出：2
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= nums1.length <= 500
 * 1 <= nums2.length <= 500
 * 1 <= nums1[i], nums2[i] <= 2000
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/uncrossed-lines
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaxUncrossedLines {
    /**
     * 回溯解法
     * <p>
     * 超时
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        return backtrace(nums1, nums2, 0, 0, nums1.length, nums2.length);
    }

    private int backtrace(int[] nums1, int[] nums2, int idx1, int idx2, int len1, int len2) {
        if (idx1 == len1 || idx2 == len2) {
            return 0;
        }
        int cnt = 0;
        cnt = Math.max(cnt, backtrace(nums1, nums2, idx1 + 1, idx2, len1, len2));
        for (int i = idx2; i < len2; i++) {
            if (nums1[idx1] == nums2[i]) {
                cnt = Math.max(backtrace(nums1, nums2, idx1 + 1, i + 1, len1, len2) + 1, cnt);
            }
        }
        return cnt;
    }

    /**
     * dp解法
     *
     * 性能击败5%
     *
     * 时间复杂度O(m*n^2)
     * 空间复杂度O(m*n)
     *
     * 第三层循环有没办法进行简化？
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int maxUncrossedLines2(int[] nums1, int[] nums2) {
        int len1 = nums1.length, len2 = nums2.length;
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = len1 - 1; i >= 0; i--) {
            for (int j = 0; j < len2; j++) {
                int cnt = dp[i + 1][j];
                for (int k = j; k < len2; k++) {
                    if (nums1[i] == nums2[k]) {
                        cnt = Math.max(cnt, dp[i + 1][k + 1] + 1);
                    }
                }
                dp[i][j] = cnt;
            }
        }
        return dp[0][0];
    }
}
