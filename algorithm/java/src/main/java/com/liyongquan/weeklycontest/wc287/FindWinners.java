package com.liyongquan.weeklycontest.wc287;

import java.util.*;

public class FindWinners {
    public List<List<Integer>> findWinners(int[][] matches) {
        TreeMap<Integer, Integer> mp = new TreeMap<>();
        for (int[] match : matches) {
            int win = match[0];
            int lose = match[1];
            mp.put(win, mp.getOrDefault(win, 0));
            mp.put(lose, mp.getOrDefault(lose, 0) + 1);
        }
        List<Integer> l1 = new LinkedList<>(), l2 = new LinkedList<>();
        for (Map.Entry<Integer, Integer> entry : mp.entrySet()) {
            if (entry.getValue() == 0) {
                l1.add(entry.getKey());
            } else if (entry.getValue() == 1) {
                l2.add(entry.getKey());
            }
        }
        return Arrays.asList(l1, l2);
    }
}
