package com.liyongquan.bit;

/**
 * 插入。给定两个32位的整数N与M，以及表示比特位置的i与j。编写一种方法，将M插入N，使得M从N的第j位开始，到第i位结束。假定从j位到i位足以容纳M，也即若M = 10 011，那么j和i之间至少可容纳5个位。例如，不可能出现j = 3和i = 2的情况，因为第3位和第2位之间放不下M。
 * <p>
 * 示例1:
 * <p>
 * 输入：N = 1024(10000000000), M = 19(10011), i = 2, j = 6
 * 输出：N = 1100(10001001100)
 * 示例2:
 * <p>
 * 输入： N = 0, M = 31(11111), i = 0, j = 4
 * 输出：N = 31(11111)
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/insert-into-bits-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class InsertBits {
    public int insertBits(int N, int M, int i, int j) {
        M <<= i;
        int mask = (1 << 31) - 1;
        for (int k = i; k <= j; k++) {
            mask -= 1 << k;
        }
        return (N & mask) | M;
    }

    public static void main(String[] args) {
        InsertBits ib = new InsertBits();
        int i = ib.insertBits(1024, 19, 2, 6);
        System.out.println(i);
    }
}
