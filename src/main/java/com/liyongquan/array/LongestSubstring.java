package com.liyongquan.array;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstring {
    /**
     * 暴力解法
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if (s.length() <= 0) {
            return 0;
        }
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            Set<Character> set = new HashSet<>();
            int count = 0;
            for (int j = i; j < s.length(); j++) {
                char c = s.charAt(j);
                if (!set.contains(c)) {
                    set.add(c);
                    count++;
                } else {
                    break;
                }
            }
            if (count > max) {
                max = count;
            }
        }
        return max;
    }
}
