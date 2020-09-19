package com.liyongquan.array;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定两个字符串 s1 和 s2，请编写一个程序，确定其中一个字符串的字符重新排列后，能否变成另一个字符串。
 * <p>
 * 示例 1：
 * <p>
 * 输入: s1 = "abc", s2 = "bca"
 * 输出: true
 * 示例 2：
 * <p>
 * 输入: s1 = "abc", s2 = "bad"
 * 输出: false
 * 说明：
 * <p>
 * 0 <= len(s1) <= 100
 * 0 <= len(s2) <= 100
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/check-permutation-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CheckPermutation {
    public boolean CheckPermutation(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        Map<Character, Integer> map = new HashMap<>(s1.length());
        for (int i = 0; i < s1.length(); i++) {
            Integer count = map.getOrDefault(s1.charAt(i), 0);
            map.put(s1.charAt(i), count + 1);
        }
        for (int i = 0; i < s2.length(); i++) {
            char c = s2.charAt(i);
            Integer count = map.getOrDefault(c, 0);
            if (count <= 0) {
                return false;
            }
            map.put(c, count - 1);
        }
        return true;
    }

    /**
     * 使用bitmap
     *
     * @param s1
     * @param s2
     * @return
     */
    public boolean CheckPermutation2(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        int[] map = new int[128];
        //Map<Character, Integer> map = new HashMap<>(s1.length());
        for (int i = 0; i < s1.length(); i++) {
            char c = s1.charAt(i);
            map[c]++;
        }
        for (int i = 0; i < s2.length(); i++) {
            char c = s2.charAt(i);
            if (map[c] <= 0) {
                return false;
            }
            map[c]--;
        }
        return true;
    }
}
