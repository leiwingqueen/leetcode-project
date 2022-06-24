package com.liyongquan.bfs;

// 743. 网络延迟时间
// 有 n 个网络节点，标记为 1 到 n。
//
//给你一个列表 times，表示信号经过 有向 边的传递时间。 times[i] = (ui, vi, wi)，其中 ui 是源节点，vi 是目标节点， wi 是一个信号从源节点传递到目标节点的时间。
//
//现在，从某个节点 K 发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回 -1 。
//
// 
//
//示例 1：
//
//
//
//输入：times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
//输出：2
//示例 2：
//
//输入：times = [[1,2,1]], n = 2, k = 1
//输出：1
//示例 3：
//
//输入：times = [[1,2,1]], n = 2, k = 2
//输出：-1
// 
//
//提示：
//
//1 <= k <= n <= 100
//1 <= times.length <= 6000
//times[i].length == 3
//1 <= ui, vi <= n
//ui != vi
//0 <= wi <= 100
//所有 (ui, vi) 对都 互不相同（即，不含重复边）
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/network-delay-time
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import java.util.*;

/**
 * @author liyongquan
 * @date 2021/8/2
 */
public class NetworkDelayTime {
    /**
     * 带权有向图的最短路径
     * <p>
     * Dijkstra算法
     *
     * @param times
     * @param n
     * @param k
     * @return
     */
    public int networkDelayTime(int[][] times, int n, int k) {
        //构造有向图,key-起始点，value-[[目标点，权重],...]
        Map<Integer, List<int[]>> edges = new HashMap<>();
        for (int[] time : times) {
            int source = time[0];
            int target = time[1];
            int weight = time[2];
            if (!edges.containsKey(source)) {
                edges.put(source, new LinkedList<>());
            }
            edges.get(source).add(new int[]{target, weight});
        }
        //初始化
        int[] distance = new int[n];
        int[] book = new int[n];
        for (int i = 0; i < n; i++) {
            distance[i] = -1;
        }
        distance[k - 1] = 0;
        book[k - 1] = 1;
        List<int[]> edge = edges.getOrDefault(k, new LinkedList<>());
        for (int[] arr : edge) {
            int target = arr[0];
            int weight = arr[1];
            distance[target - 1] = weight;
        }
        //松弛过程
        int cnt = 1;
        int max = 0;
        while (cnt < n) {
            //找到距离最近的点
            int min = Integer.MAX_VALUE;
            int idx = 0;
            for (int i = 0; i < n; i++) {
                if (book[i] == 0 && distance[i] >= 0 && distance[i] < min) {
                    min = distance[i];
                    idx = i;
                }
            }
            if (min == Integer.MAX_VALUE) {
                return -1;
            }
            //更新相邻的点的距离
            cnt++;
            book[idx] = 1;
            max = distance[idx];
            List<int[]> list = edges.getOrDefault(idx + 1, new LinkedList<>());
            for (int[] arr : list) {
                int target = arr[0];
                int weight = arr[1];
                if (distance[target - 1] < 0 || distance[idx] + weight < distance[target - 1]) {
                    distance[target - 1] = distance[idx] + weight;
                }
            }
        }
        return max;
    }
}
