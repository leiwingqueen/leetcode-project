package com.liyongquan.dp;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
 * <p>
 * 返回 s 所有可能的分割方案。
 * <p>
 * 示例:
 * <p>
 * 输入: "aab"
 * 输出:
 * [
 * ["aa","b"],
 * ["a","a","b"]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-partitioning
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class PalindromePartitioning {
    /**
     * 先尝试递归解法
     * <p>
     * 时间复杂度：指数级别
     *
     * @param s
     * @return
     */
    public List<List<String>> partition(String s) {
        return dfs(s, 0, s.length() - 1);
    }

    private List<List<String>> dfs(String s, int start, int end) {
        if (start > end) {
            return Arrays.asList(new LinkedList<>());
        }
        List<List<String>> res = new LinkedList<>();
        for (int i = end; i >= start; i--) {
            if (isPalindrome(s, i, end)) {
                List<List<String>> subRes = dfs(s, start, i - 1);
                for (List<String> subRe : subRes) {
                    subRe.add(s.substring(i, end + 1));
                }
                res.addAll(subRes);
            }
        }
        return res;
    }

    private boolean isPalindrome(String s, int start, int end) {
        if (start > end) {
            return false;
        }
        int l = start, r = end;
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }
}
