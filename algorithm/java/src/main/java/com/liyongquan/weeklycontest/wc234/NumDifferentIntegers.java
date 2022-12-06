package com.liyongquan.weeklycontest.wc234;

import java.util.HashSet;
import java.util.Set;

public class NumDifferentIntegers {
    public int numDifferentIntegers(String word) {
        int len = word.length();
        Set<String> set = new HashSet<>();
        int idx = 0;
        while (idx < len) {
            while (idx < len && (word.charAt(idx) < '0' || word.charAt(idx) > '9')) {
                idx++;
            }
            if (idx == len) {
                break;
            }
            int l = idx;
            // 去掉前导0
            while (l < len && word.charAt(l) == '0') {
                l++;
            }
            int r = l;
            while (r < len && word.charAt(r) >= '0' && word.charAt(r) <= '9') {
                r++;
            }
            if (l == r) {
                set.add("0");
            } else {
                set.add(word.substring(l, r));
            }
            idx = r;
        }
        return set.size();
    }
}
