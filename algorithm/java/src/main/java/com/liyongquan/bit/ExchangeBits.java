package com.liyongquan.bit;

/**
 * 配对交换。编写程序，交换某个整数的奇数位和偶数位，尽量使用较少的指令（也就是说，位0与位1交换，位2与位3交换，以此类推）。
 * <p>
 * 示例1:
 * <p>
 * 输入：num = 2（或者0b10）
 * 输出 1 (或者 0b01)
 * 示例2:
 * <p>
 * 输入：num = 3
 * 输出：3
 * 提示:
 * <p>
 * num的范围在[0, 2^30 - 1]之间，不会发生整数溢出。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/exchange-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ExchangeBits {
    public int exchangeBits(int num) {
        int result = 0, count = 0;
        while (num != 0) {
            int i1 = num & 0b1;
            int i2 = (num & 0b10) >> 1;
            result += (i1 << (count + 1)) + (i2 << count);
            count += 2;
            num >>= 2;
        }
        return result;
    }

    public static void main(String[] args) {
        ExchangeBits eb = new ExchangeBits();
        int i = eb.exchangeBits(571603718);
        System.out.println(i);
    }
}
