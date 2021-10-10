package com.liyongquan.array;

import java.util.*;

/**
 * @author liyongquan
 * @date 2021/10/10
 */
public class TwoOutOfThree {
    public List<Integer> twoOutOfThree(int[] nums1, int[] nums2, int[] nums3) {
        Map<Integer, Integer> map = new HashMap<>();
        scan(nums1, map);
        scan(nums2, map);
        scan(nums3, map);
        List<Integer> res = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() >= 2) {
                res.add(entry.getKey());
            }
        }
        return res;
    }

    private void scan(int[] nums, Map<Integer, Integer> map) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        for (Integer num : set) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
    }
}
