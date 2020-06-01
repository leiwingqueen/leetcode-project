package com.liyongquan.array;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstring {
    /**
     * 暴力解法
     *
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

    /**
     * 滑动窗口算法
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring2(String s) {
        if (s.length() <= 0) {
            return 0;
        }
        int left = 0, right = 0;
        int max = 0, count = 0;
        Set<Character> set = new HashSet<>();
        while (left < s.length()) {
            while (right < s.length() && !set.contains(s.charAt(right))) {
                set.add(s.charAt(right));
                count++;
                right++;
            }
            if (count > max) {
                max = count;
            }
            set.remove(s.charAt(left));
            count--;
            left++;
        }
        return max;
    }
}
