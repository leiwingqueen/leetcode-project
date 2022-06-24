package com.liyongquan.dp;

/**
 * 123. 买卖股票的最佳时机 III
 * <p>
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 * <p>
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
 * <p>
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入：prices = [3,3,5,0,0,3,1,4]
 * 输出：6
 * 解释：在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
 *      随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
 * 示例 2：
 * <p>
 * 输入：prices = [1,2,3,4,5]
 * 输出：4
 * 解释：在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。  
 *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。  
 *      因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 * 示例 3：
 * <p>
 * 输入：prices = [7,6,4,3,1]
 * 输出：0
 * 解释：在这个情况下, 没有交易完成, 所以最大利润为 0。
 * 示例 4：
 * <p>
 * 输入：prices = [1]
 * 输出：0
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= prices.length <= 105
 * 0 <= prices[i] <= 105
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Stock5 {
    /**
     * 主要是状态分析要十分清晰。可以参考以下官解：
     * <p>
     * 由于我们最多可以完成两笔交易，因此在任意一天结束之后，我们会处于以下五个状态中的一种：
     * <p>
     * 未进行过任何操作；
     * <p>
     * 只进行过一次买操作；
     * <p>
     * 进行了一次买操作和一次卖操作，即完成了一笔交易；
     * <p>
     * 在完成了一笔交易的前提下，进行了第二次买操作；
     * <p>
     * 完成了全部两笔交易。
     * <p>
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/solution/mai-mai-gu-piao-de-zui-jia-shi-ji-iii-by-wrnt/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * <p>
     * ============================分割线，以下内容是我个人的分析=================================
     * <p>
     * 对应上面的几种状态，有以下几种状态转换方程：
     * <p>
     * f0(n)=0
     * f1(n)=max{f1(n-1),-p[n-1]}
     * f2(n)=max{f2(n-1),f1(n-1)+p[n-1]}
     * f3(n)=max{f3(n-1),f2(n-1)-p[n-1]}
     * f4(n)=max{f4(n-1),f3(n-1)+p[n-1]}
     * <p>
     * 由于f0是一个常量，我们不需要存储。最终我们要得到的解是max{0,f2(n),f4(n)}(0次，1次，2次交易)
     * <p>
     * 初始化：
     * f1(1)=-p[0];
     * f2(2)=-p[0]+p[1];
     * f3(3)=-p[0]+p[1]-p[2];
     * f4(4)=-p[0]+p[1]-p[2]+p[3];
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices.length <= 1) {
            return 0;
        }
        int f1 = -prices[0];
        int f2 = Integer.MIN_VALUE, f3 = Integer.MIN_VALUE, f4 = Integer.MIN_VALUE;
        //dp迭代
        for (int i = 1; i < prices.length; i++) {
            int n1 = Math.max(f1, -prices[i]);
            int n2 = Math.max(f2, f1 + prices[i]);
            int n3 = f3;
            if (i >= 2) {
                n3 = Math.max(f3, f2 - prices[i]);
            }
            int n4 = f4;
            if (i >= 3) {
                n4 = Math.max(f4, f3 + prices[i]);
            }
            f1 = n1;
            f2 = n2;
            f3 = n3;
            f4 = n4;
        }
        return Math.max(0, Math.max(f2, f4));
    }
}
