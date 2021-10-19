package com.liyongquan.design;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author liyongquan
 * @date 2021/10/18
 */
public class Badminton {
    /**
     * 这个算法还是有点问题，
     *
     * @param players
     * @return
     */
    public List<String[]> match(Player[] players) {
        int len = players.length;
        //穷举所有组合
        List<Player[]> compose = new ArrayList<>(len * (len - 1) / 2);
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                compose.add(new Player[]{players[i], players[j]});
            }
        }
        //对所有组合进行排序,分数高的组合放在前面
        PriorityQueue<Player[]> pq = new PriorityQueue<>((o1, o2) -> o2[0].score + o2[1].score - (o1[0].score + o1[1].score));
        for (Player[] p : compose) {
            pq.add(p);
        }
        //贪心算法选择，必须保证对战的人员没有重复
        List<String[]> res = new LinkedList<>();
        while (pq.size() > 1) {
            Player[] c1 = pq.poll();
            List<Player[]> tmp = new LinkedList<>();
            //找到没有冲突且分数相近的组合
            while (pq.size() > 0) {
                Player[] c2 = pq.poll();
                if (!conflict(c1, c2)) {
                    res.add(new String[]{c1[0].name, c1[1].name, c2[0].name, c2[1].name});
                    break;
                } else {
                    tmp.add(c2);
                }
            }
            if (pq.size() == 0) {
                System.out.println("not match");
            }
            for (Player[] p : tmp) {
                pq.add(p);
            }
        }
        return res;
    }

    private boolean conflict(Player[] c1, Player[] c2) {
        return c1[0].name.equals(c2[0].name) || c1[0].name.equals(c2[1].name)
                || c1[1].name.equals(c2[0].name) || c1[1].name.equals(c2[1].name);
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
