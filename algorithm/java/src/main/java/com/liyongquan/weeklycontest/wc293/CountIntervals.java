package com.liyongquan.weeklycontest.wc293;

import java.util.*;

/**
 * 居然过了
 */
public class CountIntervals {
    TreeMap<Integer, RangeEntry> ts;
    int cnt = 0;

    public CountIntervals() {
        ts = new TreeMap<>();
    }

    public void add(int left, int right) {
        //range.right>=left
        SortedMap<Integer, RangeEntry> subMap = ts.tailMap(left);
        List<Integer> removeKey = new ArrayList<>();
        int l = left, r = right;
        for (Map.Entry<Integer, RangeEntry> entry : subMap.entrySet()) {
            RangeEntry rangeEntry = entry.getValue();
            if (rangeEntry.left > r) {
                break;
            }
            //range.left<=right&&range.right>=left
            //有交集
            removeKey.add(entry.getKey());
            cnt -= rangeEntry.right - rangeEntry.left + 1;
            l = Math.min(rangeEntry.left, l);
            r = Math.max(rangeEntry.right, r);
        }
        for (Integer key : removeKey) {
            ts.remove(key);
        }
        ts.put(r, new RangeEntry(l, r));
        cnt += r - l + 1;
    }

    public int count() {
        return cnt;
    }

    private static class RangeEntry {
        int left;
        int right;

        public RangeEntry(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }
}
