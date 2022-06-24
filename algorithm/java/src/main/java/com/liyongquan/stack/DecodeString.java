package com.liyongquan.stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 * <p>
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 * <p>
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 * <p>
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "3[a]2[bc]"
 * 输出："aaabcbc"
 * 示例 2：
 * <p>
 * 输入：s = "3[a2[c]]"
 * 输出："accaccacc"
 * 示例 3：
 * <p>
 * 输入：s = "2[abc]3[cd]ef"
 * 输出："abcabccdcdcdef"
 * 示例 4：
 * <p>
 * 输入：s = "abc3[cd]xyz"
 * 输出："abccdcdcdxyz"
 * 通过次数79,174提交次数146,721
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/decode-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class DecodeString {
    /**
     * 递归解法
     * <p>
     * 我们定义token
     * 1.普通字符串
     * 2.数字[token]
     * 遇到字母，直接输出
     * 遇到数字，直到一个完整的]结束，继续递归
     *
     * @param s
     * @return
     */
    public String decodeString(String s) {
        int len = s.length();
        if (len == 0) {
            return s;
        }
        StringBuilder sb = new StringBuilder();
        int l = 0, r = 0;
        while (r < len) {
            char ch = s.charAt(r);
            if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')) {
                sb.append(ch);
                r++;
            } else {
                //数字的场景直接递归
                int num = 0;
                while (r < len && (s.charAt(r) >= '0' && s.charAt(r) <= '9')) {
                    num = num * 10 + (s.charAt(r) - '0');
                    r++;
                }
                //这里的字符一定是[开始
                l = r;
                Deque<Character> stack = new LinkedList<>();
                stack.offerFirst(s.charAt(r));
                r++;
                while (!stack.isEmpty()) {
                    char c = s.charAt(r);
                    if (c == '[') {
                        stack.offerFirst('[');
                    } else if (c == ']') {
                        stack.pollFirst();
                    }
                    r++;
                }
                //[l,r)为pattern的内容，我们需要去掉左右的中括号，实际的范围是[l+1,r-1)
                String token = decodeString(s.substring(l + 1, r - 1));
                //重复num遍输出
                for (int i = 0; i < num; i++) {
                    sb.append(token);
                }
            }
        }
        return sb.toString();
    }
}
