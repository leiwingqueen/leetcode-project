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
     * <p>
     * 性能击败5%
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

    /**
     * 既然递归能实现，我们使用dp也能实现
     * <p>
     * 时间复杂度O(n^3)
     * <p>
     * 性能一样糟糕。。果然没有任何优化
     *
     * @param s
     * @return
     */
    public List<List<String>> partition2(String s) {
        if (s.length() == 0) {
            return Collections.emptyList();
        }
        Partition[] dp = new Partition[s.length() + 1];
        dp[0] = new Partition(Arrays.asList(new LinkedList<>()));
        dp[1] = new Partition(Arrays.asList(Arrays.asList(s.substring(0, 1))));
        for (int i = 2; i <= s.length(); i++) {
            List<List<String>> res = new LinkedList<>();
            for (int j = i - 1; j >= 0; j--) {
                if (isPalindrome(s, j, i - 1)) {
                    Partition par = dp[j];
                    Partition clone = par.clone();
                    clone.add(s.substring(j, i));
                    res.addAll(clone.par);
                }
            }
            dp[i] = new Partition(res);
        }
        return dp[s.length()].par;
    }

    /**
     * 这个方法的时间复杂度比较高，我们可以使用dp的思路用空间换时间
     *
     * @param s
     * @param start
     * @param end
     * @return
     */
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

    private static class Partition implements Cloneable {
        List<List<String>> par;

        public Partition(List<List<String>> par) {
            this.par = par;
        }

        public void add(String s) {
            for (List<String> item : par) {
                item.add(s);
            }
        }

        @Override
        protected Partition clone() {
            List<List<String>> obj = new LinkedList<>();
            for (List<String> strings : this.par) {
                List<String> list = new LinkedList<>();
                for (String s : strings) {
                    list.add(s);
                }
                obj.add(list);
            }
            return new Partition(obj);
        }
    }

    /**
     * 空间换时间，减少回文串判断的时间复杂度
     *
     * 崩溃了。还是只能击败5%
     *
     * @param s
     * @return
     */
    public List<List<String>> partition3(String s) {
        int len = s.length();
        //空间换时间，减少判断回文串的时间复杂度
        int[][] dp = new int[len][len];
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i; j < len; j++) {
                if (j == i || (s.charAt(i) == s.charAt(j) && (i + 1 > j - 1 || dp[i + 1][j - 1] == 1))) {
                    dp[i][j] = 1;
                }
            }
        }
        return dfs(s, 0, s.length() - 1, dp);
    }

    private List<List<String>> dfs(String s, int start, int end, int[][] dp) {
        if (start > end) {
            return Arrays.asList(new LinkedList<>());
        }
        List<List<String>> res = new LinkedList<>();
        for (int i = end; i >= start; i--) {
            if (dp[i][end] == 1) {
                List<List<String>> subRes = dfs(s, start, i - 1, dp);
                for (List<String> subRe : subRes) {
                    subRe.add(s.substring(i, end + 1));
                }
                res.addAll(subRes);
            }
        }
        return res;
    }
}
