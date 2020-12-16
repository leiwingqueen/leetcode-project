package com.liyongquan.twopointer;

/**
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 * 示例 2：
 * <p>
 * 输入：height = [4,2,0,3,2,5]
 * 输出：9
 *  
 * <p>
 * 提示：
 * <p>
 * n == height.length
 * 0 <= n <= 3 * 104
 * 0 <= height[i] <= 105
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/trapping-rain-water
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class TrappingTainWater {
    /**
     * 暴力解法，一个个格子计算能够装的水量
     *
     * 性能击败5%
     *
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int water = 0;
        for (int i = 0; i < height.length; i++) {
            //左边的最大高度
            int l = i - 1, lmax = 0;
            while (l >= 0) {
                if (height[l] > lmax) {
                    lmax = height[l];
                }
                l--;
            }
            //右边的最大值
            int r = i + 1, rmax = 0;
            while (r < height.length) {
                if (height[r] > rmax) {
                    rmax = height[r];
                }
                r++;
            }
            int min = Math.min(lmax, rmax);
            if (min > height[i]) {
                water += min - height[i];
            }
        }
        return water;
    }
}
