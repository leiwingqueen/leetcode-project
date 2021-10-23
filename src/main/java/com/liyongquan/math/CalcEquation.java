package com.liyongquan.math;

//给你一个变量对数组 equations 和一个实数值数组 values 作为已知条件，其中 equations[i] = [Ai, Bi] 和 values[i] 共同表示等式 Ai / Bi = values[i] 。每个 Ai 或 Bi 是一个表示单个变量的字符串。
//
//另有一些以数组 queries 表示的问题，其中 queries[j] = [Cj, Dj] 表示第 j 个问题，请你根据已知条件找出 Cj / Dj = ? 的结果作为答案。
//
//返回 所有问题的答案 。如果存在某个无法确定的答案，则用 -1.0 替代这个答案。如果问题中出现了给定的已知条件中没有出现的字符串，也需要用 -1.0 替代这个答案。
//
//注意：输入总是有效的。你可以假设除法运算中不会出现除数为 0 的情况，且不存在任何矛盾的结果。
//
// 
//
//示例 1：
//
//输入：equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
//输出：[6.00000,0.50000,-1.00000,1.00000,-1.00000]
//解释：
//条件：a / b = 2.0, b / c = 3.0
//问题：a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
//结果：[6.0, 0.5, -1.0, 1.0, -1.0 ]
//示例 2：
//
//输入：equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
//输出：[3.75000,0.40000,5.00000,0.20000]
//示例 3：
//
//输入：equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
//输出：[0.50000,2.00000,-1.00000,-1.00000]
// 
//
//提示：
//
//1 <= equations.length <= 20
//equations[i].length == 2
//1 <= Ai.length, Bi.length <= 5
//values.length == equations.length
//0.0 < values[i] <= 20.0
//1 <= queries.length <= 20
//queries[i].length == 2
//1 <= Cj.length, Dj.length <= 5
//Ai, Bi, Cj, Dj 由小写英文字母与数字组成
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/evaluate-division
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import javafx.util.Pair;

import java.util.*;

/**
 * @author liyongquan
 * @date 2021/10/23
 */
public class CalcEquation {
    /**
     * 这个解法是有问题的，不能通过
     *
     * @param equations
     * @param values
     * @param queries
     * @return
     */
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Double> map = new HashMap<>();
        int len = equations.size();
        for (int i = 0; i < len; i++) {
            String v1 = equations.get(i).get(0);
            String v2 = equations.get(i).get(1);
            double r = values[i];
            if (map.containsKey(v1)) {
                if (!map.containsKey(v2)) {
                    map.put(v2, map.get(v1) / r);
                }
            } else {
                if (map.containsKey(v2)) {
                    map.put(v1, map.get(v2) * r);
                } else {
                    map.put(v1, 1D);
                    map.put(v2, 1D / r);
                }
            }
        }
        double[] res = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String v1 = queries.get(i).get(0);
            String v2 = queries.get(i).get(1);
            if (map.containsKey(v1) && map.containsKey(v2)) {
                res[i] = map.get(v1) / map.get(v2);
            } else {
                res[i] = -1D;
            }
        }
        return res;
    }

    /**
     * 构造有向图，假设变量a和变量b有一个等式，那么我们就把变量a,b连一条线。每个查询我们使用bfs来查询两个点之间经过的线，把线的权值相乘即可
     *
     * @param equations
     * @param values
     * @param queries
     * @return
     */
    public double[] calcEquation2(List<List<String>> equations, double[] values, List<List<String>> queries) {
        int len = equations.size();
        //变量映射成对应的ID
        Map<String, Integer> mp = new HashMap<>();
        int id = 0;
        for (List<String> equation : equations) {
            String v1 = equation.get(0);
            String v2 = equation.get(1);
            if (!mp.containsKey(v1)) {
                mp.put(v1, id++);
            }
            if (!mp.containsKey(v2)) {
                mp.put(v2, id++);
            }
        }
        //构造图
        double[][] graph = new double[id][id];
        for (int i = 0; i < id; i++) {
            for (int j = 0; j < id; j++) {
                graph[i][j] = -1;
            }
        }
        for (int i = 0; i < len; i++) {
            String v1 = equations.get(i).get(0);
            String v2 = equations.get(i).get(1);
            graph[mp.get(v1)][mp.get(v2)] = values[i];
            graph[mp.get(v2)][mp.get(v1)] = 1D / values[i];
        }
        //计算结果
        double[] res = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            List<String> query = queries.get(i);
            String v1 = query.get(0);
            String v2 = query.get(1);
            if (mp.containsKey(v1) && mp.containsKey(v2)) {
                res[i] = bfs(graph, mp.get(query.get(0)), mp.get(query.get(1)));
            } else {
                res[i] = -1D;
            }
        }
        return res;
    }

    private double bfs(double[][] graph, int v1, int v2) {
        if (v1 == v2) {
            return 1D;
        }
        int len = graph.length;
        boolean[] visit = new boolean[len];
        Queue<Pair<Integer, Double>> queue = new LinkedList<>();
        queue.add(new Pair<>(v1, 1D));
        visit[v1] = true;
        while (!queue.isEmpty()) {
            Pair<Integer, Double> poll = queue.poll();
            for (int i = 0; i < len; i++) {
                //存在线，访问下一个节点
                if (!visit[i] && graph[poll.getKey()][i] > 0) {
                    visit[i] = true;
                    double r = poll.getValue() * graph[poll.getKey()][i];
                    if (i == v2) {
                        return r;
                    }
                    queue.add(new Pair<>(i, r));
                }
            }
        }
        return -1D;
    }

    //TODO:并查集
}
