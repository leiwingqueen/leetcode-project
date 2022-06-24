package com.liyongquan.dp;

/**
 * 买卖股票的最佳时机含手续费
 * <p>
 * 给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；非负整数 fee 代表了交易股票的手续费用。
 * <p>
 * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
 * <p>
 * 返回获得利润的最大值。
 * <p>
 * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
 * <p>
 * 示例 1:
 * <p>
 * 输入: prices = [1, 3, 2, 8, 4, 9], fee = 2
 * 输出: 8
 * 解释: 能够达到的最大利润:
 * 在此处买入 prices[0] = 1
 * 在此处卖出 prices[3] = 8
 * 在此处买入 prices[4] = 4
 * 在此处卖出 prices[5] = 9
 * 总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
 * 注意:
 * <p>
 * 0 < prices.length <= 50000.
 * 0 < prices[i] < 50000.
 * 0 <= fee < 50000.
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Stock4 {
    /**
     * dp解法，可以使用类似背包问题的解法。
     * <p>
     * f0(n)为第N天没有持有股票的利润，f1(n)为第N天持有股票的利润(股票的价值不能当利润)，我们假设卖出的时候才会支付手续费
     * <p>
     * f0(n)=max{f0(n-1),f1(n-1)+price[n]-fee}
     * f1(n)=max{f1(n-1),f0(n-1)-price[n]}
     * <p>
     * 最终我们的解为f0(n)
     * <p>
     * 初始化：
     * <p>
     * f0(1)=0,f1(1)=-price[0]
     *
     * 时间复杂度O(n)，空间复杂度O(1)
     *
     * @param prices
     * @param fee
     * @return
     */
    public int maxProfit(int[] prices, int fee) {
        if (prices.length == 0) {
            return 0;
        }
        //初始化
        int f0 = 0, f1 = -prices[0];
        //dp迭代
        for (int i = 1; i < prices.length; i++) {
            int tmp = Math.max(f0, f1 + prices[i] - fee);
            f1 = Math.max(f1, f0 - prices[i]);
            f0 = tmp;
        }
        return f0;
    }
}
