package com.liyongquan.array;

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * <p>
 * 如果不存在公共前缀，返回空字符串 ""。
 * <p>
 * 示例 1:
 * <p>
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例 2:
 * <p>
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 * 说明:
 * <p>
 * 所有输入只包含小写字母 a-z 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-common-prefix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length <= 0) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        int minLength = Integer.MAX_VALUE;
        for (int i = 0; i < strs.length; i++) {
            if (strs[i].length() < minLength) {
                minLength = strs[i].length();
            }
        }
        int pt = 0;
        while (pt < minLength) {
            boolean eq = true;
            char c = strs[0].charAt(pt);
            for (int i = 1; i < strs.length; i++) {
                if (c != strs[i].charAt(pt)) {
                    eq = false;
                    break;
                }
            }
            if (eq) {
                stringBuilder.append(c);
                pt++;
            } else {
                break;
            }
        }
        return stringBuilder.toString();
    }
}
