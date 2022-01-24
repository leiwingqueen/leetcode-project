package com.liyongquan.bfs;

//2045. 到达目的地的第二短时间
//城市用一个 双向连通 图表示，图中有 n 个节点，从 1 到 n 编号（包含 1 和 n）。图中的边用一个二维整数数组 edges 表示，其中每个 edges[i] = [ui, vi] 表示一条节点 ui 和节点 vi 之间的双向连通边。每组节点对由 最多一条 边连通，顶点不存在连接到自身的边。穿过任意一条边的时间是 time 分钟。
//
//每个节点都有一个交通信号灯，每 change 分钟改变一次，从绿色变成红色，再由红色变成绿色，循环往复。所有信号灯都 同时 改变。你可以在 任何时候 进入某个节点，但是 只能 在节点 信号灯是绿色时 才能离开。如果信号灯是  绿色 ，你 不能 在节点等待，必须离开。
//
//第二小的值 是 严格大于 最小值的所有值中最小的值。
//
//例如，[2, 3, 4] 中第二小的值是 3 ，而 [2, 2, 4] 中第二小的值是 4 。
//给你 n、edges、time 和 change ，返回从节点 1 到节点 n 需要的 第二短时间 。
//
//注意：
//
//你可以 任意次 穿过任意顶点，包括 1 和 n 。
//你可以假设在 启程时 ，所有信号灯刚刚变成 绿色 。
// 
//
//示例 1：
//
//        
//
//输入：n = 5, edges = [[1,2],[1,3],[1,4],[3,4],[4,5]], time = 3, change = 5
//输出：13
//解释：
//上面的左图展现了给出的城市交通图。
//右图中的蓝色路径是最短时间路径。
//花费的时间是：
//- 从节点 1 开始，总花费时间=0
//- 1 -> 4：3 分钟，总花费时间=3
//- 4 -> 5：3 分钟，总花费时间=6
//因此需要的最小时间是 6 分钟。
//
//右图中的红色路径是第二短时间路径。
//- 从节点 1 开始，总花费时间=0
//- 1 -> 3：3 分钟，总花费时间=3
//- 3 -> 4：3 分钟，总花费时间=6
//- 在节点 4 等待 4 分钟，总花费时间=10
//- 4 -> 5：3 分钟，总花费时间=13
//因此第二短时间是 13 分钟。
//示例 2：
//
//
//
//输入：n = 2, edges = [[1,2]], time = 3, change = 2
//输出：11
//解释：
//最短时间路径是 1 -> 2 ，总花费时间 = 3 分钟
//最短时间路径是 1 -> 2 -> 1 -> 2 ，总花费时间 = 11 分钟
// 
//
//提示：
//
//2 <= n <= 104
//n - 1 <= edges.length <= min(2 * 104, n * (n - 1) / 2)
//edges[i].length == 2
//1 <= ui, vi <= n
//ui != vi
//不含重复边
//每个节点都可以从其他节点直接或者间接到达
//1 <= time, change <= 103
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/second-minimum-time-to-reach-destination
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import com.liyongquan.prefixSum.FavoriteCandy;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SecondMinimum {
    /**
     * 一步步来，先把最短路径的算法写下
     * <p>
     * BFS
     *
     * @param n
     * @param edges
     * @param time
     * @param change
     * @return
     */
    public int secondMinimum(int n, int[][] edges, int time, int change) {
        //构造图
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new LinkedList<>();
        }
        for (int[] edge : edges) {
            int x = edge[0] - 1;
            int y = edge[1] - 1;
            graph[x].add(y);
            graph[y].add(x);
        }
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visit = new boolean[n];
        int[] dis = new int[n];
        visit[0] = true;
        queue.add(0);
        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            for (Integer next : graph[poll]) {
                if (!visit[next]) {
                    dis[next] = dis[poll] + time;
                    visit[next] = true;
                    queue.add(next);
                }
            }
        }
        return dis[n - 1];
    }

    /**
     * 增加change的逻辑
     *
     * @param n
     * @param edges
     * @param time
     * @param change
     * @return
     */
    public int secondMinimum2(int n, int[][] edges, int time, int change) {
        int round = change * 2;
        //构造图
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new LinkedList<>();
        }
        for (int[] edge : edges) {
            int x = edge[0] - 1;
            int y = edge[1] - 1;
            graph[x].add(y);
            graph[y].add(x);
        }
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visit = new boolean[n];
        int[] dis = new int[n];
        visit[0] = true;
        queue.add(0);
        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            for (Integer next : graph[poll]) {
                if (!visit[next]) {
                    dis[next] = dis[poll] + time;
                    //等待时间处理
                    if (dis[poll] % round >= change) {
                        dis[next] += round - dis[poll] % round;
                    }
                    visit[next] = true;
                    queue.add(next);
                }
            }
        }
        return dis[n - 1];
    }

    /**
     * 第二短路径处理
     *
     * @param n
     * @param edges
     * @param time
     * @param change
     * @return
     */
    public int secondMinimum3(int n, int[][] edges, int time, int change) {
        int round = change * 2;
        //构造图
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new LinkedList<>();
        }
        for (int[] edge : edges) {
            int x = edge[0] - 1;
            int y = edge[1] - 1;
            graph[x].add(y);
            graph[y].add(x);
        }
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visit = new boolean[n];
        int[] dis = new int[n];
        //第二短的路径
        int[] second = new int[n];
        for (int i = 0; i < n; i++) {
            second[i] = Integer.MAX_VALUE;
        }
        visit[0] = true;
        queue.add(0);
        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            for (Integer next : graph[poll]) {
                //没有访问过的话就是最短路径
                int d1 = dis[poll] + time;
                //等待时间处理
                if (d1 % round >= change) {
                    d1 += round - dis[poll] % round;
                }
                //次短路径的时间
                int d2 = second[poll] + time;
                if (d2 % round >= change) {
                    d2 += round - second[poll] % round;
                }
                if (!visit[next]) {
                    dis[next] = d1;
                    visit[next] = true;
                    queue.add(next);
                } else {
                    //次短路径处理
                    boolean flag = false;
                    if (d1 < second[next] && d1 > dis[poll]) {
                        second[next] = d1;
                        flag = true;
                    } else if (d2 < second[next] && d2 > dis[poll]) {
                        second[next] = d2;
                        flag = true;
                    }
                    //需要继续更新后面的节点
                    if (flag) {
                        queue.add(next);
                    }
                }
            }
        }
        return second[n - 1];
    }
}
