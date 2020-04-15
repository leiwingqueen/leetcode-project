package com.liyongquan.math;

/**
 * 实现函数double Power(double base, int exponent)，求base的exponent次方。
 * 不得使用库函数，同时不需要考虑大数问题。
 *
 *
 *
 * 示例 1:
 *
 * 输入: 2.00000, 10
 * 输出: 1024.00000
 *
 * 示例 2:
 *
 * 输入: 2.10000, 3
 * 输出: 9.26100
 *
 * 示例 3:
 *
 * 输入: 2.00000, -2
 * 输出: 0.25000
 * 解释: 2-2 = 1/22 = 1/4 = 0.25
 *
 *
 *
 * 说明:
 *
 *     -100.0 < x < 100.0
 *     n 是 32 位有符号整数，其数值范围是 [−231, 231 − 1] 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shu-zhi-de-zheng-shu-ci-fang-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Pow {
    /**
     * 正常解法会超时,因此考虑使用dp的思路来解题
     * @param x
     * @param n
     * @return
     */
    public double myPow1(double x, int n) {
        if (n==0) {
            return 1;
        }
        if (x==1) {
            return 1;
        }
        long count=n;
        boolean negative=n<0;
        if(n<0){
            count=-count;
        }
        double r=1;
        for (long i = 0; i < count; i++) {
            r*=x;
        }
        return negative?1/r:r;
    }

    /**
     * 二分法
     * dp方程：
     * n为指数，
     * 偶数:
     * f(n)=f(n/2)*f(n/2)
     * 奇数:
     * f(n)=f(n/2)*f(n/2)*x
     *
     * 初始值:
     * f(0)=1
     * f(1)=x
     *
     * 复杂度可以由O(n)下降为O(log(n))
     * @param x
     * @param n
     * @return
     */
    public double myPow(double x, int n) {
        long count=n;
        boolean negative=n<0;
        if(n<0){
            count=-count;
        }
        double r = degrace(x, count);
        return negative?1/r:r;
    }

    private double degrace(double x,long n){
        if (x==1) {
            return 1;
        }
        if (n==0) {
            return 1;
        }
        if (n==1) {
            return x;
        }
        double degrace = degrace(x, n / 2);
        degrace*=degrace;
        if (n%2==1) {
            degrace*=x;
        }
        return degrace;
    }

    public static void main(String[] args) {
        Pow pow=new Pow();
        /**
         * 2.00000
         * -2147483648
         */
        double v = pow.myPow(2, 2);
        System.out.println(v);
    }
}
