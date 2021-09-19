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
    public int minSteps(int n) {
        if (n == 1) {
            return 0;
        }
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j <= i; j++) {
                if (j < i) {
                    //粘贴
                    if (i - j >= j) {
                        dp[i][j] = dp[i - j][j] + 1;
                    } else {
                        dp[i][j] = -1;
                    }
                } else {
                    //拷贝
                    int min = Integer.MAX_VALUE;
                    for (int k = 0; k < i; k++) {
                        if (dp[i][k] >= 0) {
                            min = Math.min(min, dp[i][k] + 1);
                        }
                    }
                    dp[i][j] = min == Integer.MAX_VALUE ? -1 : min;
                }
            }
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (dp[n][i] >= 0) {
                res = Math.min(res, dp[n][i]);
            }
        }
        return res;
    }
}
