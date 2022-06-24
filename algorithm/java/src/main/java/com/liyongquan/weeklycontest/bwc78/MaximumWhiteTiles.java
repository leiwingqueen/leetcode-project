package com.liyongquan.weeklycontest.bwc78;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MaximumWhiteTiles {
    public int maximumWhiteTiles(int[][] tiles, int carpetLen) {
        List<int[]> ranges = Arrays.stream(tiles).sorted(Comparator.comparingInt(o -> o[0])).collect(Collectors.toList());
        int[] prefixSum = new int[ranges.size() + 1];
        for (int i = 0; i < ranges.size(); i++) {
            int[] range = ranges.get(i);
            prefixSum[i + 1] = prefixSum[i] + range[1] - range[0] + 1;
        }
        int res = 0;
        for (int i = 0; i < ranges.size(); i++) {
            int[] range = ranges.get(i);
            int edge = range[0] + carpetLen - 1;
            int idx = search(ranges, edge);
            //前idx个总和,然后再加上最后一个区间
            int r = prefixSum[idx] - prefixSum[i];
            int[] last = ranges.get(idx);
            r += Math.min(edge - last[0] + 1, last[1] - last[0] + 1);
            res = Math.max(res, r);
        }
        return res;
    }

    private int search(List<int[]> ranges, int target) {
        int l = 0, r = ranges.size() - 1;
        while (l < r) {
            int mid = l + (r - l + 1) / 2;
            int[] range = ranges.get(mid);
            if (target >= range[0]) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }
}
