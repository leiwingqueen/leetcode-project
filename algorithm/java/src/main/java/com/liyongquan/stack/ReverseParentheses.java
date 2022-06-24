package com.liyongquan.stack;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 给出一个字符串 s（仅含有小写英文字母和括号）。
 * <p>
 * 请你按照从括号内到外的顺序，逐层反转每对匹配括号中的字符串，并返回最终的结果。
 * <p>
 * 注意，您的结果中 不应 包含任何括号。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "(abcd)"
 * 输出："dcba"
 * 示例 2：
 * <p>
 * 输入：s = "(u(love)i)"
 * 输出："iloveu"
 * 示例 3：
 * <p>
 * 输入：s = "(ed(et(oc))el)"
 * 输出："leetcode"
 * 示例 4：
 * <p>
 * 输入：s = "a(bcdefghijkl(mno)p)q"
 * 输出："apmnolkjihgfedcbq"
 *  
 * <p>
 * 提示：
 * <p>
 * 0 <= s.length <= 2000
 * s 中只有小写英文字母和括号
 * 我们确保所有括号都是成对出现的
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-substrings-between-each-pair-of-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ReverseParentheses {
    /**
     * 典型的栈解法
     * <p>
     * 栈+缓冲队列
     *
     * @param s
     * @return
     */
    public String reverseParentheses(String s) {
        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (stack.isEmpty() && ch != '(') {
                sb.append(ch);
            } else {
                if (ch != ')') {
                    stack.add(ch);
                } else {
                    //pop出栈内的元素
                    Queue<Character> queue = new LinkedList<>();
                    while (!stack.isEmpty() && stack.peek() != '(') {
                        queue.offer(stack.pop());
                    }
                    //pop出左括号
                    stack.pop();
                    //不为空，继续入栈
                    if (stack.isEmpty()) {
                        while (!queue.isEmpty()) {
                            sb.append(queue.poll());
                        }
                    } else {
                        while (!queue.isEmpty()) {
                            stack.add(queue.poll());
                        }
                    }
                }
            }
        }
        return sb.toString();
    }
}
