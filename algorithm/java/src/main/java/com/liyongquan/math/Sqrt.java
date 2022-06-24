package com.liyongquan.math;

/**
 * 实现 int sqrt(int x) 函数。
 * <p>
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 * <p>
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 4
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: 8
 * 输出: 2
 * 说明: 8 的平方根是 2.82842...,
 *      由于返回类型是整数，小数部分将被舍去。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sqrtx
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Sqrt {
    /**
     * 先使用二分查找定位到r*r<=x的第一个数。
     * 最终我们可以把结果定位到[r,2r)的区间
     *
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        int l = 0, r = x, result = -1;
        while (l <= r) {
            int middle = (l + r) / 2;
            long c = (long) middle * middle;
            if (c == x) {
                return middle;
            } else if (c < x) {
                result = middle;
                l = middle + 1;
            } else {
                r = middle - 1;
            }
        }
        return result;
    }

    public int mySqrt2(int x) {
        int r = x;
        while ((long) r * r > x) {
            r /= 2;
        }
        if ((long) r * r == x) {
            return r;
        }
        while ((long) r * r < x) {
            r += 1;
        }
        if ((long) r * r == x) {
            return r;
        }
        return r - 1;
    }

    public static void main(String[] args) {
        Sqrt sqrt = new Sqrt();
        int i = sqrt.mySqrt2(2147395599);
        System.out.println(i);
    }
}
