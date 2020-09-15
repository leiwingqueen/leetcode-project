package com.liyongquan.dp;

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
    public int match(int[] s, int w) {
        int[][] dp = new int[s.length][w + 1];
        //初始化
        for (int i = 0; i <= w; i++) {
            if (s[0] >= i) {
                dp[0][i] = s[0];
            } else {
                dp[0][i] = -1;
            }
        }
        for (int i = 0; i < s.length; i++) {
            dp[i][0] = 0;
        }
        //dp表达式转换
        for (int i = 1; i < s.length; i++) {
            for (int j = 1; j <= w; j++) {
                if (j >= s[i]) {
                    //不能直接取min,-1的场景要特殊处理
                    dp[i][j] = dp[i - 1][j];
                    int temp = dp[i - 1][j - s[i]];
                    if (temp > 0 && (dp[i][j] < 0 || temp + s[i] < dp[i][j])) {
                        dp[i][j] = temp + s[i];
                    }
                } else {
                    //j<s[i]，这意味着如果选择s[i]的话，s[i]就是最优解了
                    dp[i][j] = dp[i - 1][j];
                    if (dp[i][j] < 0 || s[i] < dp[i][j]) {
                        dp[i][j] = s[i];
                    }
                }
            }
        }
        return dp[s.length - 1][w];
    }
}
