package com.liyongquan.math;

//给你 二维 平面上两个 由直线构成的 矩形，请你计算并返回两个矩形覆盖的总面积。
//
//每个矩形由其 左下 顶点和 右上 顶点坐标表示：
//
//第一个矩形由其左下顶点 (ax1, ay1) 和右上顶点 (ax2, ay2) 定义。
//第二个矩形由其左下顶点 (bx1, by1) 和右上顶点 (bx2, by2) 定义。
// 
//
//示例 1：
//
//
//输入：ax1 = -3, ay1 = 0, ax2 = 3, ay2 = 4, bx1 = 0, by1 = -1, bx2 = 9, by2 = 2
//输出：45
//示例 2：
//
//输入：ax1 = -2, ay1 = -2, ax2 = 2, ay2 = 2, bx1 = -2, by1 = -2, bx2 = 2, by2 = 2
//输出：16
// 
//
//提示：
//
//-104 <= ax1, ay1, ax2, ay2, bx1, by1, bx2, by2 <= 104
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/rectangle-area
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import java.util.ArrayList;
import java.util.List;

/**
 * @author liyongquan
 * @date 2021/9/30
 */
public class ComputeArea {
    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        int[][] p1 = get4Point(ax1, ay1, ax2, ay2);
        int[][] p2 = get4Point(bx1, by1, bx2, by2);
        int s1 = getSquare(p1);
        int s2 = getSquare(p2);
        if (s1 == 0 || s2 == 0) {
            return s1 + s2;
        }
        if (s1 < s2) {
            int[][] tmp = p1;
            p1 = p2;
            p2 = tmp;
        }
        //判断p2有多少个点在p1里面
        List<Integer> inside = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            if (inside(p1, p2[i])) {
                inside.add(i);
            }
        }
        if (inside.size() == 0) {
            return s1 + s2;
        }
        if (inside.size() >= 3) {
            return s1;
        }
        if (inside.size() == 1) {
            Integer idx = inside.get(0);
            //对角位置的下标
            int idx2 = (idx + 2) % 4;
            //计算重叠区域的面积
            int square = getSquare(p2[idx], p1[idx2]);
            return s1 + s2 - square;
        }
        //剩下就是两个点重叠的场景
        int s = 0;
        if (inside.get(0) == 0) {
            s = getSquare(p2[0], new int[]{p2[1][0], p1[2][1]});
        } else if (inside.get(0) == 1) {
            s = getSquare(p2[1], new int[]{p1[0][0], p2[2][1]});
        } else if (inside.get(0) == 2) {
            s = getSquare(p2[2], new int[]{p2[0][0], p1[0][1]});
        } else {
            s = getSquare(p2[3], new int[]{p1[1][0], p2[0][1]});
        }
        return s1 + s2 - s;
    }

    private int[][] get4Point(int ax1, int ay1, int ax2, int ay2) {
        int[][] points = {
                {ax1, ay1},
                {ax2, ay1},
                {ax2, ay2},
                {ax1, ay2}
        };
        return points;
    }

    private int getSquare(int[][] p) {
        int[] left = p[0];
        int[] right = p[2];
        return (right[0] - left[0]) * (right[1] - left[1]);
    }

    private int getSquare(int[] p1, int[] p2) {
        return Math.abs(p1[0] - p2[0]) * Math.abs(p1[1] - p2[1]);
    }

    private boolean inside(int[][] p1, int[] p2) {
        int[] left = p1[0];
        int[] right = p1[2];
        return p2[0] >= left[0] && p2[0] <= right[0] && p2[1] >= left[1] && p2[0] <= right[1];
    }
}
