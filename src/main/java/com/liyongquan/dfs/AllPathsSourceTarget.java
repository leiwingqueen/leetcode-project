package com.liyongquan.dfs;

//797. 所有可能的路径
//给你一个有 n 个节点的 有向无环图（DAG），请你找出所有从节点 0 到节点 n-1 的路径并输出（不要求按特定顺序）
//
//二维数组的第 i 个数组中的单元都表示有向图中 i 号节点所能到达的下一些节点，空就是没有下一个结点了。
//
//译者注：有向图是有方向的，即规定了 a→b 你就不能从 b→a 。
//
// 
//
//示例 1：
//
//
//
//输入：graph = [[1,2],[3],[3],[]]
//输出：[[0,1,3],[0,2,3]]
//解释：有两条路径 0 -> 1 -> 3 和 0 -> 2 -> 3
//示例 2：
//
//
//
//输入：graph = [[4,3,1],[3,2,4],[3],[4],[]]
//输出：[[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]
//示例 3：
//
//输入：graph = [[1],[]]
//输出：[[0,1]]
//示例 4：
//
//输入：graph = [[1,2,3],[2],[3],[]]
//输出：[[0,1,2,3FindCheapestPrice],[0,2,3],[0,3]]
//示例 5：
//
//输入：graph = [[1,3],[2],[3],[]]
//输出：[[0,1,2,3],[0,3]]
// 
//
//提示：
//
//n == graph.length
//2 <= n <= 15
//0 <= graph[i][j] < n
//graph[i][j] != i（即，不存在自环）
//graph[i] 中的所有元素 互不相同
//保证输入为 有向无环图（DAG）
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/all-paths-from-source-to-target
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author liyongquan
 * @date 2021/8/25
 */
public class AllPathsSourceTarget {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        int n = graph.length;
        List<List<Integer>> res = new LinkedList<>();
        backtrace(graph, new int[n], n, 1, res);
        return res;
    }

    private void backtrace(int[][] graph, int[] path, int n, int idx, List<List<Integer>> res) {
        int parent = path[idx - 1];
        for (int next : graph[parent]) {
            path[idx] = next;
            if (next == n - 1) {
                res.add(arr2list(path, idx + 1));
            } else {
                backtrace(graph, path, n, idx + 1, res);
            }
        }
    }

    private List<Integer> arr2list(int[] arr, int len) {
        List<Integer> res = new ArrayList<>(len);
        for (int i = 0; i < len; i++) {
            res.add(arr[i]);
        }
        return res;
    }
}
