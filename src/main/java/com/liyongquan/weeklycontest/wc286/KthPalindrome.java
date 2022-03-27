package com.liyongquan.weeklycontest.wc286;

import java.util.ArrayList;
import java.util.List;

public class KthPalindrome {
    public long[] kthPalindrome(int[] queries, int intLength) {
        int n = intLength / 2;
        if (intLength % 2 == 1) {
            n++;
        }
        long[] res = new long[queries.length];
        int idx = 0;
        for (int query : queries) {
            long r = find(new int[n], 0, n, query, intLength);
            res[idx++] = r;
        }
        return res;
    }

    private long find(int[] path, int idx, int n, int k, int len) {
        if (idx == n) {
            int[] p = new int[len];
            int l = 0, r = len - 1;
            int i = 0;
            while (l <= r) {
                p[l] = path[i];
                p[r] = path[i];
                i++;
                l++;
                r--;
            }
            long res = 0;
            long base = 1;
            for (int j = len - 1; j >= 0; j--) {
                res += p[j] * base;
                base *= 10;
            }
            return res;
        }
        List<Integer> list = new ArrayList<>();
        if (idx != 0) {
            list.add(0);
        }
        for (int i = 1; i <= 9; i++) {
            list.add(i);
        }
        for (int i : list) {
            int high = n - idx;
            long cnt = (long) Math.pow(10, high);
            if (k <= cnt) {
                path[idx++] = i;
                return find(path, idx, n, k, len);
            } else {
                k -= cnt;
            }
        }
        return -1;
    }
}
