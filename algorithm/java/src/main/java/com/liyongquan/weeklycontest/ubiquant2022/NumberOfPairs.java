package com.liyongquan.weeklycontest.ubiquant2022;

import java.util.HashMap;
import java.util.Map;

public class NumberOfPairs {
    public int numberOfPairs(int[] nums) {
        int mod = 1_000_000_007;
        Map<Integer, Integer> map = new HashMap<>();
        int cnt = 0;
        for (int num : nums) {
            int key = num - revert(num);
            map.put(key, map.getOrDefault(key, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer value = entry.getValue();
            if (value >= 2) {
                cnt = (int) ((cnt + (long) value * (value - 1) / 2) % mod);
            }
        }
        return cnt;
    }

    private int revert(int num) {
        int r = 0;
        while (num > 0) {
            r = r * 10 + num % 10;
            num /= 10;
        }
        return r;
    }
}
