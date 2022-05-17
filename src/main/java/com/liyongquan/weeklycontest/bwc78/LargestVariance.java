package com.liyongquan.weeklycontest.bwc78;

//2272. 最大波动的子字符串
//字符串的 波动 定义为子字符串中出现次数 最多 的字符次数与出现次数 最少 的字符次数之差。
//
//给你一个字符串 s ，它只包含小写英文字母。请你返回 s 里所有 子字符串的 最大波动 值。
//
//子字符串 是一个字符串的一段连续字符序列。
//
// 
//
//示例 1：
//
//输入：s = "aababbb"
//输出：3
//解释：
//所有可能的波动值和它们对应的子字符串如以下所示：
//- 波动值为 0 的子字符串："a" ，"aa" ，"ab" ，"abab" ，"aababb" ，"ba" ，"b" ，"bb" 和 "bbb" 。
//- 波动值为 1 的子字符串："aab" ，"aba" ，"abb" ，"aabab" ，"ababb" ，"aababbb" 和 "bab" 。
//- 波动值为 2 的子字符串："aaba" ，"ababbb" ，"abbb" 和 "babb" 。
//- 波动值为 3 的子字符串 "babbb" 。
//所以，最大可能波动值为 3 。
//示例 2：
//
//输入：s = "abcde"
//输出：0
//解释：
//s 中没有字母出现超过 1 次，所以 s 中每个子字符串的波动值都是 0 。
// 
//
//提示：
//
//1 <= s.length <= 104
//s  只包含小写英文字母。
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/substring-with-largest-variance
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

public class LargestVariance {
    /**
     * 暂时想不到，先尝试暴力
     * <p>
     * 时间复杂度O(n^3)
     *
     * @param s
     * @return
     */
    public int largestVariance(String s) {
        int len = s.length();
        int res = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                int[] cnt = new int[26];
                for (int k = i; k <= j; k++) {
                    int idx = s.charAt(k) - 'a';
                    cnt[idx]++;
                }
                int max = 0;
                int min = Integer.MAX_VALUE;
                for (int k = 0; k < 26; k++) {
                    if (cnt[k] > 0) {
                        max = Math.max(max, cnt[k]);
                        min = Math.min(min, cnt[k]);
                    }
                }
                res = Math.max(max - min, res);
            }
        }
        return res;
    }

    /**
     * 解法2。
     * <p>
     * 还是没理解
     *
     * @param s
     * @return
     */
    public int largestVariance2(String s) {
        int len = s.length();
        if (len == 1) {
            return 0;
        }
        int res = 0;
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                if (i == j) {
                    continue;
                }
                char a = (char) ('a' + i);
                char b = (char) ('a' + j);
                int sum = 0;
                int diff = 0;
                int min = 0;
                for (int k = 0; k < len; k++) {
                    int val = 0;
                    if (s.charAt(k) == a) {
                        val = 1;
                    } else if (s.charAt(k) == b) {
                        val = -1;
                    }
                    sum += val;
                    diff = Math.max(sum - min, min);
                    min = Math.min(min, sum);
                }
                res = Math.max(diff, res);
            }
        }
        return res;
    }
}
