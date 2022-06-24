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
        long[] preSum = new long[idx + 1];
        for (int i = 1; i <= idx; i++) {
            preSum[i] = preSum[i - 1] + flowers[i - 1];
        }
        for (int i = 0; i <= idx; i++) {
            long p = (long) i * full;
            //不完善花园的数量
            int q = idx - i;
            //二分查找找到不完善花园的最大值
            l = 1;
            r = target - 1;
            while (l < r) {
                int mid = l + (r - l + 1) / 2;
                if (need(flowers, q, mid, preSum) <= newFlowers) {
                    l = mid;
                } else {
                    r = mid - 1;
                }
            }
            if (q > 0) {
                p += (long) l * partial;
            }
            res = Math.max(res, p);
            //更新下一轮迭代
            if (i < idx) {
                int next = flowers[idx - i - 1];
                if (newFlowers < target - next) {
                    break;
                }
                newFlowers -= target - next;
            }
        }
        return res + (len - idx) * full;
    }

    /**
     * 保证前n个都必须>=k的最小需要的数量
     *
     * @param arr
     * @param n
     * @param k
     * @return
     */
    private long need(int[] arr, int n, int k, long[] preSum) {
        if (arr[0] >= k) {
            return 0;
        }
        //找到<k的分界点
        int l = 0, r = n - 1;
        while (l < r) {
            int mid = l + (r - l + 1) / 2;
            if (arr[mid] < k) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        //统计[0,l]的和
        int len = l + 1;
        return (long) len * k - preSum[len];
    }

}
