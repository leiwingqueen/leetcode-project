package com.liyongquan.weeklycontest.wc234;

public class ReinitializePermutation {
    public int reinitializePermutation(int n) {
        int[] p = new int[n], r = new int[n];
        for (int i = 0; i < n; i++) {
            p[i] = i;
        }
        int cnt = 0;
        while (true) {
            int diff = 0;
            for (int i = 0; i < n; i++) {
                int num;
                if (i % 2 == 0) {
                    num = p[i / 2];
                } else {
                    num = p[n / 2 + (i - 1) / 2];
                }
                if (num != i) {
                    diff++;
                }
                r[i] = num;
            }
            cnt++;
            if (diff == 0) {
                return cnt;
            }
            p = r;
            r = new int[n];
        }
    }

    public int reinitializePermutation2(int n) {
        return 2 * n / 3;
    }
}
