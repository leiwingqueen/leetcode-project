package com.liyongquan.slidewindow;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个字符串 s ，找出 至多 包含两个不同字符的最长子串 t ，并返回该子串的长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "eceba"
 * 输出: 3
 * 解释: t 是 "ece"，长度为3。
 * 示例 2:
 * <p>
 * 输入: "ccaabbb"
 * 输出: 5
 * 解释: t 是 "aabbb"，长度为5。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-with-at-most-two-distinct-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LengthOfLongestSubstringTwoDistinct {
    /**
     * 滑动窗口算法
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int l = 0, r = 0, max = 0;
        Map<Character, Integer> map = new HashMap<>(3);
        while (l <= r && r < s.length()) {
            //尝试不断右移，找到右边的最大边界
            while (r < s.length() && map.size() <= 2) {
                Integer count = map.getOrDefault(s.charAt(r), 0);
                map.put(s.charAt(r), count + 1);
                if (map.size() > 2) {
                    break;
                }
                r++;
            }
            System.out.println("右边界移动:" + r);
            //到达右边界，目前值即为最大值
            int len = r - l;
            if (len > max) {
                max = len;
            }
            if (r == s.length()) {
                return max;
            }
            //左边界移动
            while (l < r && map.size() > 2) {
                Integer count = map.getOrDefault(s.charAt(l), 0);
                if (count <= 1) {
                    map.remove(s.charAt(l));
                } else {
                    map.put(s.charAt(l), count - 1);
                }
                l++;
            }
            System.out.println("左边界移动:" + l);
            //更新max
            len = r - l + 1;
            if (len > max) {
                max = len;
            }
            r++;
        }
        return max;
    }

    /**
     * 优化，这样左移的效率比较低。假如我们只存储每个字符的最右的坐标，这样移动的效率会更高
     *
     * 诡异的是，在leetcode的测试数据集中这个效率还不如上面的解法
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstringTwoDistinct2(String s) {
        int l = 0, r = 0, max = 0;
        Map<Character, Integer> map = new HashMap<>(3);
        while (l <= r && r < s.length()) {
            //尝试不断右移，找到右边的最大边界
            while (r < s.length() && map.size() <= 2) {
                map.put(s.charAt(r), r);
                if (map.size() > 2) {
                    break;
                }
                r++;
            }
            System.out.println("右边界移动:" + r);
            //到达右边界，目前值即为最大值
            int len = r - l;
            if (len > max) {
                max = len;
            }
            if (r == s.length()) {
                return max;
            }
            //左边界移动
            Integer delIdx = Collections.min(map.values());
            map.remove(s.charAt(delIdx));
            l = delIdx + 1;
            System.out.println("左边界移动:" + l);
            //更新max
            len = r - l + 1;
            if (len > max) {
                max = len;
            }
            r++;
        }
        return max;
    }
}

