package com.liyongquan.weeklycontest.wc241;

import java.util.HashMap;
import java.util.Map;

public class FindSumPairs {
    private Map<Integer, Integer> m1;
    private Map<Integer, Integer> m2;
    private int[] nums2;

    public FindSumPairs(int[] nums1, int[] nums2) {
        m1 = new HashMap<>();
        m2 = new HashMap<>();
        for (int num : nums1) {
            m1.put(num, m1.getOrDefault(num, 0) + 1);
        }
        for (int num : nums2) {
            m2.put(num, m2.getOrDefault(num, 0) + 1);
        }
        this.nums2 = nums2;
    }

    public void add(int index, int val) {
        int num = nums2[index];
        nums2[index] += val;
        m2.put(num, m2.get(num) - 1);
        m2.put(nums2[index], m2.getOrDefault(nums2[index], 0) + 1);
    }

    /**
     * 时间复杂度O(m)
     * <p>
     * m是num1的长度,n是num2的长度
     *
     * @param tot
     * @return
     */
    public int count(int tot) {
        int res = 0;
        for (Map.Entry<Integer, Integer> entry : m1.entrySet()) {
            if (entry.getValue() > 0) {
                Integer n1 = entry.getKey();
                int n2 = tot - n1;
                Integer cnt = m2.getOrDefault(n2, 0);
                res += cnt * entry.getValue();
            }
        }
        return res;
    }
}
