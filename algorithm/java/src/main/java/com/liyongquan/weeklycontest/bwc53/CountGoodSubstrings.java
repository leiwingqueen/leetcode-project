package com.liyongquan.weeklycontest.bwc53;

public class CountGoodSubstrings {
    public int countGoodSubstrings(String s) {
        if (s.length() < 3) {
            return 0;
        }
        int l = 0, r = 0;
        int[] bitmap = new int[26];
        int duplicate = 0;
        int cnt = 0;
        while (r < s.length()) {
            int idx1 = s.charAt(r) - 'a';
            if (bitmap[idx1] == 1) {
                duplicate++;
            }
            bitmap[idx1]++;
            r++;
            if (r - l == 3) {
                if (duplicate == 0) {
                    cnt++;
                }
                int idx2 = s.charAt(l) - 'a';
                bitmap[idx2]--;
                if (bitmap[idx2] == 1) {
                    duplicate--;
                }
                l++;
            }
        }
        return cnt;
    }
}
