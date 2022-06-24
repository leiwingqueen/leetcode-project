package com.liyongquan.interview;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 找到右边第一个大的值
 */
public class FindBigger {
    public int[] find(int[] arr) {
        int len = arr.length;
        Deque<Integer> pq = new LinkedList<>();
        int[] res = new int[len];
        for (int i = len - 1; i >= 0; i--) {
            while (pq.size() > 0 && pq.peekFirst() < arr[i]) {
                pq.pollFirst();
            }
            res[i] = pq.size() == 0 ? -1 : pq.peekFirst();
            pq.offerFirst(arr[i]);
        }
        return res;
    }
}
