package com.liyongquan.weeklycontest.wc309;

import java.util.Arrays;

public class CheckDistances {
    public boolean checkDistances(String s, int[] distance) {
        int[] pos = new int[26];
        Arrays.fill(pos, -1);
        for (int i = 0; i < s.length(); i++) {
            int p = s.charAt(i) - 'a';
            if (pos[p] < 0) {
                pos[p] = i;
            } else {
                if (i - pos[p] - 1 != distance[p]) {
                    return false;
                }
            }
        }
        return true;
    }
}
