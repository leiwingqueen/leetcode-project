package com.liyongquan.twopointer;

/**
 * 编写一个函数，以字符串作为输入，反转该字符串中的元音字母。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入："hello"
 * 输出："holle"
 * 示例 2：
 * <p>
 * 输入："leetcode"
 * 输出："leotcede"
 *  
 * <p>
 * 提示：
 * <p>
 * 元音字母不包含字母 "y" 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-vowels-of-a-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ReverseVowels {
    public String reverseVowels(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        StringBuilder sb = new StringBuilder(s);
        int left = 0, right = s.length() - 1;
        while (left < right) {
            char c1 = sb.charAt(left);
            char c2 = sb.charAt(right);
            if (yy(c1) && yy(c2)) {
                sb.setCharAt(left, c2);
                sb.setCharAt(right, c1);
                left++;
                right--;
                continue;
            }
            if (!yy(c1)) {
                left++;
            }
            if (!yy(c2)) {
                right--;
            }
        }
        return sb.toString();
    }

    private boolean yy(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'||
                c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
    }
}
