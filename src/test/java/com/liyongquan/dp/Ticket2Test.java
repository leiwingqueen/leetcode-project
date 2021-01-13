package com.liyongquan.dp;

import javafx.util.Pair;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class Ticket2Test {

    @Test
    public void match() {
        //刚好满足
        baseTest(new int[]{1, 2, 5, 3}, new int[]{2, 1, 4});
        baseTest(new int[]{1, 2, 5, 3}, new int[]{2, 1, 1, 7});

        baseTest(new int[]{1, 2, 50, 3}, new int[]{2, 1, 1, 7});
        //不足的场景
        baseTest(new int[]{1, 2, 5, 3}, new int[]{4, 2});
    }

    @Test
    public void match_over() {
        baseTest(new int[]{1, 4, 5, 9}, new int[]{1, 2, 3});
        baseTest(new int[]{1, 4, 5, 9, 34, 5, 4}, new int[]{1, 7, 10});
    }

    private void baseTest(int[] tickets, int[] tz) {
        System.out.println("=================================");
        Ticket2 ticket = new Ticket2();
        StringBuilder builder = new StringBuilder();
        for (int i : tickets) {
            builder.append(i + ",");
        }
        StringBuilder sb = new StringBuilder();
        for (int i : tz) {
            sb.append(i + ",");
        }
        System.out.println("Q:台账列表:" + sb + "发票列表:" + builder);
        Ticket2.MatchResult match = ticket.match(tickets, tz);
        System.out.println("A:能够单独出账的台账数量:" + match.greedPairList.size());
        builder = new StringBuilder();
        for (Pair<Integer, Integer> pair : match.greedPairList) {
            builder.append(pair.getKey() + "-" + pair.getValue() + ",");
        }
        System.out.println("能够单独出账的台账下标映射([台账下标]-[发票下标]):[" + builder + "]");
        builder = new StringBuilder();
        for (Integer idx : match.ticketPair.list) {
            builder.append(tickets[idx] + ",");
        }
        System.out.println("剩下的拼单的台账-选择的发票的金额:" + builder);
        //发票浪费
        int wast = 0;
        if (match.ticketPair.list.size() > 0) {
            int tSum = 0;
            for (Integer t : match.ticketPair.list) {
                tSum += tickets[t];
            }
            int tzSum = 0;
            for (int i : tz) {
                tzSum += i;
            }
            for (Pair<Integer, Integer> pair : match.greedPairList) {
                tzSum -= tz[pair.getKey()];
            }
            wast = tSum - tzSum;
            //log.info("tSum:{},tzSum:{}", tSum, tzSum);
        }
        System.out.println("发票浪费:" + wast);
    }
}