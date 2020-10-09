package com.liyongquan.graph;

import java.util.*;

/**
 * 节点间通路。给定有向图，设计一个算法，找出两个节点之间是否存在一条路径。
 * <p>
 * 示例1:
 * <p>
 * 输入：n = 3, graph = [[0, 1], [0, 2], [1, 2], [1, 2]], start = 0, target = 2
 * 输出：true
 * 示例2:
 * <p>
 * 输入：n = 5, graph = [[0, 1], [0, 2], [0, 4], [0, 4], [0, 1], [1, 3], [1, 4], [1, 3], [2, 3], [3, 4]], start = 0, target = 4
 * 输出 true
 * 提示：
 * <p>
 * 节点数量n在[0, 1e5]范围内。
 * 节点编号大于等于 0 小于 n。
 * 图中可能存在自环和平行边。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/route-between-nodes-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RouteBetweenNodes {
    /**
     * bfs，时间复杂度O(n)，n为节点数量
     *
     * @param n
     * @param graph
     * @param start
     * @param target
     * @return
     */
    public boolean findWhetherExistsPath(int n, int[][] graph, int start, int target) {
        if (n <= 0) {
            return false;
        }
        if (start == target) {
            return true;
        }
        Map<Integer, Set<Integer>> graphMap = new HashMap<>(graph.length);
        for (int[] g : graph) {
            Set<Integer> set = graphMap.getOrDefault(g[0], new HashSet<>());
            set.add(g[1]);
            graphMap.put(g[0], set);
        }
        Deque<Integer> queue = new LinkedList<>();
        int[] visit = new int[n];
        visit[start] = 1;
        queue.add(start);
        while (!queue.isEmpty()) {
            Integer node = queue.poll();
            Set<Integer> set = graphMap.getOrDefault(node, new HashSet<>());
            for (Integer to : set) {
                if (visit[to] == 1) {
                    continue;
                }
                if (to == target) {
                    return true;
                }
                visit[to] = 1;
                queue.add(to);
            }
        }
        return false;
    }

    /**
     * dfs解法
     *
     * @param n
     * @param graph
     * @param start
     * @param target
     * @return
     */
    public boolean findWhetherExistsPath2(int n, int[][] graph, int start, int target) {
        if (n <= 0) {
            return false;
        }
        if (start == target) {
            return true;
        }
        Map<Integer, Set<Integer>> graphMap = new HashMap<>(graph.length);
        for (int[] g : graph) {
            Set<Integer> set = graphMap.getOrDefault(g[0], new HashSet<>());
            set.add(g[1]);
            graphMap.put(g[0], set);
        }
        int[] visit = new int[n];
        return dfs(graphMap, start, target, visit);
    }

    private boolean dfs(Map<Integer, Set<Integer>> graphMap, int start, int target, int[] visit) {
        if (start == target) {
            return true;
        }
        visit[start] = 1;
        Set<Integer> neighbors = graphMap.getOrDefault(start, new HashSet<>());
        for (Integer neighbor : neighbors) {
            if (neighbor == target) {
                return true;
            }
            if (visit[neighbor] == 1) {
                continue;
            }
            if (dfs(graphMap, neighbor, target, visit)) {
                return true;
            }
        }
        return false;
    }
}
