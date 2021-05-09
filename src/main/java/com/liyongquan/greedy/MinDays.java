package com.liyongquan.greedy;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * 1482. 制作 m 束花所需的最少天数
 * <p>
 * 给你一个整数数组 bloomDay，以及两个整数 m 和 k 。
 * <p>
 * 现需要制作 m 束花。制作花束时，需要使用花园中 相邻的 k 朵花 。
 * <p>
 * 花园中有 n 朵花，第 i 朵花会在 bloomDay[i] 时盛开，恰好 可以用于 一束 花中。
 * <p>
 * 请你返回从花园中摘 m 束花需要等待的最少的天数。如果不能摘到 m 束花则返回 -1 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：bloomDay = [1,10,3,10,2], m = 3, k = 1
 * 输出：3
 * 解释：让我们一起观察这三天的花开过程，x 表示花开，而 _ 表示花还未开。
 * 现在需要制作 3 束花，每束只需要 1 朵。
 * 1 天后：[x, _, _, _, _]   // 只能制作 1 束花
 * 2 天后：[x, _, _, _, x]   // 只能制作 2 束花
 * 3 天后：[x, _, x, _, x]   // 可以制作 3 束花，答案为 3
 * 示例 2：
 * <p>
 * 输入：bloomDay = [1,10,3,10,2], m = 3, k = 2
 * 输出：-1
 * 解释：要制作 3 束花，每束需要 2 朵花，也就是一共需要 6 朵花。而花园中只有 5 朵花，无法满足制作要求，返回 -1 。
 * 示例 3：
 * <p>
 * 输入：bloomDay = [7,7,7,7,12,7,7], m = 2, k = 3
 * 输出：12
 * 解释：要制作 2 束花，每束需要 3 朵。
 * 花园在 7 天后和 12 天后的情况如下：
 * 7 天后：[x, x, x, x, _, x, x]
 * 可以用前 3 朵盛开的花制作第一束花。但不能使用后 3 朵盛开的花，因为它们不相邻。
 * 12 天后：[x, x, x, x, x, x, x]
 * 显然，我们可以用不同的方式制作两束花。
 * 示例 4：
 * <p>
 * 输入：bloomDay = [1000000000,1000000000], m = 1, k = 1
 * 输出：1000000000
 * 解释：需要等 1000000000 天才能采到花来制作花束
 * 示例 5：
 * <p>
 * 输入：bloomDay = [1,10,2,9,3,8,4,7,5,6], m = 4, k = 2
 * 输出：9
 *  
 * <p>
 * 提示：
 * <p>
 * bloomDay.length == n
 * 1 <= n <= 10^5
 * 1 <= bloomDay[i] <= 10^9
 * 1 <= m <= 10^6
 * 1 <= k <= n
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-number-of-days-to-make-m-bouquets
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MinDays {
    /**
     * 先尝试简单的暴力
     * <p>
     * <p>
     * 时间复杂度
     * 假设不同的天数为l
     * O(l*n)
     * <p>
     * 超时
     *
     * @param bloomDay
     * @param m
     * @param k
     * @return
     */
    public int minDays(int[] bloomDay, int m, int k) {
        int len = bloomDay.length;
        if (m * k > len) {
            return -1;
        }
        //盛开的天数
        TreeSet<Integer> set = new TreeSet<>();
        for (int b : bloomDay) {
            set.add(b);
        }
        //穷举所有的天数
        for (Integer day : set) {
            //更新盛开的花的序列
            int[] bloom = new int[len];
            for (int i = 0; i < len; i++) {
                if (bloomDay[i] <= day) {
                    bloom[i] = 1;
                }
            }
            //判断能否满足
            int cnt = 0;
            int idx = 0;
            while (idx < len) {
                while (idx < len && bloom[idx] != 1) {
                    idx++;
                }
                int start = idx;
                while (idx < len && bloom[idx] == 1) {
                    idx++;
                    if (idx - start == k) {
                        cnt++;
                        break;
                    }
                }
            }
            if (cnt >= m) {
                return day;
            }
        }
        return -1;
    }

    /**
     * 其实这里解的范围已经确定了，我们可以尝试用二分
     *
     * @param bloomDay
     * @param m
     * @param k
     * @return
     */
    public int minDays2(int[] bloomDay, int m, int k) {
        int len = bloomDay.length;
        if (m * k > len) {
            return -1;
        }
        //盛开的天数
        TreeSet<Integer> set = new TreeSet<>();
        for (int b : bloomDay) {
            set.add(b);
        }
        int[] dayArr = new int[set.size()];
        int j = 0;
        for (Integer day : set) {
            dayArr[j++] = day;
        }
        //二分查找
        int l = 0, r = dayArr.length - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            int day = dayArr[mid];
            //更新盛开的花的序列
            int[] bloom = new int[len];
            for (int i = 0; i < len; i++) {
                if (bloomDay[i] <= day) {
                    bloom[i] = 1;
                }
            }
            //判断能否满足
            int cnt = 0;
            int idx = 0;
            while (idx < len) {
                while (idx < len && bloom[idx] != 1) {
                    idx++;
                }
                int start = idx;
                while (idx < len && bloom[idx] == 1) {
                    idx++;
                    if (idx - start == k) {
                        cnt++;
                        break;
                    }
                }
            }
            if (cnt >= m) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return dayArr[l];
    }
}
