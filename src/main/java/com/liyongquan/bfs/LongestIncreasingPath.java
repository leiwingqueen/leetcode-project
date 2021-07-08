package com.liyongquan.bfs;

/**
 * 给定一个 m x n 整数矩阵 matrix ，找出其中 最长递增路径 的长度。
 * <p>
 * 对于每个单元格，你可以往上，下，左，右四个方向移动。 你 不能 在 对角线 方向上移动或移动到 边界外（即不允许环绕）。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：matrix = [[9,9,4],[6,6,8],[2,1,1]]
 * 输出：4
 * 解释：最长递增路径为 [1, 2, 6, 9]。
 * 示例 2：
 * <p>
 * <p>
 * 输入：matrix = [[3,4,5],[3,2,6],[2,2,1]]
 * 输出：4
 * 解释：最长递增路径是 [3, 4, 5, 6]。注意不允许在对角线方向上移动。
 * 示例 3：
 * <p>
 * 输入：matrix = [[1]]
 * 输出：1
 *  
 * <p>
 * 提示：
 * <p>
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 200
 * 0 <= matrix[i][j] <= 231 - 1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-increasing-path-in-a-matrix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LongestIncreasingPath {
    public static final int[][] DIR = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1},
    };

    /**
     * 不用想了，肯定是指数级别的复杂度
     *
     * @param matrix
     * @return
     */
    public int longestIncreasingPath(int[][] matrix) {
        int row = matrix.length, col = matrix[0].length;
        int max = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int[][] visit = new int[row][col];
                visit[i][j] = 1;
                int r = backtrace(new int[]{i, j}, visit, matrix, row, col);
                max = Math.max(max, r);
            }
        }
        return max;
    }

    private int backtrace(int[] pos, int[][] visit, int[][] matrix, int row, int col) {
        int max = 1;
        for (int[] dir : DIR) {
            int x = pos[0] + dir[0];
            int y = pos[1] + dir[1];
            if (x >= 0 && x < row && y >= 0 && y < col && visit[x][y] == 0 && matrix[x][y] > matrix[pos[0]][pos[1]]) {
                visit[x][y] = 1;
                int r = backtrace(new int[]{x, y}, visit, matrix, row, col);
                max = Math.max(r + 1, max);
                visit[x][y] = 0;
            }
        }
        return max;
    }
}
