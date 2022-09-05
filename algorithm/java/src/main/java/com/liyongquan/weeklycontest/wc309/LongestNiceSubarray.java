package com.liyongquan.weeklycontest.wc309;

public class LongestNiceSubarray {
    public int longestNiceSubarray(int[] nums) {
        //int[] cnt = new int[31];
        int l = 0, r = 0;
        int res = 1;
        int n = nums.length;
        int or = 0;
        while (r < n) {
            if (l == r || (nums[r] & or) == 0) {
                or |= nums[r];
                r++;
            } else {
                res = Math.max(res, r - l);
                or ^= nums[l];
                l++;
            }
        }
        res = Math.max(r - l, res);
        return res;
    }

    private int[] convert(int num) {
        int[] res = new int[31];
        for (int i = 0; i < 31; i++) {
            res[i] = (num & (1 << i)) != 0 ? 1 : 0;
        }
        return res;
    }

    private boolean check(int num, int[] cnt) {
        for (int i = 0; i < 31; i++) {
            if ((num & (1 << i)) != 0 && cnt[i] > 0) {
                return false;
            }
        }
        return true;
    }
}
