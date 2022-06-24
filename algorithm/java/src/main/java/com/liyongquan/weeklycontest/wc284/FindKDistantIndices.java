package com.liyongquan.weeklycontest.wc284;

import java.util.ArrayList;
import java.util.List;

public class FindKDistantIndices {
    public List<Integer> findKDistantIndices(int[] nums, int key, int k) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int l = i;
            boolean flag = false;
            while (l >= 0 && i - l <= k) {
                if (nums[l--] == key) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                res.add(i);
            } else {
                int r = i + 1;
                while (r < nums.length && r - i <= k) {
                    if (nums[r++] == key) {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    res.add(i);
                }
            }
        }
        return res;
    }
}
