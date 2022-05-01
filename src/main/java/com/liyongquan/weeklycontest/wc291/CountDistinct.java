package com.liyongquan.weeklycontest.wc291;

import java.util.HashSet;
import java.util.Set;

public class CountDistinct {
    /**
     * 双指针是好，但是不能去重。其实这一题是直接暴力解就好了
     *
     * @param nums
     * @param k
     * @param p
     * @return
     */
    public int countDistinct(int[] nums, int k, int p) {
        int len = nums.length;
        int l = 0, r = 0;
        int cnt = 0;
        int res = 0;
        while (r < len) {
            while (r < len && cnt <= k) {
                if (nums[r] % p == 0) {
                    cnt++;
                    if (cnt > k) {
                        break;
                    }
                }
                res += r - l + 1;
                r++;
            }
            if (r == len) {
                return res;
            }
            while (l < r && cnt > k) {
                if (nums[l] % p == 0) {
                    cnt--;
                    res += r - l + 1;
                    r++;
                }
                l++;
            }
        }
        return res;
    }

    public int countDistinct2(int[] nums, int k, int p) {
        int len = nums.length;
        Set<String> set = new HashSet<>();
        int res = 0;
        for (int i = 0; i < len; i++) {
            int cnt = 0;
            StringBuilder sb = new StringBuilder();
            for (int j = i; j < len; j++) {
                if (nums[j] % p == 0) {
                    cnt++;
                    if (cnt > k) {
                        break;
                    }
                }
                sb.append(nums[j]);
                sb.append('#');
                if (!set.contains(sb.toString())) {
                    set.add(sb.toString());
                    res++;
                }
            }
        }
        return res;
    }
}
