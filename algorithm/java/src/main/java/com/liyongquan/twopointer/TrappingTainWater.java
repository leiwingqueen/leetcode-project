package com.liyongquan.twopointer;

import java.util.PriorityQueue;

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

    private static class Pair {
        int key;
        int value;
        Pair(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    /**
     * 暴力解法，一个个格子计算能够装的水量
     * <p>
     * 性能击败5%
     * <p>
     * 时间复杂度O(n^2),空间复杂度O(1)
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

    /**
     * 上面复杂度最高的地方在于都需要重新计算左右两边的最大值，能否继续利用上一次的计算结果？
     * <p>
     * 对于左边的最大值，由于我们的位点是一直往右移动的，这意味着左边界的最大值只会增加，不会减少。但是对于右边界，其实是一个递减的过程，我们需要用一个大顶堆来维护当前的最大值。
     * <p>
     * 时间复杂度O(nlogn)，空间复杂度O(n)
     * <p>
     * 性能击败17%
     *
     * @param height
     * @return
     */
    public int trap2(int[] height) {
        if (height.length == 0) {
            return 0;
        }
        int water = 0;
        int lmax = 0;
        //构造大顶堆
        PriorityQueue<Pair> pq = new PriorityQueue<>(height.length, (o1, o2) -> o2.value - o1.value);
        for (int i = 0; i < height.length; i++) {
            pq.add(new Pair(i, height[i]));
        }
        for (int i = 0; i < height.length - 1; i++) {
            //pop掉已经走过的节点
            while (pq.size() > 0 && pq.peek().key <= i) {
                pq.poll();
            }
            int rmax = pq.size() == 0 ? 0 : pq.peek().value;
            int min = Math.min(lmax, rmax);
            if (min > height[i]) {
                water += min - height[i];
            }
            //更新左最大值
            if (height[i] > lmax) {
                lmax = height[i];
            }
        }
        return water;
    }

    /**
     * 还有一个O(n)的解法，我们提前算好左右边界的值即可。
     *
     * @param height
     * @return
     */
    public int trap3(int[] height) {
        if (height.length == 0) {
            return 0;
        }
        //计算右边界
        int[] rmax = new int[height.length];
        int max = 0;
        for (int i = height.length - 1; i >= 0; i--) {
            rmax[i] = max;
            max = Math.max(max, height[i]);
        }
        //扫描并计算
        int lmax = 0, water = 0;
        for (int i = 0; i < height.length; i++) {
            int min = Math.min(lmax, rmax[i]);
            if (min > height[i]) {
                water += min - height[i];
            }
            lmax = Math.max(height[i], lmax);
        }
        return water;
    }
}
