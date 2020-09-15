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
                dp[0][i] = Integer.MAX_VALUE;
            }
        }
        for (int i = 0; i < s.length; i++) {
            dp[i][0] = 0;
        }
        //dp表达式转换
        for (int i = 1; i < s.length; i++) {
            for (int j = 1; j <= w; j++) {
                if (j >= s[i]) {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - s[i]] + s[i]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[s.length - 1][w];
    }
}
