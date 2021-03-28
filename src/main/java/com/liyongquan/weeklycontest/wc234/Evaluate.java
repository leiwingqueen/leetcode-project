package com.liyongquan.weeklycontest.wc234;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Evaluate {
    public String evaluate(String s, List<List<String>> knowledge) {
        Map<String, String> map = new HashMap<>();
        for (List<String> kv : knowledge) {
            map.put(kv.get(0), kv.get(1));
        }
        int len = s.length();
        int idx = 0;
        StringBuilder sb = new StringBuilder();
        while (idx < len) {
            if (s.charAt(idx) == '(') {
                idx++;
                int start = idx;
                while (idx < len && s.charAt(idx) != ')') {
                    idx++;
                }
                //[start,idx)
                String key = s.substring(start, idx);
                sb.append(map.getOrDefault(key, "?"));
            } else {
                sb.append(s.charAt(idx));
            }
            idx++;
        }
        return sb.toString();
    }
}
