package com.liyongquan.weeklycontest.wc283;

import java.util.Arrays;

public class MinimalKSum {
    public long minimalKSum(int[] nums, int k) {
        Arrays.sort(nums);
        int len = nums.length;
        int pre = 0;
        long sum = 0;
        int idx = 0;
        while (idx < len && k > 0) {
            int n = nums[idx] - pre - 1;
            if (n > 0) {
                int add = Math.min(n, k);
                //[pre+1,pre+add]
                sum += (long) (pre + 1 + pre + add) * add / 2;
                k -= n;
            }
            pre = nums[idx];
            idx++;
        }
        if (k > 0) {
            sum += (long) (pre + 1 + pre + k) * k / 2;
        }
        return sum;
    }
}
