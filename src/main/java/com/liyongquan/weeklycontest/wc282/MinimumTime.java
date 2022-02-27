package com.liyongquan.weeklycontest.wc282;

public class MinimumTime {
    public long minimumTime(int[] time, int totalTrips) {
        long l = 1, r = Long.MAX_VALUE;
        while (l < r) {
            long mid = l + (r - l) / 2;
            if (check(time, totalTrips, mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    private boolean check(int[] time, int totalTrips, long t) {
        long total = 0;
        for (int i : time) {
            total += t / i;
            if (total >= totalTrips) {
                return true;
            }
        }
        return false;
    }
}
