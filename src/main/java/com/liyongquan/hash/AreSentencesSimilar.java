package com.liyongquan.hash;

import java.util.*;

/**
 * 给定两个句子 words1, words2 （每个用字符串数组表示），和一个相似单词对的列表 pairs ，判断是否两个句子是相似的。
 * <p>
 * 例如，当相似单词对是 pairs = [["great", "fine"], ["acting","drama"], ["skills","talent"]]的时候，"great acting skills" 和 "fine drama talent" 是相似的。
 * <p>
 * 注意相似关系是不具有传递性的。例如，如果 "great" 和 "fine" 是相似的，"fine" 和 "good" 是相似的，但是 "great" 和 "good" 未必是相似的。
 * <p>
 * 但是，相似关系是具有对称性的。例如，"great" 和 "fine" 是相似的相当于 "fine" 和 "great" 是相似的。
 * <p>
 * 而且，一个单词总是与其自身相似。例如，句子 words1 = ["great"], words2 = ["great"], pairs = [] 是相似的，尽管没有输入特定的相似单词对。
 * <p>
 * 最后，句子只会在具有相同单词个数的前提下才会相似。所以一个句子 words1 = ["great"] 永远不可能和句子 words2 = ["doubleplus","good"] 相似。
 * <p>
 *  
 * <p>
 * 注：
 * <p>
 * words1 and words2 的长度不会超过 1000。
 * pairs 的长度不会超过 2000。
 * 每个pairs[i] 的长度为 2。
 * 每个 words[i] 和 pairs[i][j] 的长度范围为 [1, 20]。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sentence-similarity
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class AreSentencesSimilar {
    /**
     * 双map
     *
     * @param sentence1
     * @param sentence2
     * @param similarPairs
     * @return
     */
    public boolean areSentencesSimilar(String[] sentence1, String[] sentence2, List<List<String>> similarPairs) {
        if (sentence1.length != sentence2.length) {
            return false;
        }
        Map<String, Set<String>> m1 = new HashMap<>();
        Map<String, Set<String>> m2 = new HashMap<>();
        for (List<String> pair : similarPairs) {
            Set<String> set1 = m1.getOrDefault(pair.get(0), new HashSet<>());
            set1.add(pair.get(1));
            Set<String> set2 = m2.getOrDefault(pair.get(1), new HashSet<>());
            set2.add(pair.get(0));
        }
        for (int i = 0; i < sentence1.length; i++) {
            String s1 = sentence1[i];
            String s2 = sentence2[i];
            if (!s1.equals(s2) && !m1.getOrDefault(s1, new HashSet<>()).contains(s2)
                    && !m2.getOrDefault(s1, new HashSet<>()).contains(s2)) {
                return false;
            }
        }
        return true;
    }
}
