package com.liyongquan.weeklycontest.wc305;

import java.util.Arrays;

public class LongestIdealString {
    public int longestIdealString(String s, int k) {
        int n = s.length();
        int[] pos = new int[26];
        Arrays.fill(pos, -1);
        int[] dp = new int[n];
        dp[0] = 1;
        int res = 1;
        pos[s.charAt(0) - 'a'] = 0;
        for (int i = 1; i < n; i++) {
            dp[i] = 1;
            char ch = s.charAt(i);
            int l = Math.max(ch - 'a' - k, 0);
            int r = Math.min(ch - 'a' + k, 25);
            for (int j = l; j <= r; j++) {
                if (pos[j] >= 0) {
                    dp[i] = Math.max(dp[i], dp[pos[j]] + 1);
                }
            }
            res = Math.max(res, dp[i]);
            pos[ch - 'a'] = i;
        }
        return res;
    }
}
