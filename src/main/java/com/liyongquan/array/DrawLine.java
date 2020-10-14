package com.liyongquan.array;

/**
 * 绘制直线。有个单色屏幕存储在一个一维数组中，使得32个连续像素可以存放在一个 int 里。
 * 屏幕宽度为w，且w可被32整除（即一个 int 不会分布在两行上），屏幕高度可由数组长度及屏幕宽度推算得出。请实现一个函数，绘制从点(x1, y)到点(x2, y)的水平线。
 * <p>
 * 给出数组的长度 length，宽度 w（以比特为单位）、直线开始位置 x1（比特为单位）、直线结束位置 x2（比特为单位）、直线所在行数 y。返回绘制过后的数组。
 * <p>
 * 示例1:
 * <p>
 * 输入：length = 1, w = 32, x1 = 30, x2 = 31, y = 0
 * 输出：[3]
 * 说明：在第0行的第30位到第31为画一条直线，屏幕表示为[0b000000000000000000000000000000011]
 * 示例2:
 * <p>
 * 输入：length = 3, w = 96, x1 = 0, x2 = 95, y = 0
 * 输出：[-1, -1, -1]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/draw-line-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class DrawLine {
    /**
     * 时间复杂度O(x2-x1)
     *
     * @param length
     * @param w
     * @param x1
     * @param x2
     * @param y
     * @return
     */
    public int[] drawLine(int length, int w, int x1, int x2, int y) {
        //length*32/w
        int h = (length << 5) / w;
        int[] result = new int[length];
        //宽度为w,高度h。即(x,y)所在的位数为y*w+x，对应所在整数为 (y*w+x)/32,对应整数的位为(y*w+x)%32
        for (int i = x1; i <= x2; i++) {
            //所在的位的位置
            int bit = y * w + i;
            //对应的整数的位置
            int index = bit >>> 5;
            //对应的整数的位(y*w+x)%32
            int bit2 = 31 - (bit & ((2 << 4) - 1));
            result[index] += (1 << bit2);
        }
        return result;
    }
}
