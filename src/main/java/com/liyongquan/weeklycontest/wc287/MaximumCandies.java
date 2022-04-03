package com.liyongquan.weeklycontest.wc287;

public class MaximumCandies {
    public int maximumCandies(int[] candies, long k) {
        long sum = 0;
        for (int candy : candies) {
            sum += candy;
        }
        if (sum < k) {
            return 0;
        }
        int l = 1;
        int r = (int) (sum / k);
        while (l < r) {
            int mid = l + (r - l + 1) / 2;
            if (check(candies, k, mid)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }

    private boolean check(int[] candies, long k, int m) {
        long cnt = 0;
        for (int candy : candies) {
            cnt += candy / m;
        }
        return cnt >= k;
    }
}
