package com.liyongquan.dp;

/**
 * 一条包含字母 A-Z 的消息通过以下方式进行了编码：
 * <p>
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 给定一个只包含数字的非空字符串，请计算解码方法的总数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "12"
 * 输出: 2
 * 解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
 * 示例 2:
 * <p>
 * 输入: "226"
 * 输出: 3
 * 解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/decode-ways
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NumDecodings {
    /**
     * dp表达式：
     * 若 s[n-2],s[n-1]组成的字符串小于26，且 s[n-1]>0
     * f(n)=f(n-1)+f(n-2)
     * 若s[n-1]==0
     * f(n)=f(n-2)
     * 否则:
     * f(n)=f(n-1)
     * 初始化：
     * f(0)=1
     * f(1)=1
     * <p>
     * 类似斐波那契数列
     *
     * @param s
     * @return
     */
    public int numDecodings(String s) {
        if (s.length() == 0) {
            return 0;
        }
        int f0 = 1;
        int f1 = s.charAt(0) - '0' > 0 ? 1 : 0;
        for (int i = 1; i < s.length(); i++) {
            int n = (s.charAt(i) - '0') + 10 * (s.charAt(i - 1) - '0');
            if (s.charAt(i) == '0') {
                if (s.charAt(i - 1) - '0' == 1 || s.charAt(i - 1) - '0' == 2) {
                    int tmp = f0;
                    f0 = f1;
                    f1 = tmp;
                } else {
                    //不可达的场景
                    return 0;
                }
            } else if (s.charAt(i - 1) != '0' && n <= 26 && n > 0) {
                int tmp = f0 + f1;
                f0 = f1;
                f1 = tmp;
            } else {
                f0 = f1;
            }
        }
        return f1;
    }

    public static void main(String[] args) {
        NumDecodings nd = new NumDecodings();
        int i = nd.numDecodings("12");
        System.out.println(i);
    }
}
