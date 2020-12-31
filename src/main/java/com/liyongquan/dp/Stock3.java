package com.liyongquan.dp;

/**
 * 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​
 * <p>
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 * <p>
 * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 * 示例:
 * <p>
 * 输入: [1,2,3,0,2]
 * 输出: 3
 * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Stock3 {
    /**
     * 典型的dp题目。因为多了一个冻结期的状态，所以状态定义需要多一种。
     * f0(n)，无持有股票，不处于冻结期
     * f1(n),无持有股票，处于冻结期
     * f2(n),持有股票
     * <p>
     * 最终结果为max{f0(n),f1(n)}
     * <p>
     * dp表达式：
     * f0(n)=max{f0(n-1),f1(n-1)}
     * f1(n)=f2(n-1)+A[n]
     * f2(n)=max{f2(n-1),f0(n-1)-A[n]}
     * <p>
     * 初始化：
     * f0(2)=0
     * f1(2)=A[1]-A[0]
     * f2(2)=max{-A[0],-A[1]}
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices.length <= 1) {
            return 0;
        }
        //初始化
        int f0 = 0;
        int f1 = prices[1] - prices[0];
        int f2 = Math.max(-prices[0], -prices[1]);
        //dp表达式
        for (int i = 2; i < prices.length; i++) {
            int n0 = Math.max(f0, f1);
            int n1 = f2 + prices[i];
            int n2 = Math.max(f2, f0 - prices[i]);
            f0 = n0;
            f1 = n1;
            f2 = n2;
        }
        return Math.max(f0, f1);
    }
}
