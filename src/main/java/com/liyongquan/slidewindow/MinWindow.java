package com.liyongquan.slidewindow;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 * <p>
 * 注意：如果 s 中存在这样的子串，我们保证它是唯一的答案。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 * 示例 2：
 * <p>
 * 输入：s = "a", t = "a"
 * 输出："a"
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length, t.length <= 105
 * s 和 t 由英文字母组成
 *  
 * <p>
 * 进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-window-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MinWindow {
    /**
     * 滑动窗口算法
     *
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        //统计t的字符
        Map<Character, Integer> tmap = new HashMap<>(t.length());
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            tmap.put(c, tmap.getOrDefault(c, 0) + 1);
        }
        int l = 0, r = 0, min = Integer.MAX_VALUE;
        String window = "";
        Map<Character, Integer> map = new HashMap<>(tmap.size());
        //满足条件的字符数量
        int fullCount = 0;
        while (l <= r && r < s.length()) {
            //右边界移动
            while (fullCount < tmap.size() && r < s.length()) {
                char c = s.charAt(r);
                Integer count = map.getOrDefault(c, 0);
                if (tmap.containsKey(c)) {
                    //刚好满足条件
                    if (count == tmap.get(c) - 1) {
                        fullCount++;
                    }
                    map.put(c, count + 1);
                }
                r++;
            }
            System.out.println("右边界:" + r);
            //没有可达解，可以直接返回
            if (fullCount < tmap.size()) {
                return window;
            }
            //左边界移动
            while (fullCount >= tmap.size() && l <= r) {
                char c = s.charAt(l);
                if (tmap.containsKey(c)) {
                    Integer count = map.getOrDefault(c, 0);
                    //不满足条件
                    if (count == tmap.get(c)) {
                        fullCount--;
                    }
                    map.put(c, count - 1);
                }
                l++;
            }
            System.out.println("左边界:" + l);
            //更新结果
            int len = r - l + 1;
            if (len < min) {
                min = len;
                window = s.substring(l - 1, r);
            }
        }
        return window;
    }
}
