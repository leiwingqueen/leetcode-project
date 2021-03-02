package com.liyongquan.dp;

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
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        return backtrace(word1, word2, 0, 0, 0);
    }

    private int backtrace(String word1, String word2, int idx1, int idx2, int cnt) {
        //边界场景，只能全部增加或者删除
        if (idx1 == word1.length()) {
            return word2.length() + cnt;
        }
        if (idx2 == word2.length()) {
            return word1.length() + cnt;
        }
        //不操作
        int min = Integer.MAX_VALUE;
        if (word1.charAt(idx1) == word2.charAt(idx2)) {
            min = backtrace(word1, word2, idx1 + 1, idx2 + 1, cnt);
        }
        //增/删/改的场景
        min = Math.min(backtrace(word1, word2, idx1, idx2 + 1, cnt + 1), min);
        min = Math.min(backtrace(word1, word2, idx1 + 1, idx2, cnt + 1), min);
        min = Math.min(backtrace(word1, word2, idx1 + 1, idx2 + 1, cnt + 1), min);
        return cnt + min;
    }
}
