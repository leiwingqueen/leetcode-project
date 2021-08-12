package com.liyongquan.bfs;

//847. 访问所有节点的最短路径
//存在一个由 n 个节点组成的无向连通图，图中的节点按从 0 到 n - 1 编号。
//
//给你一个数组 graph 表示这个图。其中，graph[i] 是一个列表，由所有与节点 i 直接相连的节点组成。
//
//返回能够访问所有节点的最短路径的长度。你可以在任一节点开始和停止，也可以多次重访节点，并且可以重用边。
//
// 
//
//示例 1：
//
//
//输入：graph = [[1,2,3],[0],[0],[0]]
//输出：4
//解释：一种可能的路径为 [1,0,2,0,3]
//示例 2：
//
//
//
//输入：graph = [[1],[0,2,4],[1,3,4],[2],[1,2]]
//输出：4
//解释：一种可能的路径为 [0,1,4,2,3]
// 
//
//提示：
//
//n == graph.length
//1 <= n <= 12
//0 <= graph[i].length < n
//graph[i] 不包含 i
//如果 graph[a] 包含 b ，那么 graph[b] 也包含 a
//输入的图总是连通图
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/shortest-path-visiting-all-nodes
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import java.util.*;

/**
 * @author liyongquan
 * @date 2021/8/12
 */
public class ShortestPathLength {
    /**
     * 参考题解，状态压缩+bfs
     *
     * @param graph
     * @return
     */
    public int shortestPathLength(int[][] graph) {
        int len = graph.length;
        if (len == 1) {
            return 0;
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < len; i++) {
            res = Math.min(bfs(graph, i), res);
        }
        return res;
    }

    private int bfs(int[][] graph, int start) {
        Queue<State> queue = new LinkedList<>();
        Set<State> visit = new HashSet<>();
        State s0 = new State(start, 1 << start, 0);
        visit.add(s0);
        queue.add(s0);
        while (!queue.isEmpty()) {
            State poll = queue.poll();
            for (int next : graph[poll.u]) {
                State s1 = new State(next, poll.mask | (1 << next), poll.dist + 1);
                if (s1.isFinish(graph.length)) {
                    return s1.dist;
                }
                if (!visit.contains(s1)) {
                    visit.add(s1);
                    queue.add(s1);
                }
            }
        }
        //题目定义就是连通图，理论上是不会到这里的
        return -1;
    }

    private static class State {
        //当前节点编号
        int u;
        //已经访问的节点状态，用bitmap来表示
        int mask;
        //已经访问的路径长度
        int dist;

        public State(int u, int mask, int dist) {
            this.u = u;
            this.mask = mask;
            this.dist = dist;
        }

        public boolean isFinish(int len) {
            return mask == (1 << len) - 1;
        }

        //同一个节点，相同的访问状态不再进行处理，剪枝
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            State state = (State) o;
            return u == state.u && mask == state.mask;
        }

        @Override
        public int hashCode() {
            return Objects.hash(u, mask);
        }
    }
}
