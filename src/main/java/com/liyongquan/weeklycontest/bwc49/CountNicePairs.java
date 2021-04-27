package com.liyongquan.weeklycontest.bwc49;

import java.util.HashMap;
import java.util.Map;

public class CountNicePairs {
    public int countNicePairs(int[] nums) {
        int len = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            int sub = nums[i] - rec(nums[i]);
            map.put(sub, map.getOrDefault(sub, 0) + 1);
        }
        long res = 0;
        int mod = 1000000007;
        for (Integer value : map.values()) {
            res += ((long) value * (value - 1) / 2);
            res %= mod;
        }
        return (int) res;
    }

    private int rec(int num) {
        int res = 0;
        while (num != 0) {
            res = res * 10 + num % 10;
            num /= 10;
        }
        return res;
    }
}
