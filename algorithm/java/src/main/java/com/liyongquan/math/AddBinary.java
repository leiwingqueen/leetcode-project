package com.liyongquan.math;

/**
 * 给你两个二进制字符串，返回它们的和（用二进制表示）。
 * <p>
 * 输入为 非空 字符串且只包含数字 1 和 0。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: a = "11", b = "1"
 * 输出: "100"
 * 示例 2:
 * <p>
 * 输入: a = "1010", b = "1011"
 * 输出: "10101"
 *  
 * <p>
 * 提示：
 * <p>
 * 每个字符串仅由字符 '0' 或 '1' 组成。
 * 1 <= a.length, b.length <= 10^4
 * 字符串如果不是 "0" ，就都不含前导零。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-binary
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class AddBinary {
    public String addBinary(String a, String b) {
        int len = Math.max(a.length(), b.length());
        int[] r = new int[len + 1];
        for (int i = 1; i <= len; i++) {
            int c1 = 0, c2 = 0;
            if (i <= a.length()) {
                c1=a.charAt(a.length() - i) - '0';
            }
            if (i<=b.length()) {
                c2 = b.charAt(b.length() - i) - '0';
            }
            int sum = r[len + 1 - i] + c1 + c2;
            //进位
            if (sum > 1) {
                r[len + 1 - i] = sum % 2;
                r[len + 1 - i - 1]++;
            } else {
                r[len + 1 - i] = sum;
            }
        }
        //结果输出
        StringBuilder builder = new StringBuilder(len + 1);
        if (r[0] != 0) {
            builder.append((char) ('0' + r[0]));
        }
        for (int i = 1; i < len + 1; i++) {
            builder.append((char) ('0' + r[i]));
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        AddBinary binary = new AddBinary();
        String s = binary.addBinary("11", "1");
        System.out.println(s);
    }
}
