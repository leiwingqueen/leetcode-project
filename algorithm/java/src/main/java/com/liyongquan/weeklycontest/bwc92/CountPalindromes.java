package com.liyongquan.weeklycontest.bwc92;

import java.util.SortedSet;
import java.util.TreeSet;

public class CountPalindromes {
    int mod = 1_000_000_007;
    private String s;

    TreeSet<Integer>[] sets = new TreeSet[10];

    public int countPalindromes(String s) {
        this.s = s;
        for (int i = 0; i < 10; i++) {
            sets[i] = new TreeSet<>();
        }
        for (int i = 0; i < s.length(); i++) {
            sets[s.charAt(i) - '0'].add(i);
        }
        return dfs5(0, s.length() - 1);
    }

    public int dfs3(int start, int end) {
        if (end - start + 1 < 3) {
            return 0;
        }
        int res = 0;
        for (int i = 0; i < 10; i++) {
            TreeSet<Integer> set = sets[i];
            SortedSet<Integer> froms = set.tailSet(start);
            for (Integer from : froms) {
                for (Integer to : set.tailSet(from)) {
                    if (to > end) {
                        break;
                    }
                    if (to - from + 1 < 3) {
                        continue;
                    }
                    res = (res + to - from - 1) % mod;
                }
            }
        }
        return res;
    }

    public int dfs5(int start, int end) {
        if (end - start + 1 < 5) {
            return 0;
        }
        int res = 0;
        for (int i = 0; i < 10; i++) {
            TreeSet<Integer> set = sets[i];
            SortedSet<Integer> froms = set.tailSet(start);
            for (Integer from : froms) {
                for (Integer to : set.tailSet(from)) {
                    if (to > end) {
                        break;
                    }
                    if (to - from + 1 < 5) {
                        continue;
                    }
                    res = (res + dfs3(from + 1, to - 1)) % mod;
                }
            }
        }
        return res;
    }
}
