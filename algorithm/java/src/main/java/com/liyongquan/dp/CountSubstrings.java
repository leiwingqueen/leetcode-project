package com.liyongquan.dp;

/**
 * 647. 回文子串
 * <p>
 * <p>
 * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
 * <p>
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入："abc"
 * 输出：3
 * 解释：三个回文子串: "a", "b", "c"
 * 示例 2：
 * <p>
 * 输入："aaa"
 * 输出：6
 * 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
 *  
 * <p>
 * 提示：
 * <p>
 * 输入的字符串长度不会超过 1000 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindromic-substrings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CountSubstrings {
    /**
     * 先尝试暴力解法
     *
     * @param s
     * @return
     */
    public int countSubstrings(String s) {
        int len = s.length();
        if (len == 0) {
            return 0;
        }
        int res = len;
        for (int i = 2; i <= len; i++) {
            for (int j = 0; j <= len - i; j++) {
                if (isPalindromic(s.substring(j, j + i))) {
                    res++;
                }
            }
        }
        return res;
    }

    private boolean isPalindromic(String s) {
        int l = 0, r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l++) != s.charAt(r--)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 我们考虑如何降低回文串的判断效率
     * <p>
     * 假设f(i,j)为当前字符是否回文串，i<=j
     * <p>
     * f(i,j)=f(i+1,j-1)&&s[i]==s[j]
     * <p>
     * 那么我们可以使用dp来降低回文串的计算效率
     * <p>
     * 需要注意的是由于dp迭代是基于左下方的点决定的，因此我们需要从下往上迭代
     *
     * @param s
     * @return
     */
    public int countSubstrings2(String s) {
        int len = s.length();
        if (len == 0) {
            return 0;
        }
        boolean[][] dp = new boolean[len][len];
        int res = 0;
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i; j < len; j++) {
                if (s.charAt(i) == s.charAt(j) && (i + 1 > j - 1 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                    res++;
                }
            }
        }
        return res;
    }
}
