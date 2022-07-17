package com.liyongquan.weeklycontest.wc302;

import javafx.util.Pair;

import java.util.Arrays;

public class SmallestTrimmedNumbers {
    public int[] smallestTrimmedNumbers(String[] nums, int[][] queries) {
        int n = nums[0].length();
        int[] res = new int[queries.length];
        int idx = 0;
        Pair<String, Integer>[] tmp = new Pair[nums.length];
        for (int i = 0; i < nums.length; i++) {
            tmp[i] = new Pair<>(nums[i], i);
        }
        for (int[] query : queries) {
            int k = query[0];
            int t = query[1];
            Arrays.sort(tmp, (o1, o2) -> {
                String s1 = o1.getKey().substring(n - t, n);
                String s2 = o2.getKey().substring(n - t, n);
                int c = s1.compareTo(s2);
                if (c != 0) {
                    return c;
                }
                return o1.getValue() - o2.getValue();
            });
            res[idx++] = tmp[k - 1].getValue();
        }
        return res;
    }
}
