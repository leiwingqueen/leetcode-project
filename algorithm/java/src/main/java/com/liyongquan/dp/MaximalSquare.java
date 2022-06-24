package com.liyongquan.dp;

/**
 * 221. 最大正方形
 * <p>
 * 在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * 输出：4
 * 示例 2：
 * <p>
 * <p>
 * 输入：matrix = [["0","1"],["1","0"]]
 * 输出：1
 * 示例 3：
 * <p>
 * 输入：matrix = [["0"]]
 * 输出：0
 *  
 * <p>
 * 提示：
 * <p>
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 300
 * matrix[i][j] 为 '0' 或 '1'
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximal-square
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaximalSquare {
    /**
     * 暴力解法
     *
     * @param matrix
     * @return
     */
    public int maximalSquare(char[][] matrix) {
        int row = matrix.length, col = matrix[0].length;
        int max = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                //遍历宽度
                for (int width = max + 1; i + width - 1 < row && j + width - 1 < col; width++) {
                    if (!isSquare(matrix, i, j, width)) {
                        break;
                    }
                    max = Math.max(max, width);
                }
            }
        }
        return max * max;
    }

    private boolean isSquare(char[][] matrix, int x, int y, int width) {
        for (int i = x; i < x + width; i++) {
            for (int j = y; j < y + width; j++) {
                if (matrix[i][j] == '0') {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * dp解法
     * <p>
     * f(i,j)表示以(i,j)开始的最大正方形的长度
     *
     * 如果M[i][j]==0,f(i,j)=0
     * 如果M[i][j]==1,
     * 那么我们可以计算可能达到的最大长度为 width=min(f(i+1,j),f(i,j+1))+1
     *
     * 假设M[i+width][j+width]==1，那么最大长度就是width，否则就是width-1
     *
     * 时间复杂度O(m*n)
     * 空间复杂度O(m*n)
     *
     *
     * @param matrix
     * @return
     */
    public int maximalSquare2(char[][] matrix) {
        int row = matrix.length, col = matrix[0].length;
        //增加一行和一列，简化边界值的处理
        int[][] dp = new int[row + 1][col + 1];
        //初始化
        for (int i = 0; i <= row; i++) {
            dp[i][0] = 0;
        }
        for (int i = 0; i <= col; i++) {
            dp[0][i] = 0;
        }
        //dp迭代
        int res = 0;
        for (int i = row - 1; i >= 0; i--) {
            for (int j = col - 1; j >= 0; j--) {
                if (matrix[i][j] == '0') {
                    continue;
                }
                int width = Math.min(dp[i][j + 1], dp[i + 1][j]);
                dp[i][j] = width;
                if (matrix[i + width][j + width] == '1') {
                    dp[i][j] = width + 1;
                    res = Math.max(res, width + 1);
                }
            }
        }
        return res * res;
    }
}
