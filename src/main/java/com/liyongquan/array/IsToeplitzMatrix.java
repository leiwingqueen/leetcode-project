package com.liyongquan.array;

/**
 * 766. 托普利茨矩阵
 * <p>
 * <p>
 * 给你一个 m x n 的矩阵 matrix 。如果这个矩阵是托普利茨矩阵，返回 true ；否则，返回 false 。
 * <p>
 * 如果矩阵上每一条由左上到右下的对角线上的元素都相同，那么这个矩阵是 托普利茨矩阵 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：matrix = [[1,2,3,4],[5,1,2,3],[9,5,1,2]]
 * 输出：true
 * 解释：
 * 在上述矩阵中, 其对角线为:
 * "[9]", "[5, 5]", "[1, 1, 1]", "[2, 2, 2]", "[3, 3]", "[4]"。
 * 各条对角线上的所有元素均相同, 因此答案是 True 。
 * 示例 2：
 * <p>
 * <p>
 * 输入：matrix = [[1,2],[2,2]]
 * 输出：false
 * 解释：
 * 对角线 "[1, 2]" 上的元素不同。
 *  
 * <p>
 * 提示：
 * <p>
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 20
 * 0 <= matrix[i][j] <= 99
 *  
 * <p>
 * 进阶：
 * <p>
 * 如果矩阵存储在磁盘上，并且内存有限，以至于一次最多只能将矩阵的一行加载到内存中，该怎么办？
 * 如果矩阵太大，以至于一次只能将不完整的一行加载到内存中，该怎么办？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/toeplitz-matrix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class IsToeplitzMatrix {
    /**
     * 同一个斜线上意味着坐标(x1,y1),(x2,y2)，有如下公式x1-y1=x2-y2
     * <p>
     * 对于m行n列的二维数组，相减的范围为[-n+1,m-1]。
     * <p>
     * 这个写得复杂度了。。
     *
     * @param matrix
     * @return
     */
    public boolean isToeplitzMatrix(int[][] matrix) {
        int row = matrix.length, col = matrix[0].length;
        //起点在第一列的斜线
        for (int i = 0; i < row; i++) {
            if (!scan(matrix, new int[]{i, 0}, row, col)) {
                return false;
            }
        }
        //起点在第一行的斜线
        for (int i = 1; i < col; i++) {
            if (!scan(matrix, new int[]{0, i}, row, col)) {
                return false;
            }
        }
        return true;
    }

    private boolean scan(int[][] matrix, int[] start, int row, int col) {
        int[] cur = {start[0], start[1]};
        while (cur[0] < row && cur[1] < col) {
            if (matrix[cur[0]][cur[1]] != matrix[start[0]][start[1]]) {
                return false;
            }
            cur[0]++;
            cur[1]++;
        }
        return true;
    }

    /**
     * 解法2，我们只需要检查每个元素是否和左上方的元素相等即可。由于第一行和第一列的元素不存在左上方的元素，因此行列的索引我们都从1开始即可
     *
     * @param matrix
     * @return
     */
    public boolean isToeplitzMatrix2(int[][] matrix) {
        int row = matrix.length, col = matrix[0].length;
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (matrix[i][j] != matrix[i - 1][j - 1]) {
                    return false;
                }
            }
        }
        return true;
    }


    public static final int GRID_LEN = 3;

    /**
     * 如果矩阵太大，以至于一次只能将不完整的一行加载到内存中，该怎么办？
     * <p>
     * 划分子矩阵，并且保证每个矩阵有一行/一列重叠
     *
     * @param matrix
     * @return
     */
    public boolean isToeplitzMatrix3(int[][] matrix) {
        int row = matrix.length, col = matrix[0].length;
        for (int i = 0; i < row; i += GRID_LEN - 1) {
            for (int j = 0; j < col; j += GRID_LEN - 1) {
                //左上角[i,j],右下角[i+GRID_LEN-1,j+GRID_LEN-1]
                int[] rightDown = {i + GRID_LEN - 1, j + GRID_LEN - 1};
                if (rightDown[0] >= row) {
                    rightDown[0] = row - 1;
                }
                if (rightDown[1] >= col) {
                    rightDown[1] = col - 1;
                }
                if (!helper(matrix, new int[]{i, j}, rightDown)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean helper(int[][] matrix, int[] leftTop, int[] rightDown) {
        for (int i = leftTop[0] + 1; i <= rightDown[0]; i++) {
            for (int j = leftTop[1] + 1; j <= rightDown[1]; j++) {
                if (matrix[i][j] != matrix[i - 1][j - 1]) {
                    return false;
                }
            }
        }
        return true;
    }
}
