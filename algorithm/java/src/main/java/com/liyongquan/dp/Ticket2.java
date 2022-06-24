package com.liyongquan.dp;


import javafx.util.Pair;

import java.util.*;

/**
 * 发票匹配问题
 * <p>
 * 先贪心，然后再dp
 */
public class Ticket2 {
    /**
     * 贪心+dp
     *
     * @param s 发票金额
     * @param t 台账金额
     */
    public MatchResult match(int[] s, int[] t) {
        //能够刚好匹配的直接匹配出账
        //k--金额，v--对应的下标列表
        Map<Integer, Set<Integer>> map = new HashMap<>(s.length);
        //发票的总额
        for (int i = 0; i < s.length; i++) {
            if (map.containsKey(s[i])) {
                map.get(s[i]).add(i);
            } else {
                Set<Integer> set = new HashSet<>();
                set.add(i);
                map.put(s[i], set);
            }
        }
        List<Pair<Integer, Integer>> greedPairList = new LinkedList<>();
        //后续需要dp的台账金额
        int tSum = 0;
        for (int i = 0; i < t.length; i++) {
            int price = t[i];
            //能够刚好匹配直接优先匹配
            if (map.containsKey(price) && map.get(price).size() > 0) {
                Set<Integer> set = map.get(price);
                Integer idx = set.iterator().next();
                greedPairList.add(new Pair<>(i, idx));
                set.remove(idx);
                //匹配成功后把台账金额置为0，并把发票总额减少，方便后续dp求最优解
                s[idx] = 0;
            } else {
                tSum += price;
            }
        }
        //剩下的作为一个大背包做dp
        TicketPair ticketPair = match(s, tSum);
        return new Ticket2.MatchResult(greedPairList, ticketPair);
    }

    /**
     * 匹配发票，并且保证得到的发票总额最小。如果不存在结果，返回-1
     * <p>
     * dp方程：
     * f(k,w)=min{f(k-1,w),f(k-1,w-s[k])+s[k]}
     * <p>
     * f(k,w)为前k个发票能够组成满足w金额的最小发票组合。s[k]为第k张发票的金额
     *
     * @param s 发票金额
     * @param w 台账金额
     * @return
     */
    public TicketPair match(int[] s, int w) {
        TicketPair[][] dp = new TicketPair[s.length][w + 1];
        //初始化
        for (int i = 0; i <= w; i++) {
            if (s[0] >= i) {
                if (s[0] == 0) {
                    dp[0][i] = new TicketPair(0, Collections.emptyList());
                }else {
                    dp[0][i] = new TicketPair(s[0], Arrays.asList(0));
                }
            } else {
                dp[0][i] = new TicketPair(-1, Collections.emptyList());
            }
        }
        for (int i = 0; i < s.length; i++) {
            dp[i][0] = new TicketPair(0, Collections.emptyList());
        }
        //dp表达式转换
        for (int i = 1; i < s.length; i++) {
            for (int j = 1; j <= w; j++) {
                if (j >= s[i]) {
                    //不能直接取min,-1的场景要特殊处理
                    dp[i][j] = dp[i - 1][j].clone();
                    int temp = dp[i - 1][j - s[i]].weight;
                    if (temp >= 0 && (dp[i][j].weight < 0 || temp + s[i] < dp[i][j].weight)) {
                        List<Integer> list = new LinkedList<>();
                        list.addAll(dp[i - 1][j - s[i]].list);
                        list.add(i);
                        dp[i][j] = new TicketPair(temp + s[i], list);
                    }
                } else {
                    //j<s[i]，这意味着如果选择s[i]的话，s[i]就是最优解了
                    dp[i][j] = dp[i - 1][j].clone();
                    if (dp[i][j].weight < 0 || s[i] < dp[i][j].weight) {
                        dp[i][j] = new TicketPair(s[i], Arrays.asList(i));
                    }
                }
            }
        }
        return dp[s.length - 1][w];
    }

    public static class TicketPair implements Cloneable {
        //发票总额
        int weight;
        //选择的发票列表
        List<Integer> list;

        public TicketPair(int weight, List<Integer> list) {
            this.weight = weight;
            this.list = list;
        }

        @Override
        protected TicketPair clone() {
            List<Integer> list = new LinkedList<>();
            for (Integer i : this.list) {
                list.add(i);
            }
            return new TicketPair(this.weight, list);
        }
    }

    public static class MatchResult {
        //贪心算法匹配的解(k--台账下标，v--发票的下标)
        List<Pair<Integer, Integer>> greedPairList;
        //dp对剩余台账返回的最优解
        TicketPair ticketPair;

        public MatchResult(List<Pair<Integer, Integer>> greedPairList, TicketPair ticketPair) {
            this.greedPairList = greedPairList;
            this.ticketPair = ticketPair;
        }
    }
}
