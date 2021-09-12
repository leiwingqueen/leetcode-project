package com.liyongquan.backtrack;

//678. 有效的括号字符串
//给定一个只包含三种字符的字符串：（ ，） 和 *，写一个函数来检验这个字符串是否为有效字符串。有效字符串具有如下规则：
//
//任何左括号 ( 必须有相应的右括号 )。
//任何右括号 ) 必须有相应的左括号 ( 。
//左括号 ( 必须在对应的右括号之前 )。
//* 可以被视为单个右括号 ) ，或单个左括号 ( ，或一个空字符串。
//一个空字符串也被视为有效字符串。
//示例 1:
//
//输入: "()"
//输出: True
//示例 2:
//
//输入: "(*)"
//输出: True
//示例 3:
//
//输入: "(*))"
//输出: True
//注意:
//
//字符串大小将在 [1，100] 范围内。
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/valid-parenthesis-string
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

/**
 * @author liyongquan
 * @date 2021/9/12
 */
public class CheckValidString {
    /**
     * 回溯解法
     * <p>
     * 超时
     *
     * @param s
     * @return
     */
    public boolean checkValidString(String s) {
        int len = s.length();
        return backtrace(s.toCharArray(), 0, len, 0);
    }

    private boolean backtrace(char[] arr, int idx, int len, int lCnt) {
        if (idx == len) {
            return lCnt == 0;
        }
        //提前剪枝，剩余的字符比左括号还少
        if (len - idx < lCnt) {
            return false;
        }
        if (arr[idx] == '(') {
            return backtrace(arr, idx + 1, len, lCnt + 1);
        } else if (arr[idx] == ')') {
            if (lCnt == 0) {
                return false;
            }
            return backtrace(arr, idx + 1, len, lCnt - 1);
        } else {
            //替换成左括号
            if (backtrace(arr, idx + 1, len, lCnt + 1)) {
                return true;
            }
            //替换成右括号
            if (lCnt > 0) {
                if (backtrace(arr, idx + 1, len, lCnt - 1)) {
                    return true;
                }
            }
            //替换成空串
            return backtrace(arr, idx + 1, len, lCnt);
        }
    }

    /**
     * dp解法
     *
     * @param s
     * @return
     */
    public boolean checkValidString2(String s) {
        int len = s.length();
        //第一位是前N个数字，第二位的左括号的数量(每出现一个右括号-1)
        boolean[][] dp = new boolean[len + 1][len + 1];
        dp[0][0] = true;
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j <= i; j++) {
                dp[i][j] = false;
                char ch = s.charAt(i - 1);
                if (ch == '(') {
                    if (j > 0 && dp[i - 1][j - 1]) {
                        dp[i][j] = true;
                    }
                } else if (ch == ')') {
                    if (j < i && dp[i - 1][j + 1]) {
                        dp[i][j] = true;
                    }
                } else {
                    //分别对应的场景 左括号，右括号和空串
                    if (j > 0 && dp[i - 1][j - 1] || j < i && dp[i - 1][j + 1] || dp[i - 1][j]) {
                        dp[i][j] = true;
                    }
                }
            }
        }
        return dp[len][0];
    }
}
