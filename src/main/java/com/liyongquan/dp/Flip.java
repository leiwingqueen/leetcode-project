package com.liyongquan.dp;

/**
 * 如果一个由 '0' 和 '1' 组成的字符串，是以一些 '0'（可能没有 '0'）后面跟着一些 '1'（也可能没有 '1'）的形式组成的，那么该字符串是单调递增的。
 * <p>
 * 我们给出一个由字符 '0' 和 '1' 组成的字符串 S，我们可以将任何 '0' 翻转为 '1' 或者将 '1' 翻转为 '0'。
 * <p>
 * 返回使 S 单调递增的最小翻转次数。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入："00110"
 * 输出：1
 * 解释：我们翻转最后一位得到 00111.
 * 示例 2：
 * <p>
 * 输入："010110"
 * 输出：2
 * 解释：我们翻转得到 011111，或者是 000111。
 * 示例 3：
 * <p>
 * 输入："00011000"
 * 输出：2
 * 解释：我们翻转得到 00000000。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= S.length <= 20000
 * S 中只包含字符 '0' 和 '1'
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/flip-string-to-monotone-increasing
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Flip {
    /**
     * f(n)表示前N个数，最后一个数字为0的最少翻转次数
     * q(n)表示前N个数，最后一个数字为1的最少翻转次数
     * <p>
     * dp表达式：
     * f(n)=f(n-1)+A[n-1]==0?0:1
     * q(n)=min{f(n-1),q(n-1)}+A[n-1]==0?1:0
     * <p>
     * f(1)=A[0]==0?0:1
     * q(1)=A[0]==0?1:0
     * <p>
     * 最终返回min{f(n),q(n)}
     *
     * @param S
     * @return
     */
    public int minFlipsMonoIncr(String S) {
        int fn = S.charAt(0) == '0' ? 0 : 1;
        int qn = S.charAt(0) == '0' ? 1 : 0;
        for (int i = 1; i < S.length(); i++) {
            char c = S.charAt(i);
            qn = Math.min(fn, qn) + (c == '0' ? 1 : 0);
            fn = fn + (c == '0' ? 0 : 1);
            //System.out.println(String.format("qn:%s,fn:%s", qn, fn));
        }
        return Math.min(qn, fn);
    }
}
