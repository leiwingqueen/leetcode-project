package com.liyongquan.greedy;


import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。
 * <p>
 * 在构造过程中，请注意区分大小写。比如 "Aa" 不能当做一个回文字符串。
 * <p>
 * 注意:
 * 假设字符串的长度不会超过 1010。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * "abccccdd"
 * <p>
 * 输出:
 * 7
 * <p>
 * 解释:
 * 我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-palindrome
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LongestPalindrome {
    /**
     * 暴力解法
     * <p>
     * 时间复杂度：遍历全部场景O(n^2)，每次判断是否回文串还需要额外增加O(n)的复杂度，总的复杂度为O(n^3)
     *
     * @param s
     * @return
     */
    public int longestPalindrome(String s) {
        int max = 1;
        for (int i = 0; i < s.length(); i++) {
            int m = 1;
            for (int j = s.length() - 1; j >= i; j--) {
                if (isPalindrome(s, i, j)) {
                    m = j - i + 1;
                    break;
                }
            }
            max = Math.max(max, m);
        }
        return max;
    }

    private boolean isPalindrome(String s, int start, int end) {
        if (start == end) {
            return true;
        }
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    public int longestPalindrome2(String s) {
        Map<Character, Integer> map = new HashMap<>(s.length());
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        int middle = 0;
        int side = 0;
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() % 2 == 0) {
                side += entry.getValue();
            } else {
                side += entry.getValue() - 1;
                middle = 1;
            }
        }
        return side + middle;
    }

    /**
     * 标准ascii码只有7位，也就是0~127，直接改为使用bitmap进行存储
     *
     * @param s
     * @return
     */
    public int longestPalindrome3(String s) {
        int[] bitmap = new int[128];
        for (int i = 0; i < s.length(); i++) {
            bitmap[s.charAt(i)]++;
        }
        int middle = 0;
        int side = 0;
        for (int i = 0; i < bitmap.length; i++) {
            if (bitmap[i] % 2 == 0) {
                side += bitmap[i];
            } else {
                side += bitmap[i] - 1;
                middle = 1;
            }
        }
        return side + middle;
    }
}
