package com.liyongquan.slidewindow;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个字符串 s ，找出 至多 包含 k 个不同字符的最长子串 T。
 * <p>
 * 示例 1:
 * <p>
 * 输入: s = "eceba", k = 2
 * 输出: 3
 * 解释: 则 T 为 "ece"，所以长度为 3。
 * 示例 2:
 * <p>
 * 输入: s = "aa", k = 1
 * 输出: 2
 * 解释: 则 T 为 "aa"，所以长度为 2。
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-with-at-most-k-distinct-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LengthOfLongestSubstringKDistinct {
    /**
     * 套滑动窗口模板
     *
     * @param s
     * @param k
     * @return
     */
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        Map<Character, Integer> map = new HashMap<>();
        int l = 0, r = 0, max = 0;
        while (r < s.length()) {
            //窗口右移动
            Integer count = map.getOrDefault(s.charAt(r), 0);
            map.put(s.charAt(r), count + 1);
            r++;
            //更新结果
            if (map.size() <= k) {
                max = r - l;
            } else {
                //窗口平移
                char c = s.charAt(l);
                if (map.get(c) == 1) {
                    map.remove(c);
                } else {
                    map.put(c, map.get(c) - 1);
                }
                l++;
            }
        }
        return max;
    }
}
