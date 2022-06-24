package com.liyongquan.weeklycontest.wc242;

public class CheckZeroOnes {
    public boolean checkZeroOnes(String s) {
        return count(s, '1') > count(s, '0');
    }

    private int count(String s, char ch) {
        int len = s.length();
        int p = 0;
        int max = 0;
        while (p < len) {
            if (s.charAt(p) != ch) {
                p++;
                continue;
            }
            int cnt = 0;
            while (p < len && s.charAt(p) == ch) {
                cnt++;
                p++;
            }
            max = Math.max(max, cnt);
        }
        return max;
    }
}
