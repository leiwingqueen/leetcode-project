package com.liyongquan.weeklycontest.wc282;

import java.util.HashMap;
import java.util.Map;

public class MinSteps {
    public int minSteps(String s, String t) {
        Map<Character, Integer> m1 = new HashMap<>();
        Map<Character, Integer> m2 = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            m1.put(ch, m1.getOrDefault(ch, 0) + 1);
        }
        for (int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            m2.put(ch, m2.getOrDefault(ch, 0) + 1);
        }
        int cnt = 0;
        for (Map.Entry<Character, Integer> entry : m1.entrySet()) {
            cnt += Math.min(entry.getValue(), m2.getOrDefault(entry.getKey(), 0));
        }
        return s.length() + t.length() - 2 * cnt;
    }
}
