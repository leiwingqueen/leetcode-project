package com.liyongquan.heap;

//中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
//
//例如，
//
//[2,3,4] 的中位数是 3
//
//[2,3] 的中位数是 (2 + 3) / 2 = 2.5
//
//设计一个支持以下两种操作的数据结构：
//
//void addNum(int num) - 从数据流中添加一个整数到数据结构中。
//double findMedian() - 返回目前所有元素的中位数。
//示例：
//
//addNum(1)
//addNum(2)
//findMedian() -> 1.5
//addNum(3)
//findMedian() -> 2
//进阶:
//
//如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？
//如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/find-median-from-data-stream
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import java.util.PriorityQueue;

/**
 * @author liyongquan
 * @date 2021/8/27
 */
public class MedianFinder {
    /**
     * initialize your data structure here.
     */
    private PriorityQueue<Integer> queueMax;
    private PriorityQueue<Integer> queueMin;
    private int size;

    public MedianFinder() {
        queueMax = new PriorityQueue<>((o1, o2) -> o2 - o1);
        queueMin = new PriorityQueue<>();
        size = 0;
    }

    public void addNum(int num) {
        if (size == 0) {
            queueMax.add(num);
        } else {
            //需要保证两个队列的数量相差不大于1
            if (num > queueMax.peek()) {
                queueMin.add(num);
                if (queueMin.size() > queueMax.size()) {
                    queueMax.add(queueMin.poll());
                }
            } else {
                queueMax.add(num);
                if (queueMax.size() > queueMin.size() + 1) {
                    queueMin.add(queueMax.poll());
                }
            }
        }
        size++;
    }

    public double findMedian() {
        if (size == 0) {
            return 0D;
        }
        if (size % 2 == 0) {
            return (queueMax.peek() + queueMin.peek()) / 2D;
        } else {
            return queueMax.peek();
        }
    }
}
