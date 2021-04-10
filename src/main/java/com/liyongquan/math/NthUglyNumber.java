package com.liyongquan.math;

/**
 * 编写一个程序，找出第 n 个丑数。
 * <p>
 * 丑数就是质因数只包含 2, 3, 5 的正整数。
 * <p>
 * 示例:
 * <p>
 * 输入: n = 10
 * 输出: 12
 * 解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
 * 说明:  
 * <p>
 * 1 是丑数。
 * n 不超过1690。
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ugly-number-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NthUglyNumber {
    /**
     * 时间复杂度O(nlog(n))
     *
     * 超时
     *
     * @param n
     * @return
     */
    public int nthUglyNumber(int n) {
        int[] arr = {2, 3, 5};
        int cnt = 0;
        int i = 1;
        while (cnt < n) {
            int num = i;
            for (int j : arr) {
                while (num % j == 0) {
                    num /= j;
                }
            }
            if (num == 1) {
                cnt++;
            }
            i++;
        }
        return i - 1;
    }

    public int nthUglyNumber2(int n) {
        //TODO:
        return 0;
    }
}
