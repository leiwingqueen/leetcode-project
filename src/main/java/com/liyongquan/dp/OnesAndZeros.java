package com.liyongquan.dp;

/**
 * 在计算机界中，我们总是追求用有限的资源获取最大的收益。
 * <p>
 * 现在，假设你分别支配着 m 个 0 和 n 个 1。另外，还有一个仅包含 0 和 1 字符串的数组。
 * <p>
 * 你的任务是使用给定的 m 个 0 和 n 个 1 ，找到能拼出存在于数组中的字符串的最大数量。每个 0 和 1 至多被使用一次。
 * <p>
 * 注意:
 * <p>
 * 给定 0 和 1 的数量都不会超过 100。
 * 给定字符串数组的长度不会超过 600。
 * 示例 1:
 * <p>
 * 输入: Array = {"10", "0001", "111001", "1", "0"}, m = 5, n = 3
 * 输出: 4
 * <p>
 * 解释: 总共 4 个字符串可以通过 5 个 0 和 3 个 1 拼出，即 "10","0001","1","0" 。
 * 示例 2:
 * <p>
 * 输入: Array = {"10", "0", "1"}, m = 1, n = 1
 * 输出: 2
 * <p>
 * 解释: 你可以拼出 "10"，但之后就没有剩余数字了。更好的选择是拼出 "0" 和 "1" 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ones-and-zeroes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class OnesAndZeros {
    /**
     * dp方程：
     * f(k,m,n)=max{f(k-1,m-S[k][0],n-S[k][1])+1,f(k-1,m,n)}
     * k为前k个字符串能组成的最大组合，S[k][0]，S[k][1]为第k个字符串的0,1个数，
     *
     * @param strs
     * @param m
     * @param n
     * @return
     */
    public int findMaxForm(String[] strs, int m, int n) {
        //统计每个字符串的0,1个数
        int[][] s = new int[strs.length][2];
        for (int i = 0; i < strs.length; i++) {
            int c0 = 0, c1 = 0;
            for (int j = 0; j < strs[i].length(); j++) {
                if (strs[i].charAt(j) == '0') {
                    c0++;
                } else {
                    c1++;
                }
            }
            s[i][0] = c0;
            s[i][1] = c1;
        }
        //初始化
        int[][][] dp = new int[strs.length][m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (s[0][0] <= i && s[0][1] <= j) {
                    dp[0][i][j] = 1;
                }
            }
        }
        //dp迭代
        for (int i = 1; i < strs.length; i++) {
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    if (s[i][0] <= j && s[i][1] <= k) {
                        dp[i][j][k] = Math.max(dp[i - 1][j - s[i][0]][k - s[i][1]] + 1, dp[i - 1][m][n]);
                    } else {
                        dp[i][j][k] = dp[i - 1][m][n];
                    }
                }
            }
        }
        return dp[strs.length - 1][m][n];
    }

    public static void main(String[] args) {
        OnesAndZeros onesAndZeros = new OnesAndZeros();
        /*int maxForm = onesAndZeros.findMaxForm(new String[]{"10", "0001", "111001", "1", "0"},
                5, 3);
        System.out.println(maxForm);*/
        int maxForm1 = onesAndZeros.findMaxForm(new String[]{"10", "0", "1"}, 1, 1);
        System.out.println(maxForm1);
    }
}
