package com.liyongquan.weeklycontest.wc237;

import java.util.Set;

public class CheckIfPangram {
    public boolean checkIfPangram(String sentence) {
        int len = sentence.length();
        if (len < 26) {
            return false;
        }
        int[] bitmap = new int[26];
        int cnt = 26;
        for (int i = 0; i < len; i++) {
            int idx = sentence.charAt(i) - 'a';
            if (bitmap[idx] == 0) {
                cnt--;
                if (cnt == 0) {
                    return true;
                }
            }
            bitmap[idx]++;
        }
        return false;
    }
}
