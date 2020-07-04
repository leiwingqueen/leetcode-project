package com.liyongquan.slidewindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * <p>
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * <p>
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *  
 * <p>
 * 提示：
 * <p>
 * s.length <= 40000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zui-chang-bu-han-zhong-fu-zi-fu-de-zi-zi-fu-chuan-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LongestSubstring {
    /**
     * 暴力解法
     * 时间复杂度 O(n^2)
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            Set<Character> set = new HashSet<>();
            int l = 0;
            for (int j = i; j < s.length(); j++) {
                char c = s.charAt(j);
                if (set.contains(c)) {
                    break;
                }
                l++;
                set.add(c);
            }
            if (l > max) {
                max = l;
            }
        }
        return max;
    }

    /**
     * 滑动窗口
     * ****写得太糙了
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring2(String s) {
        if (s.length() <= 0) {
            return 0;
        }
        int max = 0;
        int head = 0, tail = 0;
        Set<Character> set = new HashSet<>(s.length());
        while (tail <= s.length() - 1) {
            char c = s.charAt(tail);
            //移动尾指针
            if (!set.contains(c)) {
                tail++;
                set.add(c);
                //最后结点特殊处理
                if (tail == s.length()) {
                    int len = tail - head;
                    if (len > max) {
                        max = len;
                    }
                }
            } else {
                int len = tail - head;
                if (len > max) {
                    max = len;
                }
                //移动头指针
                while (s.charAt(head) != c) {
                    set.remove(s.charAt(head));
                    head++;
                }
                set.remove(s.charAt(head));
                head++;
            }
        }
        return max;
    }
}
