package com.liyongquan.greedy;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class AvoidFlood {
    public int[] avoidFlood(int[] rains) {
        int n = rains.length;
        int[] res = new int[n];
        TreeSet<Integer> free = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            if (rains[i] == 0) {
                free.add(i);
            }
        }
        int idx = 0;
        Map<Integer, Integer> prePos = new HashMap<>();
        for (int rain : rains) {
            if (rain > 0) {
                res[idx] = -1;
                if (prePos.containsKey(rain)) {
                    Integer pos = prePos.get(rain);
                    // [pos+1,idx)查找空闲位置
                    Integer ceiling = free.ceiling(pos + 1);
                    if (ceiling != null && ceiling < idx) {
                        res[ceiling] = rain;
                        free.remove(ceiling);
                    } else {
                        return new int[]{};
                    }
                }
                prePos.put(rain, idx);
            }
            idx++;
        }
        for (Integer f : free) {
            res[f] = 1;
        }
        return res;
    }
}
