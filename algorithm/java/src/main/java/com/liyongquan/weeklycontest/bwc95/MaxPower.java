package com.liyongquan.weeklycontest.bwc95;

public class MaxPower {
    public long maxPower(int[] stations, int r, int k) {
        int left = 0;
        int right = Integer.MAX_VALUE - 1;
        while (left < right) {
            int mid = left + (right - left + 1) / 2;
            int[] clone = new int[stations.length];
            for (int i = 0; i < stations.length; i++) {
                clone[i] = stations[i];
            }
            if (check(clone, r, k, mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private boolean check(int[] stations, int r, int k, int mx) {
        int n = stations.length;
        for (int i = 0; i < n; i++) {
            int s = 0;
            int right = Math.min(n - 1, i + r);
            for (int j = Math.max(0, i - r); j <= right; j++) {
                s += stations[j];
            }
            if (s < mx) {
                int diff = mx - s;
                if (diff > k) {
                    return false;
                }
                stations[right] += diff;
                k -= diff;
            }
        }
        return true;
    }
}
