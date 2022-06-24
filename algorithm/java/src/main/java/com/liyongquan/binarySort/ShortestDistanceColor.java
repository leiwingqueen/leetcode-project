package com.liyongquan.binarySort;

import java.util.ArrayList;
import java.util.List;

//给你一个数组 colors，里面有 1、2、 3 三种颜色。
//
// 我们需要在 colors 上进行一些查询操作 queries，其中每个待查项都由两个整数 i 和 c 组成。
//
// 现在请你帮忙设计一个算法，查找从索引 i 到具有目标颜色 c 的元素之间的最短距离。
//
// 如果不存在解决方案，请返回 -1。
//
//
//
// 示例 1：
//
// 输入：colors = [1,1,2,1,3,2,2,3,3], queries = [[1,3],[2,2],[6,1]]
//输出：[3,0,3]
//解释：
//距离索引 1 最近的颜色 3 位于索引 4（距离为 3）。
//距离索引 2 最近的颜色 2 就是它自己（距离为 0）。
//距离索引 6 最近的颜色 1 位于索引 3（距离为 3）。
//
//
// 示例 2：
//
// 输入：colors = [1,2], queries = [[0,3]]
//输出：[-1]
//解释：colors 中没有颜色 3。
//
//
//
//
// 提示：
//
//
// 1 <= colors.length <= 5*10^4
// 1 <= colors[i] <= 3
// 1 <= queries.length <= 5*10^4
// queries[i].length == 2
// 0 <= queries[i][0] < colors.length
// 1 <= queries[i][1] <= 3
//
// Related Topics 数组 二分查找 动态规划 👍 30 👎 0

public class ShortestDistanceColor {
    /**
     * 这个确实比较容易想，为每个数字维护一个下标的列表，通过二分找到>=该下标的第一个下标
     *
     * @param colors
     * @param queries
     * @return
     */
    public List<Integer> shortestDistanceColor(int[] colors, int[][] queries) {
        List<Integer> c1 = new ArrayList<>(colors.length),
                c2 = new ArrayList<>(colors.length),
                c3 = new ArrayList<>(colors.length);
        for (int i = 0; i < colors.length; i++) {
            if (colors[i] == 1) {
                c1.add(i);
            } else if (colors[i] == 2) {
                c2.add(i);
            } else {
                c3.add(i);
            }
        }
        List<Integer> res = new ArrayList<>(queries.length);
        for (int i = 0; i < queries.length; i++) {
            int idx = queries[i][0];
            int next = queries[i][1];
            List<Integer> arr;
            if (next == 1) {
                arr = c1;
            } else if (next == 2) {
                arr = c2;
            } else {
                arr = c3;
            }
            int left = searchLeft(arr, idx);
            int right = searchRight(arr, idx);
            int r = -1;
            if (left >= 0) {
                r = left;
            }
            if (right >= 0 && (r < 0 || right < r)) {
                r = right;
            }
            res.add(r);
        }
        return res;
    }

    private int searchRight(List<Integer> arr, int target) {
        if (arr.size() == 0) {
            return -1;
        }
        int l = 0, r = arr.size() - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (arr.get(mid) >= target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l < arr.size() ? (arr.get(l) - target) : -1;
    }

    private int searchLeft(List<Integer> arr, int target) {
        if (arr.size() == 0) {
            return -1;
        }
        int l = 0, r = arr.size() - 1;
        while (l < r) {
            int mid = l + (r - l + 1) / 2;
            if (arr.get(mid) <= target) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l >= 0 ? (target - arr.get(l)) : -1;
    }
}
