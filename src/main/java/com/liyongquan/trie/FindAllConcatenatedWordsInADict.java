package com.liyongquan.trie;

import java.util.LinkedList;
import java.util.List;

//给你一个 不含重复 单词的字符串数组 words ，请你找出并返回 words 中的所有 连接词 。
//
//连接词 定义为：一个完全由给定数组中的至少两个较短单词组成的字符串。
//
// 
//
//示例 1：
//
//输入：words = ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
//输出：["catsdogcats","dogcatsdog","ratcatdogcat"]
//解释："catsdogcats" 由 "cats", "dog" 和 "cats" 组成;
//     "dogcatsdog" 由 "dog", "cats" 和 "dog" 组成;
//     "ratcatdogcat" 由 "rat", "cat", "dog" 和 "cat" 组成。
//示例 2：
//
//输入：words = ["cat","dog","catdog"]
//输出：["catdog"]
// 
//
//提示：
//
//1 <= words.length <= 104
//0 <= words[i].length <= 1000
//words[i] 仅由小写字母组成
//0 <= sum(words[i].length) <= 105
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/concatenated-words
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

public class FindAllConcatenatedWordsInADict {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        TrieTreeExt tree = new TrieTreeExt();
        for (String word : words) {
            tree.add(word);
        }
        List<String> res = new LinkedList<>();
        for (String word : words) {
            if (check(word, tree) > 1) {
                res.add(word);
            }
        }
        return res;
    }

    private int check(String word, TrieTreeExt tree) {
        List<String> match = tree.findMatch(word);
        int res = 0;
        for (String s : match) {
            if (word.length() == s.length()) {
                if (res < 1) {
                    res = 1;
                }
            }
            String sub = word.substring(s.length());
            int r = check(sub, tree);
            if (r > 0) {
                res = Math.max(res, r + 1);
            }
        }
        return res;
    }

    static class TrieTreeExt extends TrieTree {
        //查找word中满足的子串
        List<String> findMatch(String word) {
            TrieNode cur = this.root;
            StringBuilder sb = new StringBuilder();
            List<String> list = new LinkedList<>();
            int idx = 0;
            while (idx < word.length() && cur.child[word.charAt(idx) - 'a'] != null) {
                cur = cur.child[word.charAt(idx) - 'a'];
                sb.append(word.charAt(idx));
                if (cur.end) {
                    list.add(sb.toString());
                }
                idx++;
            }
            return list;
        }
    }
}
