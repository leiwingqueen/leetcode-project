package com.liyongquan.weeklycontest.wc282;

public class PrefixCount {
    public int prefixCount(String[] words, String pref) {
        int cnt = 0;
        for (String word : words) {
            if (word.startsWith(pref)) {
                cnt++;
            }
        }
        return cnt;
    }
}
