package com.liyongquan.weeklycontest.wc234;

import java.util.HashSet;
import java.util.Set;

public class NumDifferentIntegers {
    public int numDifferentIntegers(String word) {
        int len = word.length();
        Set<Integer> set = new HashSet<>();
        int idx = 0;
        while (idx < len) {
            char ch = word.charAt(idx);
            if (ch >= '0' && ch <= '9') {
                int num = 0;
                while (idx < len && word.charAt(idx) >= '0' && word.charAt(idx) <= '9') {
                    num = num * 10 + word.charAt(idx) - '0';
                    idx++;
                }
                set.add(num);
            }
            idx++;
        }
        return set.size();
    }
}
