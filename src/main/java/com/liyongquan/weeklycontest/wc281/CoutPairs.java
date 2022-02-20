package com.liyongquan.weeklycontest.wc281;

import java.util.ArrayList;
import java.util.List;

public class CoutPairs {
    /**
     * 暴力解法
     * <p>
     * 超时
     *
     * @param nums
     * @param k
     * @return
     */
    public long coutPairs(int[] nums, int k) {
        long cnt = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] % k == 0) {
                cnt += nums.length - i - 1;
            } else {
                for (int j = i + 1; j < nums.length; j++) {
                    if (((long) nums[j] * nums[i]) % k == 0) {
                        cnt++;
                    }
                }
            }
        }
        return cnt;
    }

    private List<int[]> split(int k) {
        List<int[]> list = new ArrayList<>();
        for (int i = 1; i <= k; i++) {
            if (k % i == 0) {
                list.add(new int[]{i, k / i});
            }
        }
        return list;
    }
}
