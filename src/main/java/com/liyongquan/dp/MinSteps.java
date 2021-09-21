package com.liyongquan.dp;

//650. 只有两个键的键盘
//最初记事本上只有一个字符 'A' 。你每次可以对这个记事本进行两种操作：
//
//Copy All（复制全部）：复制这个记事本中的所有字符（不允许仅复制部分字符）。
//Paste（粘贴）：粘贴 上一次 复制的字符。
//给你一个数字 n ，你需要使用最少的操作次数，在记事本上输出 恰好 n 个 'A' 。返回能够打印出 n 个 'A' 的最少操作次数。
//
// 
//
//示例 1：
//
//输入：3
//输出：3
//解释：
//最初, 只有一个字符 'A'。
//第 1 步, 使用 Copy All 操作。
//第 2 步, 使用 Paste 操作来获得 'AA'。
//第 3 步, 使用 Paste 操作来获得 'AAA'。
//示例 2：
//
//输入：n = 1
//输出：0
// 
//
//提示：
//
//1 <= n <= 1000
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/2-keys-keyboard
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

/**
 * @author liyongquan
 * @date 2021/9/19
 */
public class MinSteps {
    /**
     * f(i,j)表示当前字符为i,最后一次拷贝的字符数量为j
     * <p>
     * f(i,j)，其中j < i，
     * f(i,j)=f(i-j,j)+1,我们有i-j >= j,因此我们有
     * f(i,i)=min{f(i-k,k)}+2,其中k < i,且i-k >= k，即k <= i/2
     * 即粘贴+拷贝
     * <p>
     * 性能击败5%，时间复杂度O(n^2)
     *
     * @param n
     * @return
     */
    public int minSteps(int n) {
        if (n == 1) {
            return 0;
        }
        int[][] dp = new int[n + 1][n + 1];
        dp[1][0] = 0;
        dp[1][1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i / 2; j++) {
                dp[i][j] = -1;
                if (dp[i - j][j] >= 0) {
                    dp[i][j] = dp[i - j][j] + 1;
                }
            }
            for (int j = i / 2 + 1; j < i; j++) {
                dp[i][j] = -1;
            }
            //计算dp[i][i]
            dp[i][i] = -1;
            for (int j = 1; j <= i / 2; j++) {
                if (dp[i - j][j] >= 0 && (dp[i][i] == -1 || dp[i - j][j] + 2 < dp[i][i])) {
                    dp[i][i] = dp[i - j][j] + 2;
                }
            }
        }
        int res = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            if (dp[n][i] >= 0) {
                res = Math.min(res, dp[n][i]);
            }
        }
        return res;
    }

    //TODO:优化方案？数学解法，log(n)?
}
