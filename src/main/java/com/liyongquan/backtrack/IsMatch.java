package com.liyongquan.backtrack;

//请实现一个函数用来匹配包含'. '和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（含0次）。在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但与"aa.a"和"ab*a"均不匹配。
//
//示例 1:
//
//输入:
//s = "aa"
//p = "a"
//输出: false
//解释: "a" 无法匹配 "aa" 整个字符串。
//示例 2:
//
//输入:
//s = "aa"
//p = "a*"
//输出: true
//解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
//示例 3:
//
//输入:
//s = "ab"
//p = ".*"
//输出: true
//解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
//示例 4:
//
//输入:
//s = "aab"
//p = "c*a*b"
//输出: true
//解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
//示例 5:
//
//输入:
//s = "mississippi"
//p = "mis*is*p*."
//输出: false
//s 可能为空，且只包含从 a-z 的小写字母。
//p 可能为空，且只包含从 a-z 的小写字母以及字符 . 和 *，无连续的 '*'。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/zheng-ze-biao-da-shi-pi-pei-lcof
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

/**
 * @author liyongquan
 * @date 2021/10/12
 */
public class IsMatch {
    /**
     * 回溯解法
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        return backtrace(s.toCharArray(), p.toCharArray(), 0, 0);
    }

    private boolean backtrace(char[] s, char[] p, int idx1, int idx2) {
        if (idx1 == s.length && idx2 == p.length) {
            return true;
        }
        if (idx2 >= p.length) {
            return false;
        }
        if (idx1 < s.length && (s[idx1] == p[idx2] || p[idx2] == '.')) {
            //后面带*，有两种场景
            if (idx2 + 1 < p.length && p[idx2 + 1] == '*') {
                //分别对应出现一次，一次都不出现，下一次继续出现
                if (backtrace(s, p, idx1 + 1, idx2 + 2) || backtrace(s, p, idx1, idx2 + 2)
                        || backtrace(s, p, idx1 + 1, idx2)) {
                    return true;
                }
            } else {
                if (backtrace(s, p, idx1 + 1, idx2 + 1)) {
                    return true;
                }
            }
        } else {
            if (idx2 + 1 < p.length && p[idx2 + 1] == '*') {
                if (backtrace(s, p, idx1, idx2 + 2)) {
                    return true;
                }
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * dp解法
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch2(String s, String p) {
        char[] s1 = s.toCharArray();
        char[] s2 = p.toCharArray();
        boolean[][] dp = new boolean[s1.length + 1][s2.length + 1];
        //初始化第一列
        dp[0][0] = true;
        for (int i = 1; i <= s1.length; i++) {
            dp[i][0] = false;
        }
        //初始化第一行
        for (int i = 1; i <= s2.length; i++) {
            if (s2[i - 1] == '*') {
                dp[0][i] = dp[0][i - 2];
            } else {
                dp[0][i] = false;
            }
        }
        //dp迭代
        for (int i = 1; i <= s1.length; i++) {
            for (int j = 1; j <= s2.length; j++) {
                if (s2[j - 1] != '*') {
                    dp[i][j] = (s1[i - 1] == s2[j - 1] || s2[j - 1] == '.') && dp[i - 1][j - 1];
                } else {
                    //出现0次
                    if (dp[i][j - 2]) {
                        dp[i][j] = true;
                    } else {
                        //上一个数字相同
                        if (s1[i - 1] == s2[j - 2] || s2[j - 2] == '.') {
                            //1次或以上
                            dp[i][j] = dp[i - 1][j - 2] || dp[i - 1][j];
                        }
                    }
                }
            }
        }
        return dp[s1.length][s2.length];
    }
}