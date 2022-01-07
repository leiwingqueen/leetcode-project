package com.liyongquan.array;

import java.util.HashMap;
import java.util.Map;

public class Cloth {
    public int satisfy(String[][] list) {
        Map<String, Integer> mp = new HashMap<>(), need = new HashMap<>();
        for (String[] d : list) {
            mp.put(d[0], mp.getOrDefault(d[0], 0) + 1);
            need.put(d[1], need.getOrDefault(d[1], 0) + 1);
        }
        int res = 0;
        for (Map.Entry<String, Integer> entry : need.entrySet()) {
            //尺寸
            String s = entry.getKey();
            int cnt = Math.min(mp.getOrDefault(s, 0), entry.getValue());
            res += cnt;
        }
        return res;
    }
}
