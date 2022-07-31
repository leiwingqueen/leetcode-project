package com.liyongquan.weeklycontest.wc304;

import java.util.Arrays;

public class MaximumGroups {
    public int maximumGroups(int[] grades) {
        Arrays.sort(grades);
        int res = 0;
        int range = 0;
        int l = 0, r = 0;
        int preSum = 0;
        while (r < grades.length) {
            int sum = 0;
            while (r < grades.length && (sum <= preSum || r - l <= range)) {
                sum += grades[r];
                r++;
            }
            if (r - l <= range) {
                return res;
            }
            res++;
            preSum = sum;
            range = r - l;
            l = r;
        }
        return res;
    }
}
