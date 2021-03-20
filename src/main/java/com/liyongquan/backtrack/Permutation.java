package com.liyongquan.backtrack;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * 剑指 Offer 38. 字符串的排列
 * <p>
 * 输入一个字符串，打印出该字符串中字符的所有排列。
 * <p>
 *  
 * <p>
 * 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
 * <p>
 *  
 * <p>
 * 示例:
 * <p>
 * 输入：s = "abc"
 * 输出：["abc","acb","bac","bca","cab","cba"]
 *  
 * <p>
 * 限制：
 * <p>
 * 1 <= s 的长度 <= 8
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zi-fu-chuan-de-pai-lie-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Permutation {
    /**
     * 回溯解法
     *
     * @param s
     * @return
     */
    public String[] permutation(String s) {
        Set<String> res = new HashSet<>();
        backtrace(new char[s.length()], s, 0, new boolean[s.length()], res);
        String[] r = new String[res.size()];
        int idx = 0;
        for (String re : res) {
            r[idx++] = re;
        }
        return r;
    }

    private void backtrace(char[] path, String s, int idx, boolean[] visit, Set<String> res) {
        if (idx == s.length()) {
            res.add(new String(path));
        }
        for (int i = 0; i < visit.length; i++) {
            if (!visit[i]) {
                visit[i] = true;
                path[idx] = s.charAt(i);
                backtrace(path, s, idx + 1, visit, res);
                visit[i] = false;
            }
        }
    }
}
