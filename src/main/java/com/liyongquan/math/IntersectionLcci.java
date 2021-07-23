package com.liyongquan.math;

//给定两条线段（表示为起点start = {X1, Y1}和终点end = {X2, Y2}），如果它们有交点，请计算其交点，没有交点则返回空值。
//
// 要求浮点型误差不超过10^-6。若有多个交点（线段重叠）则返回 X 值最小的点，X 坐标相同则返回 Y 值最小的点。
//
//
//
// 示例 1：
//
// 输入：
//line1 = {0, 0}, {1, 0}
//line2 = {1, 1}, {0, -1}
//输出： {0.5, 0}
//
//
// 示例 2：
//
// 输入：
//line1 = {0, 0}, {3, 3}
//line2 = {1, 1}, {2, 2}
//输出： {1, 1}
//
//
// 示例 3：
//
// 输入：
//line1 = {0, 0}, {1, 1}
//line2 = {1, 0}, {2, 1}
//输出： {}，两条线段没有交点
//
//
//
//
// 提示：
//
//
// 坐标绝对值不会超过 2^7
// 输入的坐标均是有效的二维坐标
//
// Related Topics 几何 数学
// 👍 65 👎 0


public class IntersectionLcci {
    /**
     * 计算交点，然后判断交点是否在线段范围内
     *
     * @param start1
     * @param end1
     * @param start2
     * @param end2
     * @return
     */
    public double[] intersection(int[] start1, int[] end1, int[] start2, int[] end2) {
        return null;
    }

    private double[] getLine(int[] start, int[] end) {
        int deltaX = start[0] - end[0];
        int deltaY = start[1] - end[1];
        double a, b, c;
        if (deltaX == 0) {
            b = 0;
            if (start[0] == 0) {
                c = 0;
            } else {

            }
        } else if (deltaY == 0) {
            a = 0;
        }
        return null;
    }
}
