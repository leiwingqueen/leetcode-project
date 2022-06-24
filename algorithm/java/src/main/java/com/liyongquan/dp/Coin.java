package com.liyongquan.dp;

/**
 * 硬币。给定数量不限的硬币，币值为25分、10分、5分和1分，编写代码计算n分有几种表示法。(结果可能会很大，你需要将结果模上1000000007)
 * <p>
 * 示例1:
 * <p>
 * 输入: n = 5
 * 输出：2
 * 解释: 有两种方式可以凑成总金额:
 * 5=5
 * 5=1+1+1+1+1
 * <p>
 * 示例2:
 * <p>
 * 输入: n = 10
 * 输出：4
 * 解释: 有四种方式可以凑成总金额:
 * 10=10
 * 10=5+5
 * 10=5+1+1+1+1+1
 * 10=1+1+1+1+1+1+1+1+1+1
 * <p>
 * 说明：
 * <p>
 * 注意:
 * <p>
 * 你可以假设：
 * <p>
 * 0 <= n (总金额) <= 1000000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/coin-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Coin {
    /**
     * n的数据量到百万级，这也就意味我们只能找一个O(n)的解法。
     * <p>
     * 思路有点像被背包问题，组合的问题最终转化成 选/不选的问题。
     * <p>
     * 假设数组int[] c=[25,10,5,1]。
     * <p>
     * 我们有dp表达式：
     * <p>
     * f(k,n)定义为只使用前面k个硬币，组装成n的解法。
     * f(k,n)=f(k-1,n)+f(k,n-c[k])
     * <p>
     * 解释：
     * 不用第k个硬币，只使用前面k-1个硬币--f(k-1,n)
     * 至少用一个第k个硬币。--f(k,n-c[k])
     * <p>
     * 时间复杂度O(4*n)，空间复杂度O(4*n)
     *
     * @param n
     * @return
     */
    public int waysToChange(int n) {
        if (n == 0) {
            return 0;
        }
        int[] coin = new int[]{1, 5, 10, 25};
        int[][] dp = new int[4][n + 1];
        //初始化
        for (int i = 0; i < coin.length; i++) {
            //这里大家可以想象为什么是1而不是0
            dp[i][0] = 1;
        }
        for (int i = 1; i <= n; i++) {
            dp[0][i] = 1;
        }
        //dp迭代
        for (int i = 1; i < coin.length; i++) {
            for (int j = 1; j <= n; j++) {
                if (j >= coin[i]) {
                    dp[i][j] = (dp[i - 1][j] + dp[i][j - coin[i]]) % 1000000007;
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[coin.length - 1][n];
    }

    public static void main(String[] args) {
        Coin coin = new Coin();
        int i = coin.waysToChange(6);
        System.out.println(i);
    }
}
