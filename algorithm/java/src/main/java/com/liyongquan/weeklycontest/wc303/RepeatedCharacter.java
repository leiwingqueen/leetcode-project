package com.liyongquan.weeklycontest.wc303;

public class RepeatedCharacter {
    public char repeatedCharacter(String s) {
        int[] cnt = new int[26];
        for (int i = 0; i < s.length(); i++) {
            cnt[s.charAt(i) - 'a']++;
            if (cnt[s.charAt(i) - 'a'] == 2) {
                return s.charAt(i);
            }
        }
        return ' ';
    }
}
