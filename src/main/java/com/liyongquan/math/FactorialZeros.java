package com.liyongquan.math;

import jdk.nashorn.internal.ir.IfNode;

/**
 * 设计一个算法，算出 n 阶乘有多少个尾随零。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 3
 * 输出: 0
 * 解释: 3! = 6, 尾数中没有零。
 * 示例 2:
 * <p>
 * 输入: 5
 * 输出: 1
 * 解释: 5! = 120, 尾数中有 1 个零.
 * 说明: 你算法的时间复杂度应为 O(log n) 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/factorial-zeros-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FactorialZeros {
    /**
     * 暴力解法
     * <p>
     * 时间复杂度O(n)
     *
     * @param n
     * @return
     */
    public int trailingZeroes(int n) {
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        int count = 0;
        while (result != 0) {
            if (result % 10 == 0) {
                count++;
                result /= 10;
            } else {
                return count;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        FactorialZeros fz = new FactorialZeros();
        int i = fz.trailingZeroes(13);
        System.out.println(i);
    }
}
