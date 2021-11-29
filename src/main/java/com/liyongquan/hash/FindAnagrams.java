package com.liyongquan.hash;

import java.util.*;

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

    /**
     * 作者：jiang-mi-2
     * 链接：https://leetcode-cn.com/problems/find-all-anagrams-in-a-string/solution/javayou-hua-labuladongda-lao-hua-dong-chuang-kou-t/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * <p>
     * 增加一个total变量，这个做法比较巧妙
     *
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams2(String s, String p) {
        if (s == null || s.length() == 0) return new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        int[] needs = new int[26]; //由于都是小写字母，因此直接用26个长度的数组代替原来的HashMap
        int[] window = new int[26];
        int left = 0, right = 0, total = p.length(); //用total检测窗口中是否已经涵盖了p中的字符
        for (char ch : p.toCharArray()) {
            needs[ch - 'a']++;
        }
        while (right < s.length()) {
            char chr = s.charAt(right);
            if (needs[chr - 'a'] > 0) {
                window[chr - 'a']++;
                if (window[chr - 'a'] <= needs[chr - 'a']) {
                    total--;
                }
            }
            while (total == 0) {
                if (right - left + 1 == p.length()) {
                    res.add(left);
                }
                char chl = s.charAt(left);
                if (needs[chl - 'a'] > 0) {
                    window[chl - 'a']--;
                    if (window[chl - 'a'] < needs[chl - 'a']) {
                        total++;
                    }
                }
                left++;
            }
            right++;
        }
        return res;
    }

    /**
     * 滑动窗口另外一种写法
     *
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams3(String s, String p) {
        if (s.length() < p.length()) {
            return Collections.emptyList();
        }
        int[] need = new int[26], window = new int[26];
        int satisfy = 0;
        for (int i = 0; i < p.length(); i++) {
            need[p.charAt(i) - 'a']++;
        }
        //滑动窗口
        List<Integer> res = new LinkedList<>();
        int l = 0, r = 0;
        while (r < s.length()) {
            char ch = s.charAt(r);
            window[ch - 'a']++;
            r++;
            if (window[ch - 'a'] <= need[ch - 'a']) {
                satisfy++;
                if (satisfy == p.length()) {
                    res.add(l);
                }
            } else {
                //窗口左边界移动
                while (l < r && window[s.charAt(l) - 'a'] <= need[s.charAt(l) - 'a']) {
                    satisfy--;
                    window[s.charAt(l) - 'a']--;
                    l++;
                }
                if (l < r) {
                    window[s.charAt(l) - 'a']--;
                    l++;
                }
            }
        }
        return res;
    }
}
