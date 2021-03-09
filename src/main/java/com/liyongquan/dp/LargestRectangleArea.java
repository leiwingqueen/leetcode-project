package com.liyongquan.dp;

/**
 * 84. 柱状图中最大的矩形
 * <p>
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * <p>
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 * <p>
 *  
 * <p>
 * <p>
 * <p>
 * 以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。
 * <p>
 *  
 * <p>
 * <p>
 * <p>
 * 图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。
 * <p>
 *  
 * <p>
 * 示例:
 * <p>
 * 输入: [2,1,5,6,2,3]
 * 输出: 10
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/largest-rectangle-in-histogram
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LargestRectangleArea {
    /**
     * 先来一个暴力解法
     * <p>
     * 时间复杂度O(n^3)
     * <p>
     * 超时
     *
     * @param heights
     * @return
     */
    public int largestRectangleArea(int[] heights) {
        int len = heights.length;
        int res = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                int min = Integer.MAX_VALUE;
                for (int k = i; k <= j; k++) {
                    min = Math.min(min, heights[k]);
                }
                res = Math.max(res, min * (j - i + 1));
            }
        }
        return res;
    }

    /**
     * 简单优化，减少一次最小值的扫描
     * <p>
     * 时间复杂度O(n^2)
     * <p>
     * 还是超时，看来至少要O(n)或者O(nlog(n))的时间复杂度
     *
     * @param heights
     * @return
     */
    public int largestRectangleArea2(int[] heights) {
        int len = heights.length;
        int res = 0;
        for (int i = 0; i < len; i++) {
            int min = heights[i];
            for (int j = i; j < len; j++) {
                min = Math.min(min, heights[j]);
                res = Math.max(res, min * (j - i + 1));
            }
        }
        return res;
    }

    /**
     * 枚举高
     * <p>
     * 超时
     *
     * @param heights
     * @return
     */
    public int largestRectangleArea3(int[] heights) {
        int len = heights.length;
        int res = 0;
        for (int i = 0; i < len; i++) {
            int l = i - 1, r = i + 1;
            while (l >= 0 && heights[l] >= heights[i]) {
                l--;
            }
            while (r < len && heights[r] >= heights[i]) {
                r++;
            }
            res = Math.max(res, (r - l - 1) * heights[i]);
        }
        return res;
    }

    //TODO:单调栈解法

}
