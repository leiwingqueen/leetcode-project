package com.liyongquan.weeklycontest.lccpu2022;

public class PerfectMenu {
    public int perfectMenu(int[] materials, int[][] cookbooks, int[][] attribute, int limit) {
        return backtrace(materials, cookbooks, attribute, limit, 0);
    }

    private int backtrace(int[] m, int[][] cbs, int[][] attr, int limit, int idx) {
        int len = cbs.length;
        if (idx == len) {
            return limit <= 0 ? 0 : -1;
        }
        int s = backtrace(m, cbs, attr, limit, idx + 1);
        boolean match = true;
        int[] cb = cbs[idx];
        for (int i = 0; i < 5; i++) {
            if (cb[i] > m[i]) {
                match = false;
                break;
            }
        }
        if (match) {
            for (int i = 0; i < 5; i++) {
                m[i] -= cb[i];
            }
            limit -= attr[idx][1];
            if (limit < 0) {
                limit = 0;
            }
            int s2 = backtrace(m, cbs, attr, limit, idx + 1);
            if (s2 >= 0) {
                s = Math.max(s, s2 + attr[idx][0]);
            }
            for (int i = 0; i < 5; i++) {
                m[i] += cb[i];
            }
        }
        return s;
    }
}
