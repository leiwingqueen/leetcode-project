package com.liyongquan.dp;

import lombok.extern.slf4j.Slf4j;

/**
 * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
 * <p>
 * 你可以对一个单词进行如下三种操作：
 * <p>
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 * 示例 2：
 * <p>
 * 输入：word1 = "intention", word2 = "execution"
 * 输出：5
 * 解释：
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/edit-distance
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
@Slf4j
public class MinDistance2 {
    /**
     * 先尝试回溯解法
     * <p>
     * 一个字符一共有4个选项
     * 1.什么都不动
     * 2.插入
     * 3.修改
     * 4.删除
     * <p>
     * 先尝试回溯
     * <p>
     * 超时，不通过
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        return backtrace(word1, word2, 0, 0);
    }

    private int backtrace(String word1, String word2, int idx1, int idx2) {
        //边界场景，只能全部增加或者删除
        if (idx1 == word1.length()) {
            return word2.length() - idx2;
        }
        if (idx2 == word2.length()) {
            return word1.length() - idx1;
        }
        //不操作
        int min = Integer.MAX_VALUE;
        if (word1.charAt(idx1) == word2.charAt(idx2)) {
            min = backtrace(word1, word2, idx1 + 1, idx2 + 1);
        }
        //增/删/改的场景
        min = Math.min(backtrace(word1, word2, idx1, idx2 + 1) + 1, min);
        min = Math.min(backtrace(word1, word2, idx1 + 1, idx2) + 1, min);
        min = Math.min(backtrace(word1, word2, idx1 + 1, idx2 + 1) + 1, min);
        //log.info("idx1:{},idx2:{},min:{}", idx1, idx2, min);
        return min;
    }

    /**
     * 从右下角开始遍历
     * dp表达式
     * 其实从上面的迭代方程就可以知道了
     *
     * 时间复杂度O(m*n)
     * 空间复杂度O(m*n)
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance2(String word1, String word2) {
        int len1 = word1.length(), len2 = word2.length();
        if (len1 == 0) {
            return len2;
        }
        if (len2 == 0) {
            return len1;
        }
        //为了简化边界值的处理，增加一行和一列
        int[][] dp = new int[len1 + 1][len2 + 1];
        //dp初始化
        for (int i = 0; i <= len2; i++) {
            dp[len1][i] = len2 - i;
        }
        for (int i = 0; i <= len1; i++) {
            dp[i][len2] = len1 - i;
        }
        //dp迭代
        for (int i = len1 - 1; i >= 0; i--) {
            for (int j = len2 - 1; j >= 0; j--) {
                int res;
                //修改/不修改
                if (word1.charAt(i) == word2.charAt(j)) {
                    res = dp[i + 1][j + 1];
                } else {
                    res = dp[i + 1][j + 1] + 1;
                }
                //增
                res = Math.min(dp[i][j + 1] + 1, res);
                //删
                res = Math.min(dp[i + 1][j] + 1, res);
                dp[i][j] = res;
                log.info("dp[{}][{}]={}", i, j, res);
            }
        }
        return dp[0][0];
    }
}
