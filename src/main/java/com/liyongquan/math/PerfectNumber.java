package com.liyongquan.math;

/**
 * 对于一个 正整数，如果它和除了它自身以外的所有正因子之和相等，我们称它为“完美数”。
 * <p>
 * 给定一个 整数 n， 如果他是完美数，返回 True，否则返回 False
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入: 28
 * 输出: True
 * 解释: 28 = 1 + 2 + 4 + 7 + 14
 *  
 * <p>
 * 提示：
 * <p>
 * 输入的数字 n 不会超过 100,000,000. (1e8)
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/perfect-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class PerfectNumber {
    public boolean checkPerfectNumber(int num) {
        if (num==1) {
            return false;
        }
        int sqrt = (int) Math.sqrt(num);
        int sum = 1;
        for (int i = 2; i <= sqrt; i++) {
            if (num % i == 0) {
                sum += (num / i + i);
            }
        }
        return sum == num;
    }
}
