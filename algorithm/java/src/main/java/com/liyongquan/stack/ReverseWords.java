package com.liyongquan.stack;

//给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
//
//
//
// 示例：
//
// 输入："Let's take LeetCode contest"
//输出："s'teL ekat edoCteeL tsetnoc"
//
//
//
//
// 提示：
//
//
// 在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。
//
// Related Topics 字符串
// 👍 278 👎 0


import java.util.Stack;

public class ReverseWords {
    /**
     * 先分割字符串，然后对每个字符进行翻转
     * 不通过
     *
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        if (s.length() == 0) {
            return s;
        }
        StringBuilder sb = new StringBuilder(s.length());
        String[] words = s.split(" ");
        for (int i = 0; i < words.length; i++) {
            //翻转单词
            String word = words[i];
            for (int j = word.length() - 1; j >= 0; j--) {
                sb.append(word.charAt(j));
            }
            if (i != word.length() - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    /**
     * 使用栈来解决
     *
     * @param s
     * @return
     */
    public String reverseWords2(String s) {
        if (s.length() == 0) {
            return s;
        }
        StringBuilder sb = new StringBuilder(s.length());
        int idx = 0;
        Stack<Character> stack = new Stack<>();
        while (idx < s.length()) {
            char ch = s.charAt(idx++);
            if (ch == ' ') {
                while (!stack.isEmpty()) {
                    Character c = stack.pop();
                    sb.append(c);
                }
                sb.append(ch);
            } else {
                stack.push(ch);
            }
        }
        while (!stack.isEmpty()) {
            Character c = stack.pop();
            sb.append(c);
        }
        return sb.toString();
    }
}
