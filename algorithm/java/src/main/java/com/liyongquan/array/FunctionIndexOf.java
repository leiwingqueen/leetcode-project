package com.liyongquan.array;

/**
 * 实现 strStr() 函数。
 * <p>
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
 * <p>
 * 示例 1:
 * <p>
 * 输入: haystack = "hello", needle = "ll"
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: haystack = "aaaaa", needle = "bba"
 * 输出: -1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/implement-strstr
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FunctionIndexOf {
    /**
     * 标准解法应该是kmp算法，但是kmp算法确实不容易被理解。这里用滑动窗口计算哈希的解法来尝试解决
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        if (haystack.length() <= 0 || needle.length() <= 0 || needle.length() > haystack.length()) {
            return -1;
        }
        //取哈希
        int code1 = hashKey(haystack, 0, needle.length() - 1);
        int code2 = hashKey(needle, 0, needle.length() - 1);
        if (code1 == code2) {
            return 0;
        }
        int mod = 2 << 31;
        int p = (int) Math.pow(26, needle.length() - 1);
        for (int i = 1; i <= haystack.length() - needle.length(); i++) {
            code1 = ((code1 - haystack.charAt(i - 1) * p) * 26 + (haystack.charAt(i + needle.length() - 1) - 'a')) % mod;
            if (code1 == code2) {
                return i;
            }
        }
        return -1;
    }

    private int hashKey(String str, int start, int end) {
        int hashcode = 0;
        int mod = 2 << 31;
        for (int i = end; i >= start; i--) {
            hashcode = (hashcode * 26 + (str.charAt(i) - 'a')) % mod;
        }
        return hashcode;
    }
}
