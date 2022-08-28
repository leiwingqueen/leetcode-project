package com.liyongquan.weeklycontest.wc308;

import com.liyongquan.backtrack.GrayCode;

public class GarbageCollection {
    public int garbageCollection(String[] garbage, int[] travel) {
        int n = garbage.length;
        int[] cnt = new int[3];
        int[] end = new int[3];
        for (int i = 0; i < n; i++) {
            String s = garbage[i];
            for (int j = 0; j < s.length(); j++) {
                char ch = s.charAt(j);
                if (ch == 'M') {
                    cnt[0]++;
                    end[0] = i;
                } else if (ch == 'P') {
                    cnt[1]++;
                    end[1] = i;
                } else {
                    cnt[2]++;
                    end[2] = i;
                }
            }
        }
        int res = 0;
        res += cnt[0] + cnt[1] + cnt[2];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < end[i]; j++) {
                res += travel[j];
            }
        }
        return res;
    }
}
