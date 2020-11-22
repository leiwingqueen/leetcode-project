package com.liyongquan.hash;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
 * <p>
 * 字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。
 * <p>
 * 说明：
 * <p>
 * 字母异位词指字母相同，但排列不同的字符串。
 * 不考虑答案输出的顺序。
 * 示例 1:
 * <p>
 * 输入:
 * s: "cbaebabacd" p: "abc"
 * <p>
 * 输出:
 * [0, 6]
 * <p>
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。
 *  示例 2:
 * <p>
 * 输入:
 * s: "abab" p: "ab"
 * <p>
 * 输出:
 * [0, 1, 2]
 * <p>
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的字母异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的字母异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的字母异位词。
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-all-anagrams-in-a-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindAnagrams {
    /**
     * 使用计数器。
     * <p>
     * 为了能够减少每次移动起始索引而导致需要重新统计，我们其实可以利用上一个索引位置的的结果。
     * <p>
     * f(i)=f(i-1)-s[i-1]+s[i+len]
     * <p>
     * f(i)为起始索引为i的计数器，减去上一个起始索引，加上新增加的尾部（滑动窗口）
     * <p>
     * 时间复杂度O(n^2)，空间复杂度O(n)
     *
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {
        if (s.length() < p.length() || p.length() == 0 || s.length() == 0) {
            return Collections.emptyList();
        }
        List<Integer> result = new LinkedList<>();
        int[] bitmap1 = new int[26], bitmap2 = new int[26];
        for (int i = 0; i < p.length(); i++) {
            bitmap1[p.charAt(i) - 'a']++;
        }
        for (int i = 0; i < p.length(); i++) {
            bitmap2[s.charAt(i) - 'a']++;
        }
        if (Arrays.equals(bitmap1, bitmap2)) {
            result.add(0);
        }
        for (int i = 1; i <= s.length() - p.length(); i++) {
            bitmap2[s.charAt(i - 1) - 'a']--;
            bitmap2[s.charAt(i + p.length() - 1) - 'a']++;
            if (Arrays.equals(bitmap1, bitmap2)) {
                result.add(i);
            }
        }
        return result;
    }
}
