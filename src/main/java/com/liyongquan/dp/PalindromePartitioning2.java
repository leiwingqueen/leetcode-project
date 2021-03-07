package com.liyongquan.dp;

/**
 * 132. 分割回文串 II
 * <p>
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是回文。
 * <p>
 * 返回符合要求的 最少分割次数 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "aab"
 * 输出：1
 * 解释：只需一次分割就可将 s 分割成 ["aa","b"] 这样两个回文子串。
 * 示例 2：
 * <p>
 * 输入：s = "a"
 * 输出：0
 * 示例 3：
 * <p>
 * 输入：s = "ab"
 * 输出：1
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 2000
 * s 仅由小写英文字母组成
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-partitioning-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class PalindromePartitioning2 {
    /**
     * 时间复杂度O(n^2)
     *
     * @param s
     * @return
     */
    public int minCut(String s) {
        int len = s.length();
        if (len <= 1) {
            return 0;
        }
        //预处理，计算[i,j]是否回文串
        //f(i,j)=f(i+1,j-1)&&A[i]==A[j]
        int[][] dp = new int[len][len];
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i; j < len; j++) {
                if (i == j || s.charAt(i) == s.charAt(j) && (i + 1 > j - 1 || dp[i + 1][j - 1] == 1)) {
                    dp[i][j] = 1;
                }
            }
        }
        //定义f(n)为前n个数组最小分割数量,f(n)=min{f(n-1),f(n-2),...,f(1)}+1
        int[] dp2 = new int[len];
        dp2[0] = 0;
        for (int i = 1; i < len; i++) {
            if (dp[0][i] == 1) {
                dp2[i] = 0;
            } else {
                int min = len;
                for (int j = i - 1; j >= 0; j--) {
                    if (dp[j + 1][i] == 1) {
                        min = Math.min(min, dp2[j] + 1);
                    }
                }
                dp2[i] = min;
            }
        }
        return dp2[len - 1];
    }
}
