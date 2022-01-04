package com.liyongquan.backtrack;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//913. 猫和老鼠
//两位玩家分别扮演猫和老鼠，在一张 无向 图上进行游戏，两人轮流行动。
//
//图的形式是：graph[a] 是一个列表，由满足 ab 是图中的一条边的所有节点 b 组成。
//
//老鼠从节点 1 开始，第一个出发；猫从节点 2 开始，第二个出发。在节点 0 处有一个洞。
//
//在每个玩家的行动中，他们 必须 沿着图中与所在当前位置连通的一条边移动。例如，如果老鼠在节点 1 ，那么它必须移动到 graph[1] 中的任一节点。
//
//此外，猫无法移动到洞中（节点 0）。
//
//然后，游戏在出现以下三种情形之一时结束：
//
//如果猫和老鼠出现在同一个节点，猫获胜。
//如果老鼠到达洞中，老鼠获胜。
//如果某一位置重复出现（即，玩家的位置和移动顺序都与上一次行动相同），游戏平局。
//给你一张图 graph ，并假设两位玩家都都以最佳状态参与游戏：
//
//如果老鼠获胜，则返回 1；
//如果猫获胜，则返回 2；
//如果平局，则返回 0 。
// 
//示例 1：
//
//
//输入：graph = [[2,5],[3],[0,4,5],[1,4,5],[2,3],[0,2,3]]
//输出：0
//示例 2：
//
//
//输入：graph = [[1,3],[0],[3],[0,2]]
//输出：1
// 
//
//提示：
//
//3 <= graph.length <= 50
//1 <= graph[i].length < graph.length
//0 <= graph[i][j] < graph.length
//graph[i][j] != i
//graph[i] 互不相同
//猫和老鼠在游戏中总是移动
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/cat-and-mouse
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
@Slf4j
public class CatMouseGame {
    /**
     * 先来个最粗暴的回溯
     *
     * @param graph
     * @return
     */
    public int catMouseGame(int[][] graph) {
        return backtrace(graph, 1, 2, true, new HashSet<>());
    }

    private int backtrace(int[][] graph, int p1, int p2, boolean seq, Set<Integer> set) {
        //log.info("p1:{},p2:{},seq:{}", p1, p2, seq);
        if (p1 == 0) {
            return 1;
        }
        if (p1 == p2) {
            return 2;
        }
        int state = encode(p1, p2, seq);
        if (set.contains(state)) {
            return 0;
        }
        //注意这里一定要放前面
        set.add(state);
        int res;
        if (seq) {
            //老鼠先走
            res = 2;
            for (int next : graph[p1]) {
                int r = backtrace(graph, next, p2, false, set);
                if (r == 1) {
                    res = 1;
                    break;
                } else if (r == 0) {
                    res = 0;
                }
            }
        } else {
            //猫先走
            res = 1;
            for (int next : graph[p2]) {
                //0的位置不能走
                if (next == 0) {
                    continue;
                }
                int r = backtrace(graph, p1, next, true, set);
                if (r == 2) {
                    res = 2;
                    break;
                } else if (r == 0) {
                    res = 0;
                }
            }
        }
        return res;
    }

    private int encode(int p1, int p2, boolean seq) {
        return ((seq ? 1 : 0) << 12) | (p1 << 6) | p2;
    }

    /**
     * dp解法
     * <p>
     * 击败了8%的用户
     *
     * @param graph
     * @return
     */
    public int catMouseGame2(int[][] graph) {
        int n = graph.length;
        int[][][] dp = new int[2 * n][n][n];
        //初始化
        /*for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[2 * n - 1][i][j] = 0;
            }
        }*/
        for (int i = 0; i < 2 * n; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][0][j] = 1;
                dp[i][j][j] = 2;
            }
        }
        for (int i = 2 * n - 2; i >= 0; i--) {
            for (int j = 1; j < n; j++) {
                for (int k = 1; k < n; k++) {
                    if (j == k) {
                        dp[i][j][k] = 2;
                    } else {
                        if (i % 2 == 0) {
                            //老鼠走
                            dp[i][j][k] = 2;
                            for (int next : graph[j]) {
                                if (dp[i + 1][next][k] == 1) {
                                    dp[i][j][k] = 1;
                                    break;
                                } else if (dp[i + 1][next][k] == 0) {
                                    dp[i][j][k] = 0;
                                }
                            }
                        } else {
                            //猫走
                            dp[i][j][k] = 1;
                            for (int next : graph[k]) {
                                //猫不能去0
                                if (next == 0) {
                                    continue;
                                }
                                if (dp[i + 1][j][next] == 2) {
                                    dp[i][j][k] = 2;
                                    break;
                                } else if (dp[i + 1][j][next] == 0) {
                                    dp[i][j][k] = 0;
                                }
                            }
                        }
                    }
                }
            }
        }
        return dp[0][1][2];
    }
}
