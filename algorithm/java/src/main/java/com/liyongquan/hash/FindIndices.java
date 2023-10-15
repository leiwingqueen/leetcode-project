package com.liyongquan.hash;

import com.liyongquan.heap.HalveArray;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class FindIndices {
    public int[] findIndices(int[] nums, int indexDifference, int valueDifference) {
        int n = nums.length;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = indexDifference; i < n; i++) {
            // 更新map
            if (!map.containsKey(nums[i - indexDifference])) {
                map.put(nums[i - indexDifference], i - indexDifference);
            }
            Integer k1 = map.floorKey(nums[i] - valueDifference);
            if (k1 != null) {
                return new int[]{map.get(k1), i};
            }
            Integer k2 = map.floorKey(nums[i] + valueDifference);
            if (k2 != null) {
                return new int[]{map.get(k2), i};
            }
        }
        return new int[]{-1, -1};
    }
}
