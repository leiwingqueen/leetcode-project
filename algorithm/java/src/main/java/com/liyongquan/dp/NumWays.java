package com.liyongquan.dp;

//有 k 种颜色的涂料和一个包含 n 个栅栏柱的栅栏，请你按下述规则为栅栏设计涂色方案：
//
//
// 每个栅栏柱可以用其中 一种 颜色进行上色。
// 相邻的栅栏柱 最多连续两个 颜色相同。
//
//
// 给你两个整数 k 和 n ，返回所有有效的涂色 方案数 。
//
//
//
// 示例 1：
//
//
//输入：n = 3, k = 2
//输出：6
//解释：所有的可能涂色方案如上图所示。注意，全涂红或者全涂绿的方案属于无效方案，因为相邻的栅栏柱 最多连续两个 颜色相同。
//
//
// 示例 2：
//
//
//输入：n = 1, k = 1
//输出：1
//
//
// 示例 3：
//
//
//输入：n = 7, k = 2
//输出：42
//
//
//
//
// 提示：
//
//
// 1 <= n <= 50
// 1 <= k <= 105
// 题目数据保证：对于输入的 n 和 k ，其答案在范围 [0, 231 - 1] 内
//
// Related Topics 动态规划
// 👍 107 👎 0

import com.sun.org.apache.xml.internal.dtm.ref.sax2dtm.SAX2DTM2;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NumWays {
    /**
     * 假设f(n,i)为前n个栅栏涂i个颜色的方案
     * <p>
     * 我们有f(n,i)=(f(n-1,i)+f(n-2,i))*(i-1)+(f(n-1,i-1)+f(n-2,i-1))*(k-i+1)
     * <p>
     * 1.不开新颜色，只是用前面的i-1种颜色(只要和前面的颜色不一样即可)
     * (f(n-1,i)+f(n-2,i))*(i-1)
     * 表示倒数最后一个使用前i-1种颜色和倒数最后两个使用前i-1种颜色的方案种类
     * <p>
     * 2.使用新颜色
     * (f(n-1,i-1)+f(n-2,i-1))*(k-i+1)
     * <p>
     * 最后的结果为
     * f(n,1)+f(n,2)+...f(n,k)
     * <p>
     * 初始化：
     * n=1,f(n,k)=k
     * k=1,f(1,k)=1,f(2,k)=1,其它0
     *
     * @param n
     * @param k
     * @return
     */
    public int numWays(int n, int k) {
        if (n == 1) {
            return k;
        }
        int[][] dp = new int[n + 1][k + 1];
        //初始化
        for (int i = 0; i <= k; i++) {
            dp[1][i] = i;
        }
        for (int i = 1; i <= n; i++) {
            if (i <= 2) {
                dp[i][1] = 1;
            } else {
                dp[i][1] = 0;
            }
        }
        for (int i = 2; i <= n; i++) {
            for (int j = 2; j <= k; j++) {
                dp[i][j] = (dp[i - 1][j] + dp[i - 2][j]) * (j - 1) + (dp[i - 1][j - 1] + dp[i - 2][j - 1]) * (k - j + 1);
                log.info("[{},{}]={}", i, j, dp[i][j]);
            }
        }
        //sum结果
        int sum = 0;
        for (int i = 0; i <= k; i++) {
            sum += dp[n][i];
        }
        return sum;
    }
}
