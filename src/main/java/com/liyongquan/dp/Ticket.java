package com.liyongquan.dp;


import java.util.*;

/**
 * 发票匹配问题
 */
public class Ticket {
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
                dp[0][i] = new TicketPair(s[0], Arrays.asList(0));
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
                    if (temp > 0 && (dp[i][j].weight < 0 || temp + s[i] < dp[i][j].weight)) {
                        List<Integer> list = new LinkedList<>();
                        list.addAll(dp[i - 1][j - s[i]].list);
                        list.add(i);
                        dp[i][j] = new TicketPair(temp + s[i], list);
                    }
                } else {
                    //j<s[i]，这意味着如果选择s[i]的话，s[i]就是最优解了
                    dp[i][j] = dp[i - 1][j].clone();
                    if (dp[i][j].weight < 0 || s[i] < dp[i][j].weight) {
                        dp[i][j] = new TicketPair(s[i],Arrays.asList(i));
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
}
