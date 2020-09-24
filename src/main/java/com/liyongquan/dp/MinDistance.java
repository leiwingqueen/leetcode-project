package com.liyongquan.dp;

/**
 * 给定两个单词 word1 和 word2，找到使得 word1 和 word2 相同所需的最小步数，每步可以删除任意一个字符串中的一个字符。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入: "sea", "eat"
 * 输出: 2
 * 解释: 第一步将"sea"变为"ea"，第二步将"eat"变为"ea"
 *  
 * <p>
 * 提示：
 * <p>
 * 给定单词的长度不超过500。
 * 给定单词中的字符只含有小写字母。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/delete-operation-for-two-strings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MinDistance {
    /**
     * 注意：这个问题本质上是求两个字符串的最长公共子序列，并不是求两个字符串的最长公共子串
     * dp方程：
     * 若s1[m-1]==s2[n-1]，证明这个字符不需要删除
     * f(m,n)=f(m-1,n)
     * 否则
     * f(m,n)=min{f(m-1,n),f(m,n-1)}
     * <p>
     * 初始化：
     * f(0,n)=n;
     * f(m,0)=m
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        //初始化
        for (int i = 0; i <= n; i++) {
            dp[0][i] = i;
        }
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        //dp转换
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i][j - 1], dp[i - 1][j]) + 1;
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        MinDistance md = new MinDistance();
        int i = md.minDistance("abcde", "abcee");
        System.out.println(i);
    }
}
