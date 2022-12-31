package com.liyongquan.design;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeSet;

// 优化解法
public class ExamRoom2 {
    private TreeSet<Integer> set;
    private PriorityQueue<int[]> pq;
    private int n;

    public ExamRoom2(int n) {
        set = new TreeSet<>();
        pq = new PriorityQueue<>((o1, o2) -> {
            int d1 = o1[1] - o1[0];
            int d2 = o2[1] - o2[0];
            if (d1 / 2 != d2 / 2) {
                return d2 - d1;
            } else {
                return o1[0] - o2[0];
            }
        });
        this.n = n;
    }

    // 假设有n个区间，则每一次检查位置相当于O(n)，这里能否进行优化?
    public int seat() {
        if (set.size() == 0) {
            set.add(0);
            return 0;
        }
        int res = -1;
        int mx = 0;
        // 左右两个点特殊处理
        if (!set.contains(0)) {
            if (set.first() >= mx) {
                mx = set.first();
                res = 0;
            }
        }
        if (!set.contains(n - 1)) {
            if (n - 1 - set.last() > mx) {
                res = n - 1;
            }
        }
        // 延迟删除
        while (pq.size() > 0) {
            int[] peek = pq.peek();
            if (set.contains(peek[0]) && set.contains(peek[1])) {
                break;
            }
            pq.poll();
        }
        if (pq.size() > 0) {
            int[] peek = pq.peek();
            if ((peek[1] - peek[0]) / 2 > mx || ((peek[1] - peek[0]) / 2 == mx && (peek[0] + (peek[1] - peek[0]) / 2) < res)) {
                res = peek[0] + (peek[1] - peek[0]) / 2;
            }
        }
        if (res == 0) {
            pq.offer(new int[]{0, set.first()});
        } else if (res == n - 1) {
            pq.offer(new int[]{set.last(), n - 1});
        } else {
            int[] poll = pq.poll();
            pq.offer(new int[]{poll[0], res});
            pq.offer(new int[]{res, poll[1]});
        }
        set.add(res);
        return res;
    }

    public void leave(int p) {
        set.remove(p);
        Integer floor = set.floor(p);
        Integer ceiling = set.ceiling(p);
        if (floor != null && ceiling != null) {
            pq.add(new int[]{floor, ceiling});
        }
    }
}
