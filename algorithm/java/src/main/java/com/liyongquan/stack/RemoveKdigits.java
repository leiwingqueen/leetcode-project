package com.liyongquan.stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 402. 移掉K位数字
 * <p>
 * 给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小。
 * <p>
 * 注意:
 * <p>
 * num 的长度小于 10002 且 ≥ k。
 * num 不会包含任何前导零。
 * 示例 1 :
 * <p>
 * 输入: num = "1432219", k = 3
 * 输出: "1219"
 * 解释: 移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219。
 * 示例 2 :
 * <p>
 * 输入: num = "10200", k = 1
 * 输出: "200"
 * 解释: 移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
 * 示例 3 :
 * <p>
 * 输入: num = "10", k = 2
 * 输出: "0"
 * 解释: 从原数字移除所有的数字，剩余为空就是0。
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-k-digits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RemoveKdigits {
    /**
     * 单调栈
     *
     * @param num
     * @param k
     * @return
     */
    public String removeKdigits(String num, int k) {
        int len = num.length();
        if (k >= len) {
            return "0";
        }
        if (k == 0) {
            return num;
        }
        int cnt = len - k;
        Deque<Character> stack = new LinkedList<>();
        for (int i = 0; i < len; i++) {
            if (!stack.isEmpty() && stack.peekLast() <= num.charAt(i) && stack.size() >= cnt) {
                continue;
            }
            //右侧剩余的数字数量
            int left = len - i;
            while (!stack.isEmpty() && stack.peekLast() > num.charAt(i)
                    //保证后面还有足够的数字
                    && cnt - stack.size() < left) {
                stack.pollLast();
            }
            stack.offerLast(num.charAt(i));
        }
        //去掉前导0
        while (!stack.isEmpty() && stack.peekFirst() == '0') {
            stack.pollFirst();
        }
        if (stack.size() == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder(stack.size());
        while (!stack.isEmpty()) {
            sb.append(stack.pollFirst());
        }
        return sb.toString();
    }
}
