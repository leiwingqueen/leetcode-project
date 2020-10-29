package com.liyongquan.dp;

import java.util.Arrays;

/**
 * 给定一个二维平面及平面上的 N 个点列表Points，其中第i个点的坐标为Points[i]=[Xi,Yi]。请找出一条直线，其通过的点的数目最多。
 * <p>
 * 设穿过最多点的直线所穿过的全部点编号从小到大排序的列表为S，你仅需返回[S[0],S[1]]作为答案，若有多条直线穿过了相同数量的点，则选择S[0]值较小的直线返回，S[0]相同则选择S[1]值较小的直线返回。
 * <p>
 * 示例：
 * <p>
 * 输入： [[0,0],[1,1],[1,0],[2,0]]
 * 输出： [0,2]
 * 解释： 所求直线穿过的3个点的编号为[0,2,3]
 * 提示：
 * <p>
 * 2 <= len(Points) <= 300
 * len(Points[i]) = 2
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-line-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class BestLine {
    /**
     * 先尝试暴力解法
     *
     * @param points
     * @return
     */
    public int[] bestLine(int[][] points) {
        //先排序
        Arrays.sort(points, (o1, o2) -> {
            if (o1[0] != o2[0]) {
                return o1[0] - o2[0];
            } else {
                return o1[1] - o2[1];
            }
        });
        //统计
        int max = 0;
        int[] result = {0, 1};
        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                //统计中间还经过多少点
                int deltax = points[j][0] - points[i][0];
                int deltay = points[j][1] - points[j][1];
                int count = 0;
                for (int k = i + 1; k < j; k++) {
                    if (deltax * (points[k][1] - points[i][1]) == deltay * (points[k][0] - points[i][0])) {
                        count++;
                    }
                }
                if (count > max) {
                    max = count;
                    result[0] = i;
                    result[1] = j;
                }
            }
        }
        return result;
    }
}
