package com.liyongquan.math;

//给定包含多个点的集合，从其中取三个点组成三角形，返回能组成的最大三角形的面积。
//
//示例:
//输入: points = [[0,0],[0,1],[1,0],[0,2],[2,0]]
//输出: 2
//解释:
//这五个点如下图所示。组成的橙色三角形是最大的，面积为2。
//
//
//注意:
//
//3 <= points.length <= 50.
//不存在重复的点。
// -50 <= points[i][j] <= 50.
//结果误差值在 10^-6 以内都认为是正确答案。
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/largest-triangle-area
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

public class LargestTriangleArea {
    public double largestTriangleArea(int[][] points) {
        int len = points.length;
        double res = 0;
        for (int i = 0; i < len - 2; i++) {
            for (int j = 0; j < len - 1; j++) {
                for (int k = 0; k < len; k++) {
                    double a = distance(points[i], points[j]);
                    double b = distance(points[i], points[k]);
                    double c = distance(points[j], points[k]);
                    if (a > 0 && b > 0 && c > 0 && a + b > c && b + c > a && a + c > b) {
                        double p = (a + b + c) / 2;
                        double area = Math.sqrt(p * (p - a) * (p - b) * (p - c));
                        res = Math.max(area, res);
                    }
                }
            }
        }
        return res;
    }

    private double distance(int[] p1, int[] p2) {
        return Math.sqrt(Math.pow(p1[0] - p2[0], 2) + Math.pow(p1[1] - p2[1], 2));
    }
}
