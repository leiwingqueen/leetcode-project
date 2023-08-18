package com.liyongquan.greedy;

// 给你一个披萨，它由 3n 块不同大小的部分组成，现在你和你的朋友们需要按照如下规则来分披萨：
//
//你挑选 任意 一块披萨。
//Alice 将会挑选你所选择的披萨逆时针方向的下一块披萨。
//Bob 将会挑选你所选择的披萨顺时针方向的下一块披萨。
//重复上述过程直到没有披萨剩下。
//每一块披萨的大小按顺时针方向由循环数组 slices 表示。
//
//请你返回你可以获得的披萨大小总和的最大值。
//
//
//
//示例 1：
//
//
//
//输入：slices = [1,2,3,4,5,6]
//输出：10
//解释：选择大小为 4 的披萨，Alice 和 Bob 分别挑选大小为 3 和 5 的披萨。然后你选择大小为 6 的披萨，Alice 和 Bob 分别挑选大小为 2 和 1 的披萨。你获得的披萨总大小为 4 + 6 = 10 。
//示例 2：
//
//
//
//输入：slices = [8,9,8,6,1,1]
//输出：16
//解释：两轮都选大小为 8 的披萨。如果你选择大小为 9 的披萨，你的朋友们就会选择大小为 8 的披萨，这种情况下你的总和不是最大的。
//
//
//提示：
//
//1 <= slices.length <= 500
//slices.length % 3 == 0
//1 <= slices[i] <= 1000

import java.util.PriorityQueue;

public class MaxSizeSlices {
    // 贪心，
    public int maxSizeSlices(int[] slices) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> slices[o2] - slices[o1]);
        int n = slices.length;
        boolean[] choose = new boolean[n];
        for (int i = 0; i < n; i++) {
            pq.add(i);
        }
        int res = 0;
        while (pq.size() > 0) {
            while (pq.size() > 0 && choose[pq.peek()]) {
                pq.poll();
            }
            if (pq.size() > 0) {
                Integer idx = pq.poll();
                res += slices[idx];
                choose[idx] = true;
                // 分别找到逆时针和顺时针的第一个
                int l = (idx + n - 1) % n;
                while (choose[l]) {
                    l = (l + n - 1) % n;
                }
                choose[l] = true;
                int r = (idx + 1) % n;
                while (choose[r]) {
                    r = (r + 1) % n;
                }
                choose[r] = true;
            }
        }
        return res;
    }
}
