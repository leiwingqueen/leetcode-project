package com.liyongquan.hash;

/**
 * 给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a2 + b2 = c。
 * <p>
 * 示例1:
 * <p>
 * 输入: 5
 * 输出: True
 * 解释: 1 * 1 + 2 * 2 = 5
 *  
 * <p>
 * 示例2:
 * <p>
 * 输入: 3
 * 输出: False
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sum-of-square-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SquareSum {
    /**
     * 暴力解法
     *
     * 算法复杂度为O(sqrt(n)^2)
     *
     * @param c
     * @return
     */
    public boolean judgeSquareSum(int c) {
        int sqrt = (int) Math.sqrt(c) ;
        for (int i = 0; i <= sqrt; i++) {
            for (int j = i; j <= sqrt; j++) {
                int p = i * i + j * j;
                if (p == c) {
                    return true;
                } else if (p > c) {
                    break;
                }
            }
        }
        return false;
    }

    /**
     * 第二个循环可以直接改为sqrt计算出来
     * @param c
     * @return
     */
    public boolean judgeSquareSum2(int c) {
        int sqrt = (int) Math.sqrt(c) ;
        for (int i = 0; i <= sqrt; i++) {
            int b = (int)Math.sqrt(c - i * i);
            if (i * i + b * b == c) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        SquareSum sum=new SquareSum();
        boolean b = sum.judgeSquareSum2(32);
        System.out.println(b);
    }
}
