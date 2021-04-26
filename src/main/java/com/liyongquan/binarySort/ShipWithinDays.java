package com.liyongquan.binarySort;

public class ShipWithinDays {
    /**
     *
     * @param weights
     * @param D
     * @return
     */
    public int shipWithinDays(int[] weights, int D) {
        int len = weights.length;
        int max = 0, sum = 0;
        for (int i = 0; i < len; i++) {
            max = Math.max(max, weights[i]);
            sum += weights[i];
        }
        //求解的范围[max,sum]
        if (D == len) {
            return max;
        }
        if (D == 1) {
            return sum;
        }
        int l = max, r = sum;
        while (l < r) {
            int mid = l + (r - l) / 2;
            int day = shipDay(weights, mid);
            if (day > D) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }

    private int shipDay(int[] weights, int k) {
        int cnt = 1;
        int sum = 0;
        for (int i = 0; i < weights.length; i++) {
            sum += weights[i];
            if (sum > k) {
                cnt++;
                sum = weights[i];
            }
        }
        return cnt;
    }
}
