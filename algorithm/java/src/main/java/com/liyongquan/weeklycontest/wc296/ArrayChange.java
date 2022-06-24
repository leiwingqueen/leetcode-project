package com.liyongquan.weeklycontest.wc296;

import java.util.HashMap;
import java.util.Map;

public class ArrayChange {
    public int[] arrayChange(int[] nums, int[][] operations) {
        Map<Integer, Integer> index = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            index.put(nums[i], i);
        }
        for (int[] op : operations) {
            int from = op[0];
            int to = op[1];
            Integer idx = index.get(from);
            nums[idx] = to;
            index.remove(from);
            index.put(to, idx);
        }
        return nums;
    }
}