package com.liyongquan.dp;


/**
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: coins = [1, 2, 5], amount = 11
 * 输出: 3
 * 解释: 11 = 5 + 5 + 1
 * 示例 2:
 * <p>
 * 输入: coins = [2], amount = 3
 * 输出: -1
 *  
 * <p>
 * 说明:
 * 你可以认为每种硬币的数量是无限的。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/coin-change
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CoinChange {
    /**
     * dp方程
     * f(n)=min(f(n-c1),f(n-c2),...,f(n-cn))+1,其中c1~cn为硬币的种类
     * 若n<cn,返回0
     * <p>
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        //边界情况处理
        if (amount == 0) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            if (coins[i] < min) {
                min = coins[i];
            }
        }
        if (amount < min) {
            return -1;
        }
        //边界值初始化
        int[] fn = new int[amount + 1];
        for (int i = 0; i < min; i++) {
            fn[i] = -1;
        }
        //dp表达式
        for (int i = min; i <= amount; i++) {
            fn[i] = dp(fn, i, coins);
        }
        return fn[amount];
    }

    private int dp(int[] fn, int index, int[] coins) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            if (index == coins[i]) {
                return 1;
            }
            //子问题无解
            if (index < coins[i]) {
                continue;
            }
            if (fn[index - coins[i]] > 0 && fn[index - coins[i]] < min) {
                min = fn[index - coins[i]];
            }
        }
        return min == Integer.MAX_VALUE ? -1 : (min + 1);
    }
}
