package com.liyongquan.math;

//447. 回旋镖的数量
//给定平面上 n 对 互不相同 的点 points ，其中 points[i] = [xi, yi] 。回旋镖 是由点 (i, j, k) 表示的元组 ，其中 i 和 j 之间的距离和 i 和 k 之间的距离相等（需要考虑元组的顺序）。
//
//返回平面上所有回旋镖的数量。
//
// 
//示例 1：
//
//输入：points = [[0,0],[1,0],[2,0]]
//输出：2
//解释：两个回旋镖为 [[1,0],[0,0],[2,0]] 和 [[1,0],[2,0],[0,0]]
//示例 2：
//
//输入：points = [[1,1],[2,2],[3,3]]
//输出：2
//示例 3：
//
//输入：points = [[1,1]]
//输出：0
// 
//
//提示：
//
//n == points.length
//1 <= n <= 500
//points[i].length == 2
//-104 <= xi, yi <= 104
//所有点都 互不相同
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/number-of-boomerangs
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import java.util.HashMap;
import java.util.Map;

/**
 * @author liyongquan
 * @date 2021/9/13
 */
public class NumberOfBoomerangs {
    public int numberOfBoomerangs(int[][] points) {
        int len = points.length;
        if (len < 3) {
            return 0;
        }
        //距离-出发点-节点数量
        Map<Integer, Map<Integer, Integer>> buckets = new HashMap<>();
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                int dis = distance(points[i], points[j]);
                if (!buckets.containsKey(dis)) {
                    buckets.put(dis, new HashMap<>());
                }
                Map<Integer, Integer> map = buckets.get(dis);
                map.put(i, map.getOrDefault(i, 0) + 1);
                map.put(j, map.getOrDefault(j, 0) + 1);
            }
        }
        //统计数量
        int res = 0;
        for (Map<Integer, Integer> map : buckets.values()) {
            for (Integer value : map.values()) {
                if (value >= 2) {
                    res += value * (value - 1);
                }
            }
        }
        return res;
    }

    private int distance(int[] p1, int[] p2) {
        return (int) Math.pow(p1[0] - p2[0], 2) + (int) Math.pow(p1[1] - p2[1], 2);
    }
}
