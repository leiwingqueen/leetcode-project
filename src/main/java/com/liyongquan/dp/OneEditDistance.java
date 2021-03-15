package com.liyongquan.dp;

//给定两个字符串 s 和 t，判断他们的编辑距离是否为 1。
//
// 注意：
//
// 满足编辑距离等于 1 有三种可能的情形：
//
//
// 往 s 中插入一个字符得到 t
// 从 s 中删除一个字符得到 t
// 在 s 中替换一个字符得到 t
//
//
// 示例 1：
//
// 输入: s = "ab", t = "acb"
//输出: true
//解释: 可以将 'c' 插入字符串 s 来得到 t。
//
//
// 示例 2:
//
// 输入: s = "cab", t = "ad"
//输出: false
//解释: 无法通过 1 步操作使 s 变为 t。
//
// 示例 3:
//
// 输入: s = "1203", t = "1213"
//输出: true
//解释: 可以将字符串 s 中的 '0' 替换为 '1' 来得到 t。
// Related Topics 字符串
// 👍 68 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
public class OneEditDistance {
    /**
     * 相当于计算两个单词的编辑距离
     * <p>
     * 假设f(i,j)为s字符串的前i个字符，t字符串的前j个字符的编辑距离
     * <p>
     * 假设s[i]!=s[j]
     * f(i,j)=min{f(i-1,j-1)+1,f(i-1,j)+1,f(i,j-1)+1}
     * 否则：
     * f(i,j)=min{f(i-1,j-1),f(i-1,j)+1,f(i,j-1)+1}
     *
     * 性能击败5%
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isOneEditDistance(String s, String t) {
        int len1 = s.length(), len2 = t.length();
        if (len1 == 0) {
            return len2 == 1;
        }
        if (len2 == 0) {
            return len1 == 1;
        }
        //dp初始化
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 0; i <= len2; i++) {
            dp[0][i] = i;
        }
        for (int i = 0; i <= len1; i++) {
            dp[i][0] = i;
        }
        //dp迭代
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                dp[i][j] = dp[i - 1][j - 1];
                if (s.charAt(i - 1) != t.charAt(j - 1)) {
                    dp[i][j] += 1;
                }
                dp[i][j] = Math.min(dp[i][j], Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1));
            }
        }
        return dp[len1][len2] == 1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

