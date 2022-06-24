package com.liyongquan.array;

import lombok.extern.slf4j.Slf4j;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 面试题 17.21. 直方图的水量
 * <p>
 * 给定一个直方图(也称柱状图)，假设有人从上面源源不断地倒水，最后直方图能存多少水量?直方图的宽度为 1。
 * <p>
 * <p>
 * <p>
 * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的直方图，在这种情况下，可以接 6 个单位的水（蓝色部分表示水）。 感谢 Marcos 贡献此图。
 * <p>
 * 示例:
 * <p>
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/volume-of-histogram-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
@Slf4j
public class VolumeOfHistogram {
    /**
     * 先来个暴力解法
     * <p>
     * 时间复杂度O(n^2)
     *
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int len = height.length;
        if (len == 0) {
            return 0;
        }
        int res = 0;
        for (int i = 0; i < len; i++) {
            //找到左边最高的点
            int left = 0, l = i - 1;
            while (l >= 0) {
                left = Math.max(left, height[l--]);
            }
            //右边的最高点
            int right = 0, r = i + 1;
            while (r < len) {
                right = Math.max(right, height[r++]);
            }
            int min = Math.min(left, right);
            if (min > height[i]) {
                //log.info("idx:{},height:{}", i, min - height[i]);
                res += min - height[i];
            }
        }
        return res;
    }

    /**
     * 单调栈
     *
     * 时间复杂度O(n)
     *
     * @param height
     * @return
     */
    public int trap2(int[] height) {
        int len = height.length;
        if (len == 0) {
            return 0;
        }
        //计算右侧的单调栈
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < len; i++) {
            while (!deque.isEmpty() && deque.peekLast() < height[i]) {
                deque.pollLast();
            }
            deque.offerLast(height[i]);
        }
        int left = 0, res = 0;
        for (int i = 0; i < len; i++) {
            if (!deque.isEmpty() && deque.peekFirst() == height[i]) {
                deque.pop();
            }
            int min = Math.min(left, deque.isEmpty() ? 0 : deque.peekFirst());
            if (min > height[i]) {
                //log.info("idx:{},height:{}", i, min - height[i]);
                res += min - height[i];
            }
            left = Math.max(left, height[i]);
        }
        return res;
    }
}
