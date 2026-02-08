package com.liyongquan.backtrack;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

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
@Slf4j
public class Permutation {
    /**
     * 回溯解法
     *
     * @param s
     * @return
     */
    public String[] permutation(String s) {
        List<String> res = new LinkedList<>();
        backtrace(new char[s.length()], s.toCharArray(), 0, res);
        String[] r = new String[res.size()];
        int idx = 0;
        for (String re : res) {
            r[idx++] = re;
        }
        return r;
    }

    private void backtrace(char[] path, char[] s, int idx, List<String> res) {
        if (idx == s.length) {
            res.add(new String(path));
            return;
        }
        //使用交换的方式来减少多余的扫描
        Set<Character> set = new HashSet<>();
        for (int i = idx; i < s.length; i++) {
            //剪枝，保证每个位置的字符只出现一次
            if (set.contains(s[i])) {
                continue;
            }
            set.add(s[i]);
            path[idx] = s[i];
            swap(s, i, idx);
            backtrace(path, s, idx + 1, res);
            //回溯
            swap(s, idx, i);
        }
    }

    private void swap(char[] s, int i, int j) {
        char tmp = s[i];
        s[i] = s[j];
        s[j] = tmp;
    }
}
