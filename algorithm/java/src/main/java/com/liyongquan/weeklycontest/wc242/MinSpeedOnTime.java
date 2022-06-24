package com.liyongquan.weeklycontest.wc242;

public class MinSpeedOnTime {
    /**
     * 二分查找
     *
     * 不通过
     *
     * @param dist
     * @param hour
     * @return
     */
    public int minSpeedOnTime(int[] dist, double hour) {
        int len = dist.length;
        if (hour <= len - 1) {
            return -1;
        }
        if (hour == len) {
            int max = 0;
            for (int d : dist) {
                max = Math.max(max, d);
            }
            return max;
        }
        if (hour < len) {
            int max = 0;
            for (int i = 0; i < len - 1; i++) {
                max = Math.max(max, dist[i]);
            }
            int last = (int) (dist[len - 1] / (hour - len - 1)) + 1;
            return Math.max(last, max);
        }
        int sum = 0;
        for (int d : dist) {
            sum += d;
        }
        int r = (int) (sum / (hour - len)) + 1;
        int l = 1;
        //二分查找
        while (l < r) {
            int mid = l + (r - l) / 2;
            double total = 0;
            for (int i = 0; i < len - 1; i++) {
                if (dist[i] % mid == 0) {
                    total += dist[i] / mid;
                } else {
                    total += dist[i] / mid + 1;
                }
            }
            total += (double) dist[len - 1] / mid;
            if (total > hour) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }
}
