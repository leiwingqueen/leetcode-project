package com.liyongquan.dp;

/**
 * 115. 不同的子序列
 * <p>
 * 给定一个字符串 s 和一个字符串 t ，计算在 s 的子序列中 t 出现的个数。
 * <p>
 * 字符串的一个 子序列 是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。（例如，"ACE" 是 "ABCDE" 的一个子序列，而 "AEC" 不是）
 * <p>
 * 题目数据保证答案符合 32 位带符号整数范围。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "rabbbit", t = "rabbit"
 * 输出：3
 * 解释：
 * 如下图所示, 有 3 种可以从 s 中得到 "rabbit" 的方案。
 * (上箭头符号 ^ 表示选取的字母)
 * rabbbit
 * ^^^^ ^^
 * rabbbit
 * ^^ ^^^^
 * rabbbit
 * ^^^ ^^^
 * 示例 2：
 * <p>
 * 输入：s = "babgbag", t = "bag"
 * 输出：5
 * 解释：
 * 如下图所示, 有 5 种可以从 s 中得到 "bag" 的方案。
 * (上箭头符号 ^ 表示选取的字母)
 * babgbag
 * ^^ ^
 * babgbag
 * ^^    ^
 * babgbag
 * ^    ^^
 * babgbag
 * ^  ^^
 * babgbag
 * ^^^
 *  
 * <p>
 * 提示：
 * <p>
 * 0 <= s.length, t.length <= 1000
 * s 和 t 由英文字母组成
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/distinct-subsequences
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NumDistinct {
    /**
     * 先尝试回溯算法
     * <p>
     * 果然执行超时，哈哈 :)
     *
     * @param s
     * @param t
     * @return
     */
    public int numDistinct(String s, String t) {
        return backtrace(s, t, 0, 0);
    }

    private int backtrace(String s, String t, int idx1, int idx2) {
        if (idx2 == t.length()) {
            return 1;
        }
        if (idx1 == s.length()) {
            return 0;
        }
        //找到第一个满足t[idx2]的字符
        int res = 0;
        for (int i = idx1; i < s.length(); i++) {
            if (s.charAt(i) == t.charAt(idx2)) {
                res += backtrace(s, t, i + 1, idx2 + 1);
            }
        }
        return res;
    }

    /**
     * 回溯写出来了，dp就简单了
     *
     * 性能击败5%
     *
     * 时间复杂度O(len1^2*len2)
     *
     * @param s
     * @param t
     * @return
     */
    public int numDistinct2(String s, String t) {
        int len1 = s.length(), len2 = t.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        //初始化
        for (int i = 1; i <= len2; i++) {
            dp[0][i] = 0;
        }
        for (int i = 0; i <= len1; i++) {
            dp[i][0] = 1;
        }
        //dp迭代
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                int cnt = 0;
                for (int k = 0; k < i; k++) {
                    if (s.charAt(k) == t.charAt(j - 1)) {
                        cnt += dp[k][j - 1];
                    }
                }
                dp[i][j] = cnt;
            }
        }
        return dp[len1][len2];
    }


}
