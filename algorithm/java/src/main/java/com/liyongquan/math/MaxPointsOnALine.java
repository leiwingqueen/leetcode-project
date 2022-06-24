package com.liyongquan.math;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * 149. 直线上最多的点数
 * <p>
 * 给你一个数组 points ，其中 points[i] = [xi, yi] 表示 X-Y 平面上的一个点。求最多有多少个点在同一条直线上。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：points = [[1,1],[2,2],[3,3]]
 * 输出：3
 * 示例 2：
 * <p>
 * <p>
 * 输入：points = [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
 * 输出：4
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= points.length <= 300
 * points[i].length == 2
 * -104 <= xi, yi <= 104
 * points 中的所有点 互不相同
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/max-points-on-a-line
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaxPointsOnALine {
    /**
     * 暴力解法
     *
     * @param points
     * @return
     */
    public int maxPoints(int[][] points) {
        if (points.length <= 2) {
            return points.length;
        }
        //排序
        Arrays.sort(points, Comparator.comparingInt(o -> o[0]));
        int max = 2;
        for (int i = 0; i < points.length - 2; i++) {
            for (int j = i + 2; j < points.length; j++) {
                int cnt = 2;
                for (int k = i + 1; k < j; k++) {
                    if (check(points[i], points[k], points[j])) {
                        cnt++;
                    }
                }
                max = Math.max(max, cnt);
            }
        }
        return max;
    }

    private boolean check(int[] p1, int[] p2, int[] p3) {
        return (p2[1] - p1[1]) * (p3[0] - p2[0]) == (p3[1] - p2[1]) * (p2[0] - p1[0]);
    }

    /**
     * 使用hash进行优化
     *
     * @param points
     * @return
     */
    public int maxPoints2(int[][] points) {
        if (points.length <= 2) {
            return points.length;
        }
        int max = 2;
        for (int i = 0; i < points.length - 1; i++) {
            //从i点出发斜率相同的最多的点
            Map<String, Integer> map = new HashMap<>();
            int mx = 0;
            for (int j = i + 1; j < points.length; j++) {
                int s1 = points[j][0] - points[i][0];
                int s2 = points[j][1] - points[i][1];
                int gcd = gcd(s1, s2);
                String key = s1 / gcd + "_" + s2 / gcd;
                int value = map.getOrDefault(key, 0) + 1;
                map.put(key, value);
                mx = Math.max(mx, value);
            }
            max = Math.max(max, mx + 1);
        }
        return max;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
