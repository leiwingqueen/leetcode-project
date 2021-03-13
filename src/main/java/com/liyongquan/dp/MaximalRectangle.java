package com.liyongquan.dp;

/**
 * 给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * 输出：6
 * 解释：最大矩形如上图所示。
 * 示例 2：
 * <p>
 * 输入：matrix = []
 * 输出：0
 * 示例 3：
 * <p>
 * 输入：matrix = [["0"]]
 * 输出：0
 * 示例 4：
 * <p>
 * 输入：matrix = [["1"]]
 * 输出：1
 * 示例 5：
 * <p>
 * 输入：matrix = [["0","0"]]
 * 输出：0
 *  
 * <p>
 * 提示：
 * <p>
 * rows == matrix.length
 * cols == matrix[0].length
 * 0 <= row, cols <= 200
 * matrix[i][j] 为 '0' 或 '1'
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximal-rectangle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaximalRectangle {
    /**
     * 暴力解法
     *
     * 时间复杂度O(n^5)
     *
     * @param matrix
     * @return
     */
    public int maximalRectangle(char[][] matrix) {
        int row = matrix.length;
        if (row == 0) {
            return 0;
        }
        int col = matrix[0].length;
        int res = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == '0') {
                    continue;
                }
                for (int area = res + 1; area <= (row - i) * (col - j); area++) {
                    for (int height = 1; height <= area && i + height - 1 < row; height++) {
                        if (area % height != 0) {
                            continue;
                        }
                        int width = area / height;
                        if (j + width - 1 >= col) {
                            continue;
                        }
                        if (isRectangle(matrix, i, j, width, height)) {
                            res = area;
                            break;
                        }
                    }
                }
            }
        }
        return res;
    }

    private boolean isRectangle(char[][] matrix, int x, int y, int width, int height) {
        for (int i = x; i < x + height; i++) {
            for (int j = y; j < y + width; j++) {
                if (matrix[i][j] == '0') {
                    return false;
                }
            }
        }
        return true;
    }
}
