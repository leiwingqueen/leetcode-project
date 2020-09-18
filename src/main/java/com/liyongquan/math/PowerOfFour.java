package com.liyongquan.math;

/**
 * 给定一个整数 (32 位有符号整数)，请编写一个函数来判断它是否是 4 的幂次方。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 16
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: 5
 * 输出: false
 * 进阶：
 * 你能不使用循环或者递归来完成本题吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/power-of-four
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class PowerOfFour {
    /**
     * 跟2的幂类似
     *
     * @param num
     * @return
     */
    public boolean isPowerOfFour(int num) {
        int tmp = 1;
        for (int i = 0; i < 16; i++) {
            if (tmp == num) {
                return true;
            }
            tmp <<= 2;
        }
        return false;
    }

    /**
     * https://leetcode-cn.com/problems/power-of-four/solution/4de-mi-by-leetcode/
     * 这里面的解题太风骚，学不会。。
     */
}
