package com.liyongquan.dp;

import com.liyongquan.math.IntToRoman;

import java.util.HashSet;
import java.util.Set;

/**
 * 664. 奇怪的打印机
 * <p>
 * 有台奇怪的打印机有以下两个特殊要求：
 * <p>
 * 打印机每次只能打印由 同一个字符 组成的序列。
 * 每次可以在任意起始和结束位置打印新字符，并且会覆盖掉原来已有的字符。
 * 给你一个字符串 s ，你的任务是计算这个打印机打印它需要的最少打印次数。
 * <p>
 *  
 * 示例 1：
 * <p>
 * 输入：s = "aaabbb"
 * 输出：2
 * 解释：首先打印 "aaa" 然后打印 "bbb"。
 * 示例 2：
 * <p>
 * 输入：s = "aba"
 * 输出：2
 * 解释：首先打印 "aaa" 然后在第二个位置打印 "b" 覆盖掉原来的字符 'a'。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 100
 * s 由小写英文字母组成
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/strange-printer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class StrangePrinter {
    /**
     * 错误，不能通过
     *
     * @param s
     * @return
     */
    public int strangePrinter(String s) {
        Set<Character> set = new HashSet<>();
        int cnt = 0;
        for (int i = 0; i < s.length(); i++) {
            if (!(i > 0 && s.charAt(i) == s.charAt(i - 1) || set.contains(s.charAt(i)))) {
                cnt++;
            }
            set.add(s.charAt(i));
        }
        return cnt;
    }

    /**
     * 有点难想，但是还不至于想不到。BUT这个DP方程还是看官解的，菜鸡
     *
     * @param s
     * @return
     */
    public int strangePrinter2(String s) {
        int len = s.length();
        int[][] dp = new int[len][len];
        //dp初始化
        for (int i = 0; i < len; i++) {
            dp[i][i] = 1;
        }
        //dp迭代
        for (int i = len - 2; i >= 0; i--) {
            for (int j = i + 1; j < len; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i][j - 1];
                } else {
                    int min = Integer.MAX_VALUE;
                    for (int k = i; k < j; k++) {
                        min = Math.min(min, dp[i][k] + dp[k + 1][j]);
                    }
                    dp[i][j] = min;
                }
            }
        }
        return dp[0][len - 1];
    }
}
