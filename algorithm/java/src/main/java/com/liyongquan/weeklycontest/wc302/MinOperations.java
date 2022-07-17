package com.liyongquan.weeklycontest.wc302;

import java.util.Arrays;

public class MinOperations {
    public int minOperations(int[] nums, int[] numsDivide) {
        Arrays.sort(nums);
        int mx = numsDivide[0];
        for (int i = 1; i < numsDivide.length; i++) {
            mx = gcd(mx, numsDivide[i]);
        }
        for (int i = 0; i < nums.length; i++) {
            if (mx % nums[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    private int gcd(int a, int b) {
        if (a < b) {
            int temp = a;
            a = b;
            b = temp;
        }
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}
