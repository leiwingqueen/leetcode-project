package com.liyongquan.weeklycontest.wc381;

public class MinimumPushes {
    public int minimumPushes(String word) {
        int n = word.length();
        int[] cnt = new int[26];
        for (int i = 0; i < n; i++) {
            cnt[word.charAt(i) - 'a']++;
        }
        int c = 8;
        int step = 1;
        int res = 0;
        for (int i = 0; i < 26; i++) {
            if (cnt[i] > 0) {
                res += step;
                c--;
                if (c < 0) {
                    step++;
                    c = 8;
                }
            }
        }
        return res;
    }
}
