package com.liyongquan.dp;

import java.math.BigDecimal;
import java.util.*;

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
     * <p>
     * 不通过，排序会导致下标的序号乱序
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
            for (int j = i + 2; j < points.length; j++) {
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
                    System.out.println("[" + i + "," + j + "]:" + count);
                    max = count;
                    result[0] = i;
                    result[1] = j;
                }
            }
        }
        return result;
    }

    /**
     * 我们考虑如何表示一条直线。
     * y=ax+b。
     * 确定a,b我们就可以定义一条直线。
     * <p>
     * 那么我们如果确定一条直接经过多少个点？
     * <p>
     * 最简单的做法是穷举n*(n-1)种情况，使用a,b作为key。value为经过的点。穷举完成就得到最终结果了。
     * <p>
     * 这种解法有精度问题
     *
     * @param points
     * @return
     */
    public int[] bestLine2(int[][] points) {
        Map<String, Set<Integer>> map = new HashMap<>();
        Map<String, int[]> pos = new HashMap<>();
        int max = 0;
        int[] result = new int[2];
        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                long[] line = getLine2(points[i], points[j]);
                String key = hash(line[0], line[1], line[2]);
                Set<Integer> set = map.getOrDefault(key, new HashSet<>());
                set.add(i);
                set.add(j);
                map.put(key, set);
                if (!pos.containsKey(key)) {
                    pos.put(key, new int[]{i, j});
                }
                //System.out.println("[" + pos.get(key)[0] + "," + pos.get(key)[1] + "]:" + set.size());
                if (set.size() > max) {
                    max = set.size();
                    result[0] = pos.get(key)[0];
                    result[1] = pos.get(key)[1];
                    //System.out.println("[" + result[0] + "," + result[1] + "]:" + max);
                }
            }
        }
        return result;
    }

    /**
     * 这个解法有精度问题
     *
     * @param p1
     * @param p2
     * @return
     */
    public BigDecimal[] getLine(int[] p1, int[] p2) {
        BigDecimal a, b;
        if (p1[0] == p2[0]) {
            a = BigDecimal.ZERO;
        } else {
            a = BigDecimal.valueOf(p2[1] - p1[1]).divide(BigDecimal.valueOf(p2[0] - p1[0]));
        }
        b = BigDecimal.valueOf(p1[1]).subtract(a.multiply(BigDecimal.valueOf(p1[0])));
        return new BigDecimal[]{a, b};
    }


    /**
     * 2）对于任意两点(X1,Y1)、(X2,Y2)，都有 A = Y2 - Y1 B = X1 - X2 C = X2Y1 - X1Y2
     * <p>
     * 另外还要取最大公约数
     *
     * @param p1
     * @param p2
     * @return
     */
    private long[] getLine2(int[] p1, int[] p2) {
        long a = p2[1] - p1[1];
        long b = p1[0] - p1[0];
        long c = p2[0] * p1[1] - p1[0] * p2[1];
        long gcd = gcd(gcd(a, b), c);
        return new long[]{a / gcd, b / gcd, c / gcd};
    }

    private long gcd(long a, long b) {
        if (a == 0) {
            return 1;
        }
        return b == 0 ? a : gcd(b, a % b);
    }

    private String hash(long a, long b, long c) {
        return a + "#" + b + "#" + c;
    }
}
