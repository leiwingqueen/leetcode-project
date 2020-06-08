package com.liyongquan.dp;

/**
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * <p>
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
 * <p>
 * 注意：你不能在买入股票前卖出股票。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 * 示例 2:
 * <p>
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Stock {
    /**
     * 暴力解法
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int max = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                int p = prices[j] - prices[i];
                if (p > 0 && p > max) {
                    max = p;
                }
            }
        }
        return max;
    }

    /**
     * dp表达式
     * dp[i]=max(dp[i−1],prices[i]−minprice)
     *
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        if (prices.length <= 0) {
            return 0;
        }
        int min = prices[0];
        int dp0 = 0;
        for (int i = 1; i < prices.length; i++) {
            dp0 = Math.max(dp0, prices[i] - min);
            if (prices[i] < min) {
                min = prices[i];
            }
        }
        return dp0;
    }

}
