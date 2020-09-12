package com.liyongquan.binarySort;

/**
 * 给定一个正整数 num，编写一个函数，如果 num 是一个完全平方数，则返回 True，否则返回 False。
 * <p>
 * 说明：不要使用任何内置的库函数，如  sqrt。
 * <p>
 * 示例 1：
 * <p>
 * 输入：16
 * 输出：True
 * 示例 2：
 * <p>
 * 输入：14
 * 输出：False
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-perfect-square
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class PerfectSquare {
    public boolean isPerfectSquare(int num) {
        int left = 1, right = num;
        while (left < right) {
            int middle = left + (right - left) / 2;
            //这里要这样写，不然会溢出
            long pow = (long) Math.pow(middle, 2);
            if (pow == num) {
                return true;
            } else if (pow < num) {
                left = middle + 1;
            } else {
                right = middle;
            }
        }
        return left * left == num;
    }

    /**
     * https://leetcode-cn.com/problems/valid-perfect-square/solution/you-xiao-de-wan-quan-ping-fang-shu-by-leetcode/
     * 牛顿迭代法，猛男落泪。。
     *
     * @param args
     */
    public boolean isPerfectSquare2(int num) {
        if (num < 2) return true;

        long x = num / 2;
        while (x * x > num) {
            x = (x + num / x) / 2;
        }
        return (x * x == num);
    }

    public static void main(String[] args) {
        PerfectSquare square = new PerfectSquare();
        boolean perfectSquare = square.isPerfectSquare(808201);
        System.out.println(perfectSquare);
    }
}
