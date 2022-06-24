package com.liyongquan.design;

/**
 * 给定一个整数数据流和一个窗口大小，根据该滑动窗口的大小，计算其所有整数的移动平均值。
 * <p>
 * 示例:
 * <p>
 * MovingAverage m = new MovingAverage(3);
 * m.next(1) = 1
 * m.next(10) = (1 + 10) / 2
 * m.next(3) = (1 + 10 + 3) / 3
 * m.next(5) = (10 + 3 + 5) / 3
 *  
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/moving-average-from-data-stream
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 其实就是简单的滑动窗口算法，使用一个双端队列来保留最近的k个数，
 * 另外可以使用一个变量来保存总和，避免每次重复计算
 */
public class MovingAverage {
    private Deque<Integer> deque;
    private int sum;
    private int size;

    /**
     * Initialize your data structure here.
     */
    public MovingAverage(int size) {
        deque = new LinkedList<>();
        sum = 0;
        this.size = size;
    }

    public double next(int val) {
        deque.offerFirst(val);
        sum += val;
        if (deque.size() > size) {
            Integer last = deque.pollLast();
            sum -= last;
        }
        return (double) sum / deque.size();
    }
}
