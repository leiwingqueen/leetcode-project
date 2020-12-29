package com.liyongquan.string;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
 * <p>
 *  
 * <p>
 * 提示：
 * <p>
 * num1 和num2 的长度都小于 5100
 * num1 和num2 都只包含数字 0-9
 * num1 和num2 都不包含任何前导零
 * 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-strings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class AddStrings {
    public String addStrings(String num1, String num2) {
        Deque<Character> deque = new LinkedList<>();
        //进位标识
        boolean up = false;
        int idx1 = num1.length() - 1, idx2 = num2.length() - 1;
        while (up || idx1 >= 0 || idx2 >= 0) {
            int n1 = idx1 >= 0 ? num1.charAt(idx1) - '0' : 0;
            int n2 = idx2 >= 0 ? num2.charAt(idx2) - '0' : 0;
            int sum = n1 + n2 + (up ? 1 : 0);
            up = sum >= 10;
            if (sum >= 10) {
                sum -= 10;
            }
            deque.offerFirst((char) (sum + '0'));
            idx1--;
            idx2--;
        }
        //转换成字符串
        StringBuilder sb = new StringBuilder(deque.size());
        while (deque.size() > 0) {
            sb.append(deque.pollFirst());
        }
        return sb.toString();
    }
}
