package com.liyongquan.weeklycontest.wc281;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public static final int mx = 100_000;
    public static final List<Integer>[] divisors = new List[mx + 1];
    static {
        for (int i = 0; i <= mx; i++) {
            divisors[i] = new ArrayList<>();
        }
        //遍历每个因子
        for (int i = 1; i <= mx; i++) {
            //遍历因子的倍数
            for (int j = i; j <= mx; j += i) {
                divisors[j].add(i);
            }
        }
    }

    public long coutPairs2(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        long cnt = 0;
        for (int i = 0; i < nums.length; i++) {
            int gcd = gcd(nums[i], k);
            cnt += map.getOrDefault(k / gcd, 0);
            //更新num[i]的所有因子的出现次数
            for (Integer div : divisors[nums[i]]) {
                map.put(div, map.getOrDefault(div, 0) + 1);
            }
        }
        return cnt;
    }

    private int gcd(int a, int b) {
        if (a == 0) {
            return 1;
        }
        return b == 0 ? a : gcd(b, a % b);
    }
}
