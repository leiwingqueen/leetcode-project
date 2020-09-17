package com.liyongquan.math;

/**
 * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 * <p>
 * 返回被除数 dividend 除以除数 divisor 得到的商。
 * <p>
 * 整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: dividend = 10, divisor = 3
 * 输出: 3
 * 解释: 10/3 = truncate(3.33333..) = truncate(3) = 3
 * 示例 2:
 * <p>
 * 输入: dividend = 7, divisor = -3
 * 输出: -2
 * 解释: 7/-3 = truncate(-2.33333..) = -2
 *  
 * <p>
 * 提示：
 * <p>
 * 被除数和除数均为 32 位有符号整数。
 * 除数不为 0。
 * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231,  231 − 1]。本题中，如果除法结果溢出，则返回 231 − 1。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/divide-two-integers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Divide {
    /**
     * 无视官方限制，解题。需要考虑溢出的场景
     *
     * @param dividend
     * @param divisor
     * @return
     */
    public int divide(int dividend, int divisor) {
        if (divisor == 1) {
            return dividend;
        }
        if (divisor == -1) {
            if (dividend == Integer.MIN_VALUE) {
                return Integer.MAX_VALUE;
            }
            return -dividend;
        }
        return dividend / divisor;
    }

    /**
     * 能够勉强通过，但是效率比较糟糕，因此每一步移动太慢
     * 解法2-不断减去除数，统计除数的数量
     *
     * @param dividend
     * @param divisor
     * @return
     */
    public int divide2(int dividend, int divisor) {
        if (divisor == 1) {
            return dividend;
        }
        if (divisor == -1) {
            if (dividend == Integer.MIN_VALUE) {
                return Integer.MAX_VALUE;
            }
            return -dividend;
        }
        int flag = (dividend ^ divisor) < 0 ? -1 : 1;
        int result = 0;
        long a = Math.abs((long) dividend);
        long b = Math.abs((long) divisor);
        long tmp = b;
        while (a >= b) {
            result++;
            b += tmp;
        }
        return flag > 0 ? result : -result;
    }

    /**
     * 解法3，有点类似tcp拥堵阻塞算法，每次*2加速收敛的速度
     *
     * @param dividend
     * @param divisor
     * @return
     */
    public int divide3(int dividend, int divisor) {
        //边界场景
        if (divisor == 1) {
            return dividend;
        }
        if (divisor == -1) {
            if (dividend == Integer.MIN_VALUE) {
                return Integer.MAX_VALUE;
            }
            return -dividend;
        }
        int flag = (dividend ^ divisor) < 0 ? -1 : 1;
        long a = Math.abs((long) dividend);
        long b = Math.abs((long) divisor);
        if (a < b) {
            return 0;
        }
        //使用*2的运算加速收敛
        long result = 1;
        while (a >= b) {
            result <<= 1;
            b <<= 1;
        }
        //最终锁定范围在result/2~result之间的范围，再通过解法2求解
        b >>= 1;
        result >>= 1;
        result -= 1;
        long tmp = Math.abs((long) divisor);
        while (a >= b) {
            result++;
            b += tmp;
        }
        return flag > 0 ? (int) result : -(int) result;
    }

    public static void main(String[] args) {
        Divide divide = new Divide();
        //int i = divide.divide3(7, -3);
        //System.out.println(i);
        int i = divide.divide3(2, 2);
        System.out.println(i);
    }
}
