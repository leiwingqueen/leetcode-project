package com.liyongquan.dp;

/**
 * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * <p>
 * 说明：每次只能向下或者向右移动一步。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * [
 *   [1,3,1],
 * [1,5,1],
 * [4,2,1]
 * ]
 * 输出: 7
 * 解释: 因为路径 1→3→1→1→1 的总和最小。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-path-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MinPath {
    /**
     * dp表达式:
     * f(m,n)=min{f(m-1,n),f(m,n-1)}+grid[m-1][n-1]
     * 初始化：
     * 横竖两条数据初始化
     * f(0,n)=sum(s[0][0],s[0][1],...,s[0][n-1])
     * f(m,0)=sum(s[0][0],s[1][0],...,s[m-1][0])
     *
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        if (grid.length == 0) {
            return 0;
        }
        int row = grid.length, col = grid[0].length;
        int[][] dp = new int[row][col];
        //初始化
        int sum = 0;
        for (int i = 0; i < row; i++) {
            sum += grid[i][0];
            dp[i][0] = sum;
        }
        sum = 0;
        for (int i = 0; i < col; i++) {
            sum += grid[0][i];
            dp[0][i] = sum;
        }
        //dp迭代
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[row - 1][col - 1];
    }
}
