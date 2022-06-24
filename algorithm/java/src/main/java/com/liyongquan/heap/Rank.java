package com.liyongquan.heap;

import java.util.PriorityQueue;

/**
 * 现有n 个乱序数，都大于 1000 ，让取排行榜前十，时间复杂度为o(n)，
 * top10, 或者 topK，应用场景榜单Top：10，堆实现Top k
 * <p>
 * 纯粹是为了实现榜单问题
 */
public class Rank {
    /**
     * 构造小根堆，维护一个top k的堆
     * <p>
     * 时间复杂度O(nlog(k))
     * 空间复杂度O(k)
     *
     * @param arr
     * @param k
     * @return
     */
    public int[] rank(int[] arr, int k) {
        if (k == 0 || arr.length == 0) {
            return new int[]{};
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>(k + 1);
        for (int i : arr) {
            if (pq.size() < k || pq.peek().intValue() < i) {
                pq.add(i);
            }
            if (pq.size() > k) {
                pq.poll();
            }
        }
        //输出k个数组
        int[] res = new int[pq.size()];
        for (int i = pq.size() - 1; i >= 0; i--) {
            res[i] = pq.poll();
        }
        return res;
    }
}
