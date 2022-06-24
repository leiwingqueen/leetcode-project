package com.liyongquan.string;

/**
 * 5693. 字符串中第二大的数字
 * <p>
 * 给你一个混合字符串 s ，请你返回 s 中 第二大 的数字，如果不存在第二大的数字，请你返回 -1 。
 * <p>
 * 混合字符串 由小写英文字母和数字组成。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "dfa12321afd"
 * 输出：2
 * 解释：出现在 s 中的数字包括 [1, 2, 3] 。第二大的数字是 2 。
 * 示例 2：
 * <p>
 * 输入：s = "abc1111"
 * 输出：-1
 * 解释：出现在 s 中的数字只包含 [1] 。没有第二大的数字。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 500
 * s 只包含小写英文字母和（或）数字。
 */
public class SecondHighest {
    /**
     * 两个变量，一个最大，一个次大
     *
     * @param s
     * @return
     */
    public int secondHighest(String s) {
        if (s.length() <= 1) {
            return -1;
        }
        int n1 = -1, n2 = -1;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch >= '0' && ch <= '9') {
                int num = ch - '0';
                if (num > n1) {
                    n2 = n1;
                    n1 = num;
                } else if (num > n2 && num < n1) {
                    n2 = num;
                }
            }
        }
        return n2;
    }
}
