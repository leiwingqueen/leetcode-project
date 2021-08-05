package com.liyongquan.bfs;

// 802. 找到最终的安全状态

// 在有向图中，以某个节点为起始节点，从该点出发，每一步沿着图中的一条有向边行走。如果到达的节点是终点（即它没有连出的有向边），则停止。
//
//对于一个起始节点，如果从该节点出发，无论每一步选择沿哪条有向边行走，最后必然在有限步内到达终点，则将该起始节点称作是 安全 的。
//
//返回一个由图中所有安全的起始节点组成的数组作为答案。答案数组中的元素应当按 升序 排列。
//
//该有向图有 n 个节点，按 0 到 n - 1 编号，其中 n 是 graph 的节点数。图以下述形式给出：graph[i] 是编号 j 节点的一个列表，满足 (i, j) 是图的一条有向边。
//
// 
//
//示例 1：
//
//
//输入：graph = [[1,2],[2,3],[5],[0],[5],[],[]]
//输出：[2,4,5,6]
//解释：示意图如上。
//示例 2：
//
//输入：graph = [[1,2,3,4],[1,2],[3,4],[0,4],[]]
//输出：[4]
// 
//
//提示：
//
//n == graph.length
//1 <= n <= 104
//0 <= graph[i].length <= n
//graph[i] 按严格递增顺序排列。
//图中可能包含自环。
//图中边的数目在范围 [1, 4 * 104] 内。
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/find-eventual-safe-states
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author liyongquan
 * @date 2021/8/5
 */
public class EventualSafeNodes {
    /**
     * bfs检测是否存在环？
     *
     * @param graph
     * @return
     */
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int len = graph.length;
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            if (check(graph, i, len)) {
                res.add(i);
            }
        }
        return res;
    }

    private boolean check(int[][] graph, int i, int len) {
        Queue<Integer> queue = new LinkedList<>();
        int[] visit = new int[len];
        visit[i] = 1;
        queue.add(i);
        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            for (int next : graph[poll]) {
                if (visit[next] == 1) {
                    return false;
                }
                visit[next] = 1;
                queue.add(next);
            }
        }
        return true;
    }
}
