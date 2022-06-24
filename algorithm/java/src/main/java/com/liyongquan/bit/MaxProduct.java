package com.liyongquan.bit;

//给定一个字符串数组 words，找到 length(word[i]) * length(word[j]) 的最大值，并且这两个单词不含有公共字母。你可以认为每个单词只包含小写字母。如果不存在这样的两个单词，返回 0。
//
// 
//
//示例 1:
//
//输入: ["abcw","baz","foo","bar","xtfn","abcdef"]
//输出: 16
//解释: 这两个单词为 "abcw", "xtfn"。
//示例 2:
//
//输入: ["a","ab","abc","d","cd","bcd","abcd"]
//输出: 4
//解释: 这两个单词为 "ab", "cd"。
//示例 3:
//
//输入: ["a","aa","aaa","aaaa"]
//输出: 0
//解释: 不存在这样的两个单词。
// 
//
//提示：
//
//2 <= words.length <= 1000
//1 <= words[i].length <= 1000
//words[i] 仅包含小写字母
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/maximum-product-of-word-lengths
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import java.util.Arrays;

/**
 * @author liyongquan
 * @date 2021/11/17
 */
public class MaxProduct {
    /**
     * 26个字符，每个字符是否存在可以用一个bit表示，那么一个字符串可以用一个int来表达。
     * 这样可以大大简化字符串之间是否存在交集的判断的复杂度
     *
     * @param words
     * @return
     */
    public int maxProduct(String[] words) {
        int[] arr = new int[words.length];
        int idx = 0;
        for (String word : words) {
            arr[idx++] = word2int(word);
        }
        int max = 0;
        for (int i = 0; i < words.length - 1; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if ((arr[i] & arr[j]) == 0) {
                    max = Math.max(words[i].length() * words[j].length(), max);
                }
            }
        }
        return max;
    }

    private int word2int(String word) {
        int r = 0;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            r |= (1 << ch - 'a');
        }
        return r;
    }
}
