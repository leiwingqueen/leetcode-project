package com.liyongquan.dp;

//有 n 个城市通过一些航班连接。给你一个数组 flights ，其中 flights[i] = [fromi, toi, pricei] ，表示该航班都从城市 fromi 开始，以价格 pricei 抵达 toi。
//
//现在给定所有的城市和航班，以及出发城市 src 和目的地 dst，你的任务是找到出一条最多经过 k 站中转的路线，使得从 src 到 dst 的 价格最便宜 ，并返回该价格。 如果不存在这样的路线，则输出 -1。
//
// 
//
//示例 1：
//
//输入:
//n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
//src = 0, dst = 2, k = 1
//输出: 200
//解释:
//城市航班图如下
//
//
//从城市 0 到城市 2 在 1 站中转以内的最便宜价格是 200，如图中红色所示。
//示例 2：
//
//输入:
//n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
//src = 0, dst = 2, k = 0
//输出: 500
//解释:
//城市航班图如下
//
//
//从城市 0 到城市 2 在 0 站中转以内的最便宜价格是 500，如图中蓝色所示。
// 
//
//提示：
//
//1 <= n <= 100
//0 <= flights.length <= (n * (n - 1) / 2)
//flights[i].length == 3
//0 <= fromi, toi < n
//fromi != toi
//1 <= pricei <= 104
//航班没有重复，且不存在自环
//0 <= src, dst, k < n
//src != dst
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/cheapest-flights-within-k-stops
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author liyongquan
 * @date 2021/8/24
 */
public class FindCheapestPrice {
    /**
     * 记忆+dfs
     *
     * @param n
     * @param flights
     * @param src
     * @param dst
     * @param k
     * @return
     */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        List<int[]>[] edges = new List[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new LinkedList<>();
        }
        for (int[] flight : flights) {
            int from = flight[0];
            int to = flight[1];
            int weight = flight[2];
            edges[from].add(new int[]{to, weight});
        }
        return dfs(edges, src, dst, k, new int[n]);
    }

    private int dfs(List<int[]>[] edges, int src, int dst, int k, int[] visit) {
        if (k < 0) {
            return -1;
        }
        int min = -1;
        for (int[] edge : edges[src]) {
            int to = edge[0];
            int weight = edge[1];
            if (visit[to] == 1) {
                continue;
            }
            if (to == dst) {
                if (min == -1 || weight < min) {
                    min = weight;
                }
            } else {
                int r = dfs(edges, to, dst, k - 1, visit);
                if (r >= 0 && (min == -1 || r + weight < min)) {
                    min = r + weight;
                }
            }
            visit[to] = 0;
        }
        return min;
    }


    //TODO：是否可以借鉴Dijkstra算法
}
