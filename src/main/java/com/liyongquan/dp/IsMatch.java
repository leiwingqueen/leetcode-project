package com.liyongquan.dp;

//给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
//
//
// '.' 匹配任意单个字符
// '*' 匹配零个或多个前面的那一个元素
//
//
// 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
//
//
// 示例 1：
//
//
//输入：s = "aa" p = "a"
//输出：false
//解释："a" 无法匹配 "aa" 整个字符串。
//
//
// 示例 2:
//
//
//输入：s = "aa" p = "a*"
//输出：true
//解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
//
//
// 示例 3：
//
//
//输入：s = "ab" p = ".*"
//输出：true
//解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
//
//
// 示例 4：
//
//
//输入：s = "aab" p = "c*a*b"
//输出：true
//解释：因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
//
//
// 示例 5：
//
//
//输入：s = "mississippi" p = "mis*is*p*."
//输出：false
//
//
//
// 提示：
//
//
// 0 <= s.length <= 20
// 0 <= p.length <= 30
// s 可能为空，且只包含从 a-z 的小写字母。
// p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
// 保证每次出现字符 * 时，前面都匹配到有效的字符
//
// Related Topics 字符串 动态规划 回溯算法
// 👍 1944 👎 0

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IsMatch {
    /**
     * 先尝试回溯解法
     * <p>
     * 性能击败5%
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        return backtrace(s, p, 0, 0);
    }

    private boolean backtrace(String s, String p, int idx1, int idx2) {
        log.info("s:{},p:{}", idx1 >= s.length() ? "" : s.substring(idx1), idx2 >= p.length() ? "" : p.substring(idx2));
        if (idx2 == p.length()) {
            return idx1 == s.length();
        }
        boolean next = idx2 + 1 < p.length() && p.charAt(idx2 + 1) == '*';
        if (idx1 == s.length() || p.charAt(idx2) != '.' && s.charAt(idx1) != p.charAt(idx2)) {
            //c*b可以认为和b等价，消除掉前面的c*
            if (next) {
                return backtrace(s, p, idx1, idx2 + 2);
            } else {
                return false;
            }
        } else {
            if (next) {
                //aab和a*b
                return backtrace(s, p, idx1 + 1, idx2) ||
                        //ab和a*b
                        backtrace(s, p, idx1 + 1, idx2 + 2) ||
                        //a和a*a
                        backtrace(s, p, idx1, idx2 + 2);
            } else {
                return backtrace(s, p, idx1 + 1, idx2 + 1);
            }
        }
    }

    /**
     * dp解法
     * <p>
     * f(i,j)为s的前i个字符和p的前j个字符是否匹配
     * 初始化：
     * if j==0: f(0,0)=true,其他false
     * if j==1
     * p[0]=='*',则直接是false
     * i>1,f(i,1)=false
     * i==1,f(i,1)=s[0]==p[0]||p[0]=='.'
     * <p>
     * dp迭代：
     * if p[j-1]!='*'
     * f(i,j)=f(i-1,j-1)&&(s[i-1]==p[j-1]||p[j-1]=='.')
     * if p[j-1]=='*'
     * 分别对应的场景是出现0次，1次和多次
     * f(i,j)=f(i,j-2)||((p[j-2]=='.'||p[j-2]==s[i-1])&&(f(i-1,j-2)||f(i-1,j)))
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch2(String s, String p) {
        int len1 = s.length(), len2 = p.length();
        if (len2 == 0) {
            return len1 == 0;
        }
        boolean[][] dp = new boolean[len1 + 1][len2 + 1];
        //dp初始化
        dp[0][0] = true;
        dp[0][1] = false;
        //i=0的场景初始化
        for (int i = 2; i <= len2; i++) {
            dp[0][i] = p.charAt(i - 1) == '*' && dp[0][i - 2];
        }
        if (len1 == 0) {
            return dp[0][len2];
        }
        //j=1的场景初始化
        if (p.charAt(0) != '*') {
            dp[1][1] = s.charAt(0) == p.charAt(0) || p.charAt(0) == '.';
        }
        //dp迭代
        for (int i = 1; i <= len1; i++) {
            for (int j = 2; j <= len2; j++) {
                if (p.charAt(j - 1) != '*') {
                    dp[i][j] = dp[i - 1][j - 1] && (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.');
                } else {
                    //0次，1次和多次
                    dp[i][j] = dp[i][j - 2] ||
                            (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.') && (dp[i - 1][j - 2] || dp[i - 1][j]);
                }
                log.info("s:{},p:{},result:{}", s.substring(0, i), p.substring(0, j), dp[i][j]);
            }
        }
        return dp[len1][len2];
    }
}
