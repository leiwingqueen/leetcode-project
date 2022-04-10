package com.liyongquan.weeklycontest.wc288;

import java.util.Arrays;

public class MaximumBeauty {
    public long maximumBeauty(int[] flowers, long newFlowers, int target, int full, int partial) {
        Arrays.sort(flowers);
        int len = flowers.length;
        if (flowers[0] >= target) {
            return (long) full * len;
        }
        //找到最后一个非完善的花园的下标
        int l = 0, r = len - 1;
        while (l < r) {
            int mid = l + (r - l + 1) / 2;
            if (flowers[mid] < target) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        int idx = l + 1;
        //[0,idx)是需要重新计算的部分，[idx,len)原本已经满足条件的完善花园，不需要计算
        //遍历所有完善花园的数量
        long res = 0;
        //不完善花园的总数
        long sum = 0;
        for (int i = 0; i < idx; i++) {
            sum += flowers[i];
        }
        for (int i = 0; i <= idx; i++) {
            int p = i * full;
            //不完善花园的数量
            int q = idx - i;
            //二分查找找到不完善花园的最大值
            l = 1;
            r = target - 1;
            while (l < r) {
                int mid = l + (r - l + 1) / 2;
                if ((long) mid * q - sum <= newFlowers) {
                    l = mid;
                } else {
                    r = mid - 1;
                }
            }
            if (q > 0) {
                p += l * partial;
            }
            res = Math.max(res, p);
            //更新下一轮迭代
            if (i < idx) {
                int next = flowers[idx - i - 1];
                if (newFlowers < target - next) {
                    break;
                }
                sum -= next;
                newFlowers -= target - next;
            }
        }
        return res + (len - idx) * full;
    }

}
