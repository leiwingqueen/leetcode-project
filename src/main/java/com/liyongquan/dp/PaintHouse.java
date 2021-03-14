package com.liyongquan.dp;

/**
 * 256. 粉刷房子
 * <p>
 * 假如有一排房子，共 n 个，每个房子可以被粉刷成红色、蓝色或者绿色这三种颜色中的一种，你需要粉刷所有的房子并且使其相邻的两个房子颜色不能相同。
 * <p>
 * 当然，因为市场上不同颜色油漆的价格不同，所以房子粉刷成不同颜色的花费成本也是不同的。每个房子粉刷成不同颜色的花费是以一个 n x 3 的矩阵来表示的。
 * <p>
 * 例如，costs[0][0] 表示第 0 号房子粉刷成红色的成本花费；costs[1][2] 表示第 1 号房子粉刷成绿色的花费，以此类推。请你计算出粉刷完所有房子最少的花费成本。
 * <p>
 * 注意：
 * <p>
 * 所有花费均为正整数。
 * <p>
 * 示例：
 * <p>
 * 输入: [[17,2,17],[16,16,5],[14,3,19]]
 * 输出: 10
 * 解释: 将 0 号房子粉刷成蓝色，1 号房子粉刷成绿色，2 号房子粉刷成蓝色。
 *      最少花费: 2 + 5 + 3 = 10。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/paint-house
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class PaintHouse {
    /**
     * f0(n),f1(n),f2(n)分别代表最后一个房子颜色是红蓝绿的最少花费
     * f0(n)=min{f1(n-1),f2(n-1)}+cost[n-1][0]
     * f1(n)=min{f0(n-1),f2(n-1)}+cost[n-1][1]
     * f2(n)=min{f0(n-1),f1(n-1)}+cost[n-1][2]
     *
     * 时间复杂度O(n)
     * 空间复杂度O(n)
     *
     * @param costs
     * @return
     */
    public int minCost(int[][] costs) {
        int len = costs.length;
        if (len == 0) {
            return 0;
        }
        int[] dp0 = new int[len], dp1 = new int[len], dp2 = new int[len];
        dp0[0] = costs[0][0];
        dp1[0] = costs[0][1];
        dp2[0] = costs[0][2];
        for (int i = 1; i < len; i++) {
            dp0[i] = Math.min(dp1[i - 1], dp2[i - 1]) + costs[i][0];
            dp1[i] = Math.min(dp0[i - 1], dp2[i - 1]) + costs[i][1];
            dp2[i] = Math.min(dp0[i - 1], dp1[i - 1]) + costs[i][2];
        }
        return Math.min(dp0[len - 1], Math.min(dp1[len - 1], dp2[len - 1]));
    }
}
