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
     * <p>
     * 算法复杂度为O(sqrt(n)^2)
     *
     * @param c
     * @return
     */
    public boolean judgeSquareSum(int c) {
        int sqrt = (int) Math.sqrt(c);
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
     *
     * @param c
     * @return
     */
    public boolean judgeSquareSum2(int c) {
        int sqrt = (int) Math.sqrt(c / 2);
        for (int i = 0; i <= sqrt; i++) {
            int b = (int) Math.sqrt(c - i * i);
            if (i * i + b * b == c) {
                return true;
            }
        }
        return false;
    }

    /**
     * 稍微优化一下判断
     *
     * @param c
     * @return
     */
    public boolean judgeSquareSum3(int c) {
        int sqrt = (int) Math.sqrt(c / 2);
        for (int i = 0; i <= sqrt; i++) {
            double b = Math.sqrt(c - i * i);
            if (b == (int) b) {
                return true;
            }
        }
        return false;
    }

    /**
     * 费马定律，真的秀
     *
     * 一个非负整数 c 如果能够表示为两个整数的平方和，当且仅当 c 的所有形如 4k + 3的质因子的幂均为偶数。
     * <p>
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/sum-of-square-numbers/solution/ping-fang-shu-zhi-he-by-leetcode-solutio-8ydl/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param c
     * @return
     */
    public boolean judgeSquareSum4(int c) {
        for (int base = 2; base * base <= c; base++) {
            // 如果不是因子，枚举下一个
            if (c % base != 0) {
                continue;
            }

            // 计算 base 的幂
            int exp = 0;
            while (c % base == 0) {
                c /= base;
                exp++;
            }

            // 根据 Sum of two squares theorem 验证
            if (base % 4 == 3 && exp % 2 != 0) {
                return false;
            }
        }

        // 例如 11 这样的用例，由于上面的 for 循环里 base * base <= c ，base == 11 的时候不会进入循环体
        // 因此在退出循环以后需要再做一次判断
        return c % 4 != 3;
    }

    public static void main(String[] args) {
        SquareSum sum = new SquareSum();
        boolean b = sum.judgeSquareSum2(32);
        System.out.println(b);
    }
}
