package com.liyongquan.weeklycontest.wc235;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FindingUsersActiveMinutes {
    public int[] findingUsersActiveMinutes(int[][] logs, int k) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] log : logs) {
            if (!map.containsKey(log[0])) {
                map.put(log[0], new HashSet<>());
            }
            map.get(log[0]).add(log[1]);
        }
        int[] res = new int[k];
        for (Set<Integer> set : map.values()) {
            res[set.size() - 1]++;
        }
        return res;
    }
}
