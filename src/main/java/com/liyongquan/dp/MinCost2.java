package com.liyongquan.dp;

//假如有一排房子，共 n 个，每个房子可以被粉刷成 k 种颜色中的一种，你需要粉刷所有的房子并且使其相邻的两个房子颜色不能相同。
//
// 当然，因为市场上不同颜色油漆的价格不同，所以房子粉刷成不同颜色的花费成本也是不同的。每个房子粉刷成不同颜色的花费是以一个 n x k 的矩阵来表示的。
//
// 例如，costs[0][0] 表示第 0 号房子粉刷成 0 号颜色的成本花费；costs[1][2] 表示第 1 号房子粉刷成 2 号颜色的成本花费，以此
//类推。请你计算出粉刷完所有房子最少的花费成本。
//
// 注意：
//
// 所有花费均为正整数。
//
// 示例：
//
// 输入: [[1,5,3],[2,9,4]]
//输出: 5
//解释: 将 0 号房子粉刷成 0 号颜色，1 号房子粉刷成 2 号颜色。最少花费: 1 + 4 = 5;
//     或者将 0 号房子粉刷成 2 号颜色，1 号房子粉刷成 0 号颜色。最少花费: 3 + 2 = 5.
//
//
// 进阶：
//您能否在 O(nk) 的时间复杂度下解决此问题？
// Related Topics 动态规划
// 👍 64 👎 0


public class MinCost2 {
    /**
     * dp表达式
     * <p>
     * f(i,j)表示前i栋房子切最后一栋房子粉刷成颜色j的最小花费
     * <p>
     * f(i,j)=min{f(i-1,1),f(i-1,2),...,f(i-1,j-1),f(i-1,j+1),...,f(i-1,k)}+costs[i][j]
     * <p>
     * 时间复杂度O(nk^2)
     * 空间复杂度O(nk)
     *
     * @param costs
     * @return
     */
    public int minCostII(int[][] costs) {
        int n = costs.length;
        if (n == 0) {
            return 0;
        }
        int k = costs[0].length;
        int[][] dp = new int[n][k];
        //初始化
        for (int i = 0; i < k; i++) {
            dp[0][i] = costs[0][i];
        }
        //dp迭代
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < k; j++) {
                //计算f(i,j)
                int min = Integer.MAX_VALUE;
                for (int l = 0; l < k; l++) {
                    if (l != j) {
                        min = Math.min(min, dp[i - 1][l]);
                    }
                }
                min += costs[i][j];
                dp[i][j] = min;
            }
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < k; i++) {
            res = Math.min(res, dp[n - 1][i]);
        }
        return res;
    }
}
