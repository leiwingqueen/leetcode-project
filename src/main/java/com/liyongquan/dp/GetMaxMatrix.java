package com.liyongquan.dp;

/**
 * 面试题 17.24. 最大子矩阵
 * <p>
 * 给定一个正整数、负整数和 0 组成的 N × M 矩阵，编写代码找出元素总和最大的子矩阵。
 * <p>
 * 返回一个数组 [r1, c1, r2, c2]，其中 r1, c1 分别代表子矩阵左上角的行号和列号，r2, c2 分别代表右下角的行号和列号。若有多个满足条件的子矩阵，返回任意一个均可。
 * <p>
 * 注意：本题相对书上原题稍作改动
 * <p>
 * 示例：
 * <p>
 * 输入：
 * [
 *    [-1,0],
 *    [0,-1]
 * ]
 * 输出：[0,1,0,1]
 * 解释：输入中标粗的元素即为输出所表示的矩阵
 *  
 * <p>
 * 说明：
 * <p>
 * 1 <= matrix.length, matrix[0].length <= 200
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/max-submatrix-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class GetMaxMatrix {
    /**
     * 暴力解法
     * <p>
     * 时间复杂度O((m*n)^3)
     * <p>
     * 超时
     *
     * @param matrix
     * @return
     */
    public int[] getMaxMatrix(int[][] matrix) {
        int row = matrix.length, col = matrix[0].length;
        //遍历起始点
        int max = Integer.MIN_VALUE;
        int[] res = new int[]{};
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                //遍历结束点
                for (int k = i; k < row; k++) {
                    for (int l = j; l < col; l++) {
                        //计算[i,j][k,l]的总和
                        int sum = 0;
                        for (int m = i; m <= k; m++) {
                            for (int n = j; n <= l; n++) {
                                sum += matrix[m][n];
                            }
                        }
                        if (sum > max) {
                            max = sum;
                            res = new int[]{i, j, k, l};
                        }
                    }
                }
            }
        }
        return res;
    }

    /**
     * 先考虑检查计算总和的时间复杂度
     *
     * 时间复杂度O((m*n)^2)
     * 空间复杂度O(m*n)
     *
     * 还是超时
     *
     * @param matrix
     * @return
     */
    public int[] getMaxMatrix2(int[][] matrix) {
        int row = matrix.length, col = matrix[0].length;
        //存放[[0,0],[i,j])的总和
        int[][] dp = new int[row + 1][col + 1];
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1] + matrix[i - 1][j - 1] - dp[i - 1][j - 1];
            }
        }
        //遍历
        int[] res = new int[]{};
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                for (int m = i; m < row; m++) {
                    for (int n = j; n < col; n++) {
                        //计算[i,j][k,l]的总和
                        int sum = dp[m + 1][n + 1] - dp[i][n + 1] - dp[m + 1][j] + dp[i][j];
                        if (sum > max) {
                            res = new int[]{i, j, m, n};
                            max = sum;
                        }
                    }
                }
            }
        }
        return res;
    }
}
