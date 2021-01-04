package com.liyongquan.dp;

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * <p>
 * 示例 1：
 * <p>
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 * <p>
 * 输入: "cbbd"
 * 输出: "bb"
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Substring {
    /**
     * 暴力+记忆
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        String result = "";
        int[][] subset = new int[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                if (isPalindrome(s, subset, i, j) && (j - i + 1) > result.length()) {
                    result = s.substring(i, j + 1);
                }
            }
        }
        return result;
    }

    private boolean isPalindrome(String s, int[][] subset, int i, int j) {
        if (i >= j) {
            return true;
        }
        if (s.charAt(i) != s.charAt(j)) {
            return false;
        }
        if (subset[i][j] != 0) {
            return subset[i][j] == 1;
        }

        boolean palindrome = isPalindrome(s, subset, i + 1, j - 1);
        subset[i][j] = palindrome ? 1 : 2;
        return palindrome;
    }

    /**
     * 运用dp的思路。f(i,j)是否回文串取决于f(i+1,j-1)是否回文串
     * <p>
     * 时间复杂度O(n^2)
     * 空间复杂度O(n^2)
     *
     * @param s
     * @return
     */
    public String longestPalindrome2(String s) {
        if (s.length() <= 1) {
            return s;
        }
        int len = s.length();
        int[][] dp = new int[len][len];
        String max = "";
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i; j < len; j++) {
                if (i == j || (s.charAt(i) == s.charAt(j) && (i + 1 > j - 1 || dp[i + 1][j - 1] == 1))) {
                    dp[i][j] = 1;
                    if (j - i + 1 > max.length()) {
                        max = s.substring(i, j + 1);
                    }
                }
            }
        }
        return max;
    }

}
