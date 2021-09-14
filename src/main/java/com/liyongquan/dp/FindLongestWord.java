package com.liyongquan.dp;

//524. 通过删除字母匹配到字典里最长单词
//给你一个字符串 s 和一个字符串数组 dictionary 作为字典，找出并返回字典中最长的字符串，该字符串可以通过删除 s 中的某些字符得到。
//
//如果答案不止一个，返回长度最长且字典序最小的字符串。如果答案不存在，则返回空字符串。
//
// 
//
//示例 1：
//
//输入：s = "abpcplea", dictionary = ["ale","apple","monkey","plea"]
//输出："apple"
//示例 2：
//
//输入：s = "abpcplea", dictionary = ["a","b","c"]
//输出："a"
// 
//
//提示：
//
//1 <= s.length <= 1000
//1 <= dictionary.length <= 1000
//1 <= dictionary[i].length <= 1000
//s 和 dictionary[i] 仅由小写英文字母组成
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/longest-word-in-dictionary-through-deleting
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import java.util.List;

/**
 * @author liyongquan
 * @date 2021/9/14
 */
public class FindLongestWord {
    /**
     * dp解法
     * <p>
     * 超时
     *
     * @param s
     * @param dictionary
     * @return
     */
    public String findLongestWord(String s, List<String> dictionary) {
        String res = "";
        for (String dict : dictionary) {
            if (match(s, dict) && (dict.length() > res.length()
                    || dict.length() == res.length() && dict.compareTo(res) < 0)) {
                res = dict;
            }
        }
        return res;
    }

    private boolean match(String s1, String s2) {
        int len1 = s1.length(), len2 = s2.length();
        if (len1 < len2) {
            return false;
        }
        boolean[][] dp = new boolean[len1 + 1][len2 + 1];
        //初始化
        for (int i = 0; i <= len1; i++) {
            dp[i][0] = true;
        }
        //dp迭代
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2 && j <= i; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[len1][len2];
    }

    /**
     * 双指针
     *
     * 通过
     *
     * @param s1
     * @param s2
     * @return
     */
    private boolean match2(String s1, String s2) {
        int len1 = s1.length(), len2 = s2.length();
        if (len1 < len2) {
            return false;
        }
        int l1 = 0, r1 = len1 - 1;
        int l2 = 0, r2 = len2 - 1;
        while (l1 <= r1) {
            if (l2 > r2) {
                return true;
            }
            //左指针移动
            while (l1 <= r1 && s1.charAt(l1) != s2.charAt(l2)) {
                l1++;
            }
            if (l1 > r1) {
                return false;
            }
            l1++;
            l2++;
            if (l2 > r2) {
                return true;
            }
            //右指针移动
            while (l1 <= r1 && s1.charAt(r1) != s2.charAt(r2)) {
                r1--;
            }
            if (l1 > r1) {
                return false;
            }
            r1--;
            r2--;
            if (l2 > r2) {
                return true;
            }
        }
        return false;
    }
}
