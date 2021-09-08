package com.liyongquan.heap;

//502. IPO
//假设 力扣（LeetCode）即将开始 IPO 。为了以更高的价格将股票卖给风险投资公司，力扣 希望在 IPO 之前开展一些项目以增加其资本。 由于资源有限，它只能在 IPO 之前完成最多 k 个不同的项目。帮助 力扣 设计完成最多 k 个不同项目后得到最大总资本的方式。
//
//给你 n 个项目。对于每个项目 i ，它都有一个纯利润 profits[i] ，和启动该项目需要的最小资本 capital[i] 。
//
//最初，你的资本为 w 。当你完成一个项目时，你将获得纯利润，且利润将被添加到你的总资本中。
//
//总而言之，从给定项目中选择 最多 k 个不同项目的列表，以 最大化最终资本 ，并输出最终可获得的最多资本。
//
//答案保证在 32 位有符号整数范围内。
//
// 
//
//示例 1：
//
//输入：k = 2, w = 0, profits = [1,2,3], capital = [0,1,1]
//输出：4
//解释：
//由于你的初始资本为 0，你仅可以从 0 号项目开始。
//在完成后，你将获得 1 的利润，你的总资本将变为 1。
//此时你可以选择开始 1 号或 2 号项目。
//由于你最多可以选择两个项目，所以你需要完成 2 号项目以获得最大的资本。
//因此，输出最后最大化的资本，为 0 + 1 + 3 = 4。
//示例 2：
//
//输入：k = 3, w = 0, profits = [1,2,3], capital = [0,1,2]
//输出：6
// 
//
//提示：
//
//1 <= k <= 105
//0 <= w <= 109
//n == profits.length
//n == capital.length
//1 <= n <= 105
//0 <= profits[i] <= 104
//0 <= capital[i] <= 109
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/ipo
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import javafx.util.Pair;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author liyongquan
 * @date 2021/9/8
 */
public class FindMaximizedCapital {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        PriorityQueue<int[]> pq1 = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
        PriorityQueue<int[]> pq2 = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);
        int len = capital.length;
        for (int i = 0; i < len; i++) {
            pq1.add(new int[]{capital[i], profits[i]});
        }
        for (int i = 0; i < k; i++) {
            //筛选可选的项目
            while (pq1.size() > 0 && pq1.peek()[0] <= w) {
                pq2.add(pq1.poll());
            }
            //没有可选的项目
            if (pq2.size() == 0) {
                break;
            }
            w += pq2.poll()[1];
        }
        return w;
    }
}
