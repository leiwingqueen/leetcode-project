package com.liyongquan.weeklycontest.wc304;

//给你一个 n 个节点的 有向图 ，节点编号为 0 到 n - 1 ，其中每个节点 至多 有一条出边。
//
//图用一个大小为 n 下标从 0 开始的数组 edges 表示，节点 i 到节点 edges[i] 之间有一条有向边。如果节点 i 没有出边，那么 edges[i] == -1 。
//
//请你返回图中的 最长 环，如果没有任何环，请返回 -1 。
//
//一个环指的是起点和终点是 同一个 节点的路径。
//
// 
//
//示例 1：
//
//
//
//输入：edges = [3,3,4,2,3]
//输出去：3
//解释：图中的最长环是：2 -> 4 -> 3 -> 2 。
//这个环的长度为 3 ，所以返回 3 。
//示例 2：
//
//
//
//输入：edges = [2,-1,3,1]
//输出：-1
//解释：图中没有任何环。
// 
//
//提示：
//
//n == edges.length
//2 <= n <= 105
//-1 <= edges[i] < n
//edges[i] != i
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/longest-cycle-in-a-graph
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

public class LongestCycle {
    public int longestCycle(int[] edges) {
        int len = edges.length;
        boolean[] visit = new boolean[len];
        int res = -1;
        for (int i = 0; i < len; i++) {
            int from = i;
            if (visit[from]) {
                continue;
            }
            int to = edges[i];
            boolean[] v2 = new boolean[len];
            while (from >= 0 && !visit[from] && !v2[from]) {
                visit[from] = true;
                v2[from] = true;
                from = edges[from];
            }
            //有环，算一遍
            if (from >= 0 && v2[from]) {
                int cnt = 1;
                int tmp = from;
                while (edges[tmp] != from) {
                    cnt++;
                    tmp = edges[tmp];
                }
                res = Math.max(res, cnt);
            }
        }
        return res;
    }

    /**
     * 解法2 时间戳
     *
     * @param edges
     * @return
     */
    public int longestCycle2(int[] edges) {
        int n = edges.length;
        int clock = 1;
        int ans = -1;
        int[] time = new int[n];
        for (int i = 0; i < n; i++) {
            if (time[i] > 0) {
                continue;
            }
            int startTime = clock;
            int from = i;
            while (from >= 0 && time[from] == 0) {
                time[from] = clock;
                clock++;
                from = edges[from];
            }
            //无环
            if (from < 0) {
                continue;
            }
            //新的环
            if (time[from] >= startTime) {
                ans = Math.max(ans, clock - time[from]);
            }
        }
        return ans;
    }
}
