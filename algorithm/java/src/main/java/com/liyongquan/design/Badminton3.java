package com.liyongquan.design;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 算法二基础上改为位运算，方便计算交集。方便后面计算相邻两场比赛的重复人数和两个组合之间的重复人数
 *
 * @author liyongquan
 * @date 2021/10/24
 */
public class Badminton3 {
    private int min = Integer.MAX_VALUE;

    private List<Team[]> res;

    public static void main(String[] args) {
        Badminton3 badminton = new Badminton3();
        Player[] players = {
                new Player("权", 110),
                new Player("礼", 100),
                new Player("一雷", 100),
                new Player("梓坤", 150),
                new Player("毅", 100),
                new Player("明", 120),
                new Player("健宁", 50),
                new Player("命文", 50),
        };
        List<String[]> res = badminton.match(players);
        System.out.println("==============对战名单==============");
        Map<String, Integer> map = new HashMap<>();
        for (String[] re : res) {
            System.out.println(String.format("%s 和 %s VS %s 和 %s", re[0], re[1], re[2], re[3]));
            for (int i = 0; i < 4; i++) {
                map.put(re[i], map.getOrDefault(re[i], 0) + 1);
            }
        }
        System.out.println("==============每人参赛场次==============");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(String.format("%s:%s", entry.getKey(), entry.getValue()));
        }
        System.out.println("==============重复出现的组合==============");
        Set<String> m2 = new HashSet<>();
        for (String[] re : res) {
            String c1 = String.format("%s 和 %s", re[0], re[1]);
            String c2 = String.format("%s 和 %s", re[2], re[3]);
            if (m2.contains(c1)) {
                System.out.println(c1);
            }
            m2.add(c1);
            if (m2.contains(c2)) {
                System.out.println(c2);
            }
            m2.add(c2);
        }
        System.out.println("==============excel格式==============");
        for (String[] re : res) {
            System.out.println(String.format("%s\t%s\tVS\t%s\t%s", re[0], re[1], re[2], re[3]));
        }
    }

    public List<String[]> match(Player[] players) {
        int len = players.length;
        //给每个用户分配一个ID(位运算，简化后续的冲突计算逻辑)
        Map<Integer, Player> mp = new HashMap<>(len);
        for (int i = 0; i < len; i++) {
            mp.put(1 << i, players[i]);
        }
        //穷举所有组合(组合直接用上面生成的ID计算)
        List<Team> compose = new ArrayList<>(len * (len - 1) / 2);
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                compose.add(new Team(1 << i, 1 << j, players[i].score + players[j].score));
            }
        }
        if (compose.size() % 2 == 1) {
            //如果是奇数的话随机抽取两个人作为一个新的组合加入(这两个人可以多打一场)
            int p = ThreadLocalRandom.current().nextInt(compose.size());
            compose.add(compose.get(p));
        }
        //构造图
        int[][] graph = new int[compose.size()][compose.size()];
        for (int i = 0; i < compose.size(); i++) {
            for (int j = 0; j < compose.size(); j++) {
                //没有冲突意味着交集为0
                if ((compose.get(i).getTeamId() & compose.get(j).getTeamId()) == 0) {
                    int w = Math.abs(compose.get(i).score - compose.get(j).score);
                    graph[i][j] = w;
                } else {
                    graph[i][j] = -1;
                }
            }
        }
        int matchSize = compose.size() / 2;
        backtrace(compose, graph, new Team[matchSize][matchSize], 0, matchSize, 0);
        //输出结果
        List<String[]> r = new ArrayList<>(matchSize);
        if (res != null) {
            //尽可能让两场比赛冲突的人员减少
            List<Team[]> sort = sort(res);
            for (Team[] re : sort) {
                r.add(new String[]{mp.get(re[0].p1).name, mp.get(re[0].p2).name,
                        mp.get(re[1].p1).name, mp.get(re[1].p2).name});
            }
        }
        return r;
    }

    private void backtrace(List<Team> compose, int[][] graph, Team[][] path, int idx, int len, int weight) {
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
            int[] backup1 = new int[compose.size()];
            int[] backup2 = new int[compose.size()];
            int[] backup3 = new int[compose.size()];
            int[] backup4 = new int[compose.size()];
            //横线处理
            for (int k = 0; k < compose.size(); k++) {
                backup1[k] = graph[edge.start][k];
                graph[edge.start][k] = -1;
                backup2[k] = graph[edge.end][k];
                graph[edge.end][k] = -1;
            }
            //竖线处理
            for (int k = 0; k < compose.size(); k++) {
                //横竖线相交的地方需要特殊处理
                if (k != edge.start && k != edge.end) {
                    backup4[k] = graph[k][edge.end];
                    backup3[k] = graph[k][edge.start];
                }
                graph[k][edge.start] = -1;
                graph[k][edge.end] = -1;
            }
            path[idx] = new Team[]{compose.get(edge.start), compose.get(edge.end)};
            backtrace(compose, graph, path, idx + 1, len, weight + graph[edge.start][edge.end]);
            //还原现场
            for (int k = 0; k < compose.size(); k++) {
                graph[edge.start][k] = backup1[k];
                graph[edge.end][k] = backup2[k];
            }
            for (int k = 0; k < compose.size(); k++) {
                if (k != edge.start && k != edge.end) {
                    graph[k][edge.start] = backup3[k];
                    graph[k][edge.end] = backup4[k];
                }
            }
        }
    }

    /**
     * 避免相同的人多长连续打
     *
     * @param list
     * @return
     */
    private List<Team[]> sort(List<Team[]> list) {
        List<Team[]> r = new ArrayList<>(list.size());
        r.add(list.get(0));
        list.remove(0);
        //保存每个人的连打次数，用来控制筛选下一场的权重
        Map<Integer, Integer> mp = new HashMap<>();
        Team[] teams = list.get(0);
        mp.put(teams[0].p1, 1);
        mp.put(teams[0].p2, 1);
        mp.put(teams[1].p1, 1);
        mp.put(teams[1].p2, 1);
        while (list.size() > 0) {
            list.sort((o1, o2) -> {
                //避免一个人连打的次数太多，需要增加一个人连打场次作为权重
                //eg.[A,B]VS[C,D],[A,E]VS[F,G],[A,C]VS[B,D]，虽然两句之间冲突的人数只有1，但是A连打了三局，我们需要把A的权重调高
                int b1 = mp.getOrDefault(o1[0].p1, 0) + mp.getOrDefault(o1[0].p2, 0) +
                        mp.getOrDefault(o1[1].p1, 0) + mp.getOrDefault(o1[1].p2, 0);
                int b2 = mp.getOrDefault(o2[0].p1, 0) + mp.getOrDefault(o2[0].p2, 0) +
                        mp.getOrDefault(o2[1].p1, 0) + mp.getOrDefault(o2[1].p2, 0);
                return b1 - b2;
            });
            Team[] select = list.get(0);
            r.add(select);
            list.remove(0);
            //更新连打次数
            for (Team team : select) {
                mp.put(team.p1, mp.getOrDefault(team.p1, 0) + 1);
                mp.put(team.p2, mp.getOrDefault(team.p2, 0) + 1);
            }
            for (Map.Entry<Integer, Integer> entry : mp.entrySet()) {
                Integer id = entry.getKey();
                //没有交集，删除连打
                if ((id & (select[0].getTeamId() + select[1].getTeamId())) == 0) {
                    mp.put(id, 0);
                }
            }
        }
        return r;
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
        int p1;
        int p2;
        int score;

        public Team(int p1, int p2, int score) {
            this.p1 = p1;
            this.p2 = p2;
            this.score = score;
        }

        public int getTeamId() {
            return p1 + p2;
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
