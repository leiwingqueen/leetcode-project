package com.liyongquan.design;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 算法一可能会存在一些匹配不成功的场景，这里尝试使用回溯求最优解。
 * <p>
 * 8个人比赛一共存在8*7/2=28个组合
 * 每个组合只比赛一次，因此会有14场比赛
 * <p>
 * 我们假设每个组合是一个点，假设两个组合能比赛(没有冲突的人员)，我们就把组合之间连一条线。
 * 线的权重为两个组合的积分差的绝对值。
 * <p>
 * 我们的目标是选择14条线，线跟线之间没有交点，并且计算得到平方和最小(等价于求方差)。
 * eg. 线的权值分别为[a1,a2,a3,...,an]，总的权值为w=a1^2+a2^2+a3^2+...+an^2
 *
 * @author liyongquan
 * @date 2021/10/19
 */
public class Badminton2 {
    private int min = Integer.MAX_VALUE;

    private List<Team[]> res;

    public static void main(String[] args) {
        Badminton2 badminton = new Badminton2();
        Player[] players = {
                new Player("权", 100),
                new Player("礼", 100),
                new Player("锋", 300),
                new Player("海真", 300),
                new Player("健宁", 50),
                new Player("命文", 50),
                new Player("毅", 120),
                new Player("明", 150),
        };
        List<String[]> res = badminton.match(players);
        System.out.println("==============对战名单==============");
        for (String[] re : res) {
            System.out.println(String.format("%s 和 %s VS %s 和 %s", re[0], re[1], re[2], re[3]));
        }
    }

    public List<String[]> match(Player[] players) {
        int len = players.length;
        //穷举所有组合
        List<Team> compose = new ArrayList<>(len * (len - 1) / 2);
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                compose.add(new Team(players[i], players[j]));
            }
        }
        //构造图
        int[][] graph = new int[compose.size()][compose.size()];
        for (int i = 0; i < compose.size(); i++) {
            for (int j = 0; j < compose.size(); j++) {
                if (!conflict(compose.get(i), compose.get(j))) {
                    int w = Math.abs(compose.get(i).getScore() - compose.get(j).getScore());
                    graph[i][j] = w;
                } else {
                    graph[i][j] = -1;
                }
            }
        }
        int matchSize = compose.size() / 2;
        backtrace(compose, graph, new boolean[compose.size()], new Team[matchSize][matchSize], 0, matchSize, 0);
        //输出结果
        List<String[]> r = new ArrayList<>(matchSize);
        if (res != null) {
            for (Team[] re : res) {
                r.add(new String[]{re[0].p1.name, re[0].p2.name, re[1].p1.name, re[1].p2.name});
            }
        }
        return r;
    }

    private void backtrace(List<Team> compose, int[][] graph, boolean[] visit, Team[][] path, int idx, int len, int weight) {
        if (idx >= len) {
            if (weight < min) {
                min = weight;
                res = new ArrayList<>(len);
                for (Team[] t : path) {
                    res.add(new Team[]{t[0], t[1]});
                }
            }
            return;
        }
        //提前剪枝，不然O(n!)的复杂度会极其恐怖
        if (weight >= min) {
            return;
        }
        //选择两个组合
        //这里需要使用启发式搜索，优先搜索权重少的边，不然很容易超时
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < compose.size() - 1; i++) {
            for (int j = i + 1; j < compose.size(); j++) {
                if (graph[i][j] >= 0) {
                    edges.add(new Edge(i, j, graph[i][j]));
                }
            }
        }
        edges.sort(Comparator.comparingInt(o -> o.weight));
        for (Edge edge : edges) {
            //更新图,i,j下所有的线取消
            //visit[edge.start] = true;
            //visit[edge.end] = true;
            int[] backup1 = new int[compose.size()];
            int[] backup2 = new int[compose.size()];
            for (int k = 0; k < compose.size(); k++) {
                backup1[k] = graph[edge.start][k];
                graph[edge.start][k] = -1;
                backup2[k] = graph[edge.end][k];
                graph[edge.end][k] = -1;
            }
            path[idx] = new Team[]{compose.get(edge.start), compose.get(edge.end)};
            backtrace(compose, graph, visit, path, idx + 1, len, weight + graph[edge.start][edge.end]);
            //还原现场
            //visit[edge.start] = false;
            //visit[edge.end] = false;
            for (int k = 0; k < compose.size(); k++) {
                graph[edge.start][k] = backup1[k];
                graph[edge.end][k] = backup2[k];
            }
        }
    }

    private boolean conflict(Team c1, Team c2) {
        return c1.p1.name.equals(c2.p1.name) || c1.p1.name.equals(c2.p2.name)
                || c1.p2.name.equals(c2.p1.name) || c1.p2.name.equals(c2.p2.name);
    }

    static class Edge {
        int start;
        int end;
        int weight;

        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        //不超过30组的情况可以简单这么算
        @Override
        public int hashCode() {
            return start << 5 + end;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Edge)) {
                return false;
            }
            Edge e = (Edge) obj;
            return start == e.start && end == e.end;
        }
    }

    static class Team {
        Player p1;
        Player p2;

        public Team(Player p1, Player p2) {
            this.p1 = p1;
            this.p2 = p2;
        }

        public int getScore() {
            return p1.score + p2.score;
        }
    }

    static class Player {
        String name;
        int score;

        public Player(String name, int score) {
            this.name = name;
            this.score = score;
        }
    }
}
