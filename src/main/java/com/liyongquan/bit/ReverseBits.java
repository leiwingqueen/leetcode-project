package com.liyongquan.bit;

/**
 * 给定一个32位整数 num，你可以将一个数位从0变为1。请编写一个程序，找出你能够获得的最长的一串1的长度。
 * <p>
 * 示例 1：
 * <p>
 * 输入: num = 1775(110111011112)
 * 输出: 8
 * 示例 2：
 * <p>
 * 输入: num = 7(01112)
 * 输出: 4
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-bits-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ReverseBits {
    /**
     * 暴力
     * 时间复杂度O(32*32)，空间复杂度O(1)
     *
     * @param num
     * @return
     */
    public int reverseBits(int num) {
        int max = 0;
        for (int i = 0; i < 32; i++) {
            int c = countBit(num | (1 << i));
            if (c > max) {
                max = c;
            }
        }
        return max;
    }

    /**
     * 统计连续1的个数，时间复杂度O(32)
     *
     * @param num
     * @return
     */
    private int countBit(int num) {
        int max = 0, count = 0;
        while (num != 0) {
            if ((num & 1) == 1) {
                count++;
            } else {
                if (count > max) {
                    max = count;
                }
                count = 0;
            }
            num >>>= 1;
        }
        if (count > max) {
            max = count;
        }
        return max;
    }
}
