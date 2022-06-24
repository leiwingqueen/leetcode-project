package com.liyongquan.slidewindow;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
 * <p>
 * 换句话说，第一个字符串的排列之一是第二个字符串的子串。
 * <p>
 * 示例1:
 * <p>
 * 输入: s1 = "ab" s2 = "eidbaooo"
 * 输出: True
 * 解释: s2 包含 s1 的排列之一 ("ba").
 *  
 * <p>
 * 示例2:
 * <p>
 * 输入: s1= "ab" s2 = "eidboaoo"
 * 输出: False
 *  
 * <p>
 * 注意：
 * <p>
 * 输入的字符串只包含小写字母
 * 两个字符串的长度都在 [1, 10,000] 之间
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutation-in-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CheckInclusion {
    /**
     * 滑动窗口算法
     * <p>
     * 初始化一个map
     *
     * @param s1
     * @param s2
     * @return
     */
    public boolean checkInclusion(String s1, String s2) {
        //初始化s2的map
        Map<Character, Integer> need = new HashMap<>(s1.length());
        for (int i = 0; i < s1.length(); i++) {
            char ch = s1.charAt(i);
            Integer c = need.getOrDefault(ch, 0);
            need.put(ch, c + 1);
        }
        Map<Character, Integer> actual = new HashMap<>(need.size());
        int l = 0, r = 0;
        int fullCount = 0;
        while (r < s2.length()) {
            //窗口扩张
            char ch = s2.charAt(r);
            if (need.containsKey(ch)) {
                Integer c = actual.getOrDefault(ch, 0);
                actual.put(ch, c + 1);
                if (c + 1 == need.get(ch)) {
                    fullCount++;
                }
            }
            r++;
            if (r - l == s1.length()) {
                if (fullCount == need.size()) {
                    return true;
                }
                char ch2 = s2.charAt(l);
                //窗口平移
                if (need.containsKey(ch2)) {
                    Integer c = actual.getOrDefault(ch2, 0);
                    actual.put(ch2, c - 1);
                    if (c == need.get(ch2)) {
                        fullCount--;
                    }
                }
                l++;
            }
        }
        return false;
    }
}
