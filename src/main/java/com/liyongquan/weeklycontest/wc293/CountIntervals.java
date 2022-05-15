package com.liyongquan.weeklycontest.wc293;

import java.util.TreeMap;
import java.util.TreeSet;

public class CountIntervals {
    TreeMap<Integer, RangeEntry> ts;

    public CountIntervals() {
        ts = new TreeMap<>();
    }

    public void add(int left, int right) {
        ts.tailSet(left, right)
    }

    public int count() {

    }

    private static class RangeEntry implements Comparable<RangeEntry> {
        int left;
        int right;

        @Override
        public int compareTo(RangeEntry o) {
            if (left != o.left) {
                return left - o.left;
            } else {
                return right - o.right;
            }
        }
    }
}
