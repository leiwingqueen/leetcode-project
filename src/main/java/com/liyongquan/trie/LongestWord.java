package com.liyongquan.trie;

import java.util.HashSet;
import java.util.Set;

/**
 * 给出一个字符串数组words组成的一本英语词典。从中找出最长的一个单词，该单词是由words词典中其他单词逐步添加一个字母组成。若其中有多个可行的答案，则返回答案中字典序最小的单词。
 * <p>
 * 若无答案，则返回空字符串。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：
 * words = ["w","wo","wor","worl", "world"]
 * 输出："world"
 * 解释：
 * 单词"world"可由"w", "wo", "wor", 和 "worl"添加一个字母组成。
 * 示例 2：
 * <p>
 * 输入：
 * words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
 * 输出："apple"
 * 解释：
 * "apply"和"apple"都能由词典中的单词组成。但是"apple"的字典序小于"apply"。
 *  
 * <p>
 * 提示：
 * <p>
 * 所有输入的字符串都只包含小写字母。
 * words数组长度范围为[1,1000]。
 * words[i]的长度范围为[1,30]。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-word-in-dictionary
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LongestWord {
    /**
     * 暴力解法
     * <p>
     * 时间复杂度O(n*l),空间复杂度O(n)
     *
     * @param words
     * @return
     */
    public String longestWord(String[] words) {
        //所有字符放入set
        Set<String> set = new HashSet<>(words.length);
        for (String word : words) {
            set.add(word);
        }
        //扫描所有数字
        String res = "";
        for (String word : words) {
            if (hasAllPrefix(word, set)) {
                if (word.length() > res.length() || (word.length() == res.length() && word.compareTo(res) < 0)) {
                    res = word;
                }
            }
        }
        return res;
    }

    private boolean hasAllPrefix(String word, Set<String> dict) {
        if (word.length() == 1) {
            return true;
        }
        for (int i = 1; i < word.length(); i++) {
            String prefix = word.substring(0, i);
            if (!dict.contains(prefix)) {
                return false;
            }
        }
        return true;
    }
}
