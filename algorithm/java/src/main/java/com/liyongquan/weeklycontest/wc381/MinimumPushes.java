package com.liyongquan.weeklycontest.wc381;

import java.util.Arrays;

public class MinimumPushes {
    public int minimumPushes(String word) {
        int n = word.length();
        Integer[] cnt = new Integer[26];
        for (int i = 0; i < 26; i++) {
            cnt[i] = 0;
        }
        for (int i = 0; i < n; i++) {
            cnt[word.charAt(i) - 'a']++;
        }
        Arrays.sort(cnt, (o1, o2) -> o2 - o1);
        int c = 8;
        int step = 1;
        int res = 0;
        for (int i = 0; i < 26; i++) {
            if (cnt[i] > 0) {
                res += step * cnt[i];
                c--;
                if (c == 0) {
                    step++;
                    c = 8;
                }
            }
        }
        return res;
    }
}
