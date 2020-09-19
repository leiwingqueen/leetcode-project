package com.liyongquan.math;

/**
 * 你的任务是计算 ab 对 1337 取模，a 是一个正整数，b 是一个非常大的正整数且会以数组形式给出。
 * <p>
 * 示例 1:
 * <p>
 * 输入: a = 2, b = [3]
 * 输出: 8
 * 示例 2:
 * <p>
 * 输入: a = 2, b = [1,0]
 * 输出: 1024
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/super-pow
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SuperPow {
    int base = 1337;

    /**
     * 这个解真的妙，关键是还解析得很清楚
     * https://leetcode-cn.com/problems/super-pow/solution/you-qian-ru-shen-kuai-su-mi-suan-fa-xiang-jie-by-l/
     *
     * @param a
     * @param b
     * @return
     */
    public int superPow(int a, int[] b) {
        if (b.length == 0) {
            return 1;
        }
        int k = b[b.length - 1];
        //pop出最后一个数字
        int[] c = new int[b.length - 1];
        for (int i = 0; i < b.length - 1; i++) {
            c[i] = b[i];
        }
        int pow1 = pow(a, k);
        int pow2 = pow(superPow(a, c), 10);
        return (pow1 * pow2) % base;
    }

    private int pow(int a, int k) {
        a %= base;
        int result = 1;
        for (int i = 0; i < k; i++) {
            result = (result * a) % base;
        }
        return result;
    }
}
