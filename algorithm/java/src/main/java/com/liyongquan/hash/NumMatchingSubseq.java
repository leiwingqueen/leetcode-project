package com.liyongquan.hash;

import java.util.TreeSet;

// 给定字符串 s 和字符串数组 words, 返回  words[i] 中是s的子序列的单词个数 。
//
//字符串的 子序列 是从原始字符串中生成的新字符串，可以从中删去一些字符(可以是none)，而不改变其余字符的相对顺序。
//
//例如， “ace” 是 “abcde” 的子序列。
// 
//
//示例 1:
//
//输入: s = "abcde", words = ["a","bb","acd","ace"]
//输出: 3
//解释: 有三个是 s 的子序列的单词: "a", "acd", "ace"。
//Example 2:
//
//输入: s = "dsahjpjauf", words = ["ahjpjau","ja","ahbwzgqnuk","tnmlanowax"]
//输出: 2
// 
//
//提示:
//
//1 <= s.length <= 5 * 104
//1 <= words.length <= 5000
//1 <= words[i].length <= 50
//words[i]和 s 都只由小写字母组成。
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/number-of-matching-subsequences
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

public class NumMatchingSubseq {
    public int numMatchingSubseq(String s, String[] words) {
        int n = words.length;
        TreeSet<Integer>[] index = new TreeSet[26];
        for (int i = 0; i < 26; i++) {
            index[i] = new TreeSet<>();
        }
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            index[ch - 'a'].add(i);
        }
        int cnt = 0;
        for (String word : words) {
            if (check(index, word)) {
                cnt++;
            }
        }
        return cnt;
    }

    private boolean check(TreeSet<Integer>[] index, String word) {
        int preIdx = -1;
        for (int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';
            Integer ceiling = index[idx].ceiling(preIdx + 1);
            if (ceiling == null) {
                return false;
            }
            preIdx = ceiling;
        }
        return true;
    }
}
