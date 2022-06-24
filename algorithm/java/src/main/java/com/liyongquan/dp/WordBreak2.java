package com.liyongquan.dp;

import java.util.*;

/**
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，在字符串中增加空格来构建一个句子，使得句子中所有的单词都在词典中。返回所有这些可能的句子。
 * <p>
 * 说明：
 * <p>
 * 分隔时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 * 示例 1：
 * <p>
 * 输入:
 * s = "catsanddog"
 * wordDict = ["cat", "cats", "and", "sand", "dog"]
 * 输出:
 * [
 *   "cats and dog",
 *   "cat sand dog"
 * ]
 * 示例 2：
 * <p>
 * 输入:
 * s = "pineapplepenapple"
 * wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 * 输出:
 * [
 *   "pine apple pen apple",
 *   "pineapple pen apple",
 *   "pine applepen apple"
 * ]
 * 解释: 注意你可以重复使用字典中的单词。
 * 示例 3：
 * <p>
 * 输入:
 * s = "catsandog"
 * wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出:
 * []
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-break-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class WordBreak2 {
    /**
     * f(i)=f(j)&&check(j,i),0<=j<i
     * <p>
     * 超出内存限制
     *
     * @param s
     * @param wordDict
     * @return
     */
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        //初始化
        List<String>[] dp = new List[s.length() + 1];
        dp[0] = Collections.emptyList();
        //dp迭代
        for (int i = 1; i <= s.length(); i++) {
            List<String> r = new LinkedList<>();
            for (String word : wordDict) {
                if (i >= word.length()) {
                    int index = lastIndex(s.substring(0, i), word);
                    if (index == 0) {
                        r.add(word);
                    } else if (index > 0 && dp[index].size() > 0) {
                        for (String s1 : dp[index]) {
                            r.add(s1 + " " + word);
                        }
                    }
                }
            }
            dp[i] = r;
        }
        return dp[s.length()];
    }

    /**
     * 超出最大内存，我们尝试下只保存最后一个单词的下标如何
     *
     * @param s
     * @param wordDict
     * @return
     */
    public List<String> wordBreak2(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        //初始化
        List<Integer>[] dp = new List[s.length() + 1];
        dp[0] = Collections.emptyList();
        //dp迭代
        for (int i = 1; i <= s.length(); i++) {
            List<Integer> r = new LinkedList<>();
            for (int j = 0, wordDictSize = wordDict.size(); j < wordDictSize; j++) {
                String word = wordDict.get(j);
                if (i >= word.length()) {
                    int index = lastIndex(s.substring(0, i), word);
                    if (index == 0 || (index > 0 && dp[index].size() > 0)) {
                        r.add(j);
                    }
                }
            }
            dp[i] = r;
        }
        //组装结果
        return getResult(dp, s.length(), wordDict);
    }

    private List<String> getResult(List<Integer>[] dp, int n, List<String> wordDict) {
        if (dp[n].size() == 0) {
            return Collections.emptyList();
        }
        List<String> result = new LinkedList<>();
        for (Integer idx : dp[n]) {
            List<String> pre = getResult(dp, n - wordDict.get(idx).length(), wordDict);
            if (pre.size() == 0) {
                result.add(wordDict.get(idx));
            }else {
                for (String s : pre) {
                    result.add(s + " " + wordDict.get(idx));
                }
            }
        }
        return result;
    }

    private int lastIndex(String str, String word) {
        if (str.length() < word.length()) {
            return -1;
        }
        int p1 = str.length() - 1, p2 = word.length() - 1;
        while (p2 >= 0 && str.charAt(p1) == word.charAt(p2)) {
            p1--;
            p2--;
        }
        return p2 < 0 ? (p1 + 1) : -1;
    }
}
