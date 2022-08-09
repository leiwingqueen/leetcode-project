package com.liyongquan.weeklycontest.wc305;

import java.util.HashSet;
import java.util.Set;

public class ArithmeticTriplets {
    public int arithmeticTriplets(int[] nums, int diff) {
        int n = nums.length;
        int res = 0;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        for (int i = 0; i <= n - 3; i++) {
            int num = nums[i];
            if (set.contains(num + diff) && set.contains(num + 2 * diff)) {
                res++;
            }
        }
        return res;
    }
}
