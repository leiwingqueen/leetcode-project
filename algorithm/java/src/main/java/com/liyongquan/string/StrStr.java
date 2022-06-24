package com.liyongquan.string;

//实现 strStr() 函数。
//
// 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。如
//果不存在，则返回 -1 。
//
//
//
// 说明：
//
// 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
//
// 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与 C 语言的 strstr() 以及 Java 的 indexOf() 定义相符。
//
//
//
// 示例 1：
//
//
//输入：haystack = "hello", needle = "ll"
//输出：2
//
//
// 示例 2：
//
//
//输入：haystack = "aaaaa", needle = "bba"
//输出：-1
//
//
// 示例 3：
//
//
//输入：haystack = "", needle = ""
//输出：0
//
//
//
//
// 提示：
//
//
// 0 <= haystack.length, needle.length <= 5 * 104
// haystack 和 needle 仅由小写英文字符组成
//
// Related Topics 双指针 字符串
// 👍 817 👎 0

public class StrStr {
    /**
     * 作弊
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        return haystack.indexOf(needle);
    }

    /**
     * 暴力解法
     * <p>
     * 时间复杂度O(m*n),m是haystack的长度，n是needle的长度
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr2(String haystack, String needle) {
        if (needle.length() == 0) {
            return 0;
        }
        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            int idx = 0;
            while (idx < needle.length() && haystack.charAt(i + idx) == needle.charAt(idx)) {
                idx++;
            }
            if (idx == needle.length()) {
                return i;
            }
        }
        return -1;
    }

    /**
     * kmp算法
     * <p>
     * 时间复杂度O(m+n)
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr3(String haystack, String needle) {
        int len = needle.length();
        if (len == 0) {
            return 0;
        }
        int[][] dp = new int[len][26];
        //构造状态机
        dp[0][needle.charAt(0) - 'a'] = 1;
        int x = 0;
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < 26; j++) {
                //状态推进
                if (needle.charAt(i) - 'a' == j) {
                    dp[i][j] = i + 1;
                } else {
                    dp[i][j] = dp[x][j];
                }
            }
            //这里是关键
            x = dp[x][needle.charAt(i) - 'a'];
        }
        //下面是利用状态机进行查找
        int stat = 0;
        for (int i = 0; i < haystack.length(); i++) {
            stat = dp[stat][haystack.charAt(i) - 'a'];
            if (stat == len) {
                return i - needle.length() + 1;
            }
        }
        return -1;
    }
}
