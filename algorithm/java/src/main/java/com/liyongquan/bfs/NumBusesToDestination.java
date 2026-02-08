package com.liyongquan.bfs;

//给你一个数组 routes ，表示一系列公交线路，其中每个 routes[i] 表示一条公交线路，第 i 辆公交车将会在上面循环行驶。
//
//
// 例如，路线 routes[0] = [1, 5, 7] 表示第 0 辆公交车会一直按序列 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1
//-> ... 这样的车站路线行驶。
//
//
// 现在从 source 车站出发（初始时不在公交车上），要前往 target 车站。 期间仅可乘坐公交车。
//
// 求出 最少乘坐的公交车数量 。如果不可能到达终点车站，返回 -1 。
//
//
//
// 示例 1：
//
//
//输入：routes = [[1,2,7],[3,6,7]], source = 1, target = 6
//输出：2
//解释：最优策略是先乘坐第一辆公交车到达车站 7 , 然后换乘第二辆公交车到车站 6 。
//
//
// 示例 2：
//
//
//输入：routes = [[7,12],[4,5,15],[6],[15,19],[9,12,13]], source = 15, target = 12
//输出：-1
//
//
//
//
// 提示：
//
//
// 1 <= routes.length <= 500.
// 1 <= routes[i].length <= 105
// routes[i] 中的所有值 互不相同
// sum(routes[i].length) <= 105
// 0 <= routes[i][j] < 106
// 0 <= source, target < 106
//
// Related Topics 广度优先搜索 数组 哈希表
// 👍 167 👎 0


import java.util.*;

public class NumBusesToDestination {

    private static class Pair {
        int key;
        int value;
        Pair(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) {
            return 0;
        }
        int len = routes.length;
        Set<Integer>[] arr = new Set[len];
        //包含起点/重点的线路
        List<Integer> sourceIdx = new ArrayList<>(), targetIdx = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            arr[i] = new HashSet<>();
            for (int j = 0; j < routes[i].length; j++) {
                arr[i].add(routes[i][j]);
            }
            if (arr[i].contains(source)) {
                sourceIdx.add(i);
            }
            if (arr[i].contains(target)) {
                targetIdx.add(i);
            }
        }
        if (sourceIdx.size() == 0 || targetIdx.size() == 0) {
            return -1;
        }
        //构造图，如果两条线路有交集，则认为两个点之间有一条边
        int[][] edges = new int[len][len];
        for (int i = 0; i < len - 1; i++) {
            for (int j = 1; j < len; j++) {
                if (check(arr[i], arr[j])) {
                    edges[i][j] = 1;
                    edges[j][i] = 1;
                }
            }
        }
        //bfs查找最短路径
        int min = Integer.MAX_VALUE;
        for (Integer src : sourceIdx) {
            int r = bfs(src, target, arr, len, edges);
            if (r > 0) {
                min = Math.min(min, r);
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    private int bfs(int sourceIdx, int target, Set<Integer>[] arr, int len, int[][] edges) {
        Queue<Pair> queue = new LinkedList<>();
        if (arr[sourceIdx].contains(target)) {
            return 1;
        }
        queue.add(new Pair(sourceIdx, 1));
        int[] visit = new int[len];
        visit[sourceIdx] = 1;
        while (!queue.isEmpty()) {
            Pair poll = queue.poll();
            int idx = poll.key;
            int depth = poll.value;
            //找下一个关联点
            for (int i = 0; i < len; i++) {
                if (i == idx) {
                    continue;
                }
                if (edges[idx][i] == 1 && visit[i] == 0) {
                    if (arr[i].contains(target)) {
                        return depth + 1;
                    }
                    queue.add(new Pair(i, depth + 1));
                    visit[i] = 1;
                }
            }
        }
        return -1;
    }

    private boolean check(Set<Integer> s1, Set<Integer> s2) {
        for (Integer r : s1) {
            if (s2.contains(r)) {
                return true;
            }
        }
        return false;
    }
}
