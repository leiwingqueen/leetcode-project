package com.liyongquan.array;

/**
 * 包含整数的二维矩阵 M 表示一个图片的灰度。你需要设计一个平滑器来让每一个单元的灰度成为平均灰度 (向下舍入) ，平均灰度的计算是周围的8个单元和它本身的值求平均，如果周围的单元格不足八个，则尽可能多的利用它们。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * [[1,1,1],
 * [1,0,1],
 * [1,1,1]]
 * 输出:
 * [[0, 0, 0],
 * [0, 0, 0],
 * [0, 0, 0]]
 * 解释:
 * 对于点 (0,0), (0,2), (2,0), (2,2): 平均(3/4) = 平均(0.75) = 0
 * 对于点 (0,1), (1,0), (1,2), (2,1): 平均(5/6) = 平均(0.83333333) = 0
 * 对于点 (1,1): 平均(8/9) = 平均(0.88888889) = 0
 * 注意:
 * <p>
 * 给定矩阵中的整数范围为 [0, 255]。
 * 矩阵的长和宽的范围均为 [1, 150]。
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/image-smoother
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ImageSmoother {
    /**
     * 没什么特殊算法，傻瓜计算
     * <p>
     * 时间负责度O(9*n)，空间复杂度O(n)
     * n是结点数量
     *
     * @param M
     * @return
     */
    public int[][] imageSmoother(int[][] M) {
        if (M.length == 0) {
            return new int[0][0];
        }
        int row = M.length, col = M[0].length;
        int[][] result = new int[row][col];
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[0].length; j++) {
                result[i][j] = cal(M, i, j);
            }
        }
        return result;
    }

    private int cal(int[][] matrix, int x, int y) {
        int c1 = 0, c2 = 0;
        for (int i = x - 1; i <= x + 1; i++) {
            if (i < 0 || i >= matrix.length) {
                continue;
            }
            for (int j = y - 1; j <= y + 1; j++) {
                if (j < 0 || j >= matrix[0].length) {
                    continue;
                }
                c1++;
                c2 += matrix[i][j];
            }
        }
        return c2 / c1;
    }

    /**
     * 使用低8位存储原数组，高8位存储更新后的数据，减少多余数据存储
     *
     * @param M
     * @return
     */
    public int[][] imageSmoother2(int[][] M) {
        if (M.length == 0) {
            return new int[0][0];
        }
        int row = M.length, col = M[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                M[i][j] |= (cal2(M, i, j) << 8);
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                M[i][j] >>= 8;
            }
        }
        return M;
    }

    private int cal2(int[][] matrix, int x, int y) {
        int c1 = 0, c2 = 0;
        for (int i = x - 1; i <= x + 1; i++) {
            if (i < 0 || i >= matrix.length) {
                continue;
            }
            for (int j = y - 1; j <= y + 1; j++) {
                if (j < 0 || j >= matrix[0].length) {
                    continue;
                }
                c1++;
                c2 += (matrix[i][j] & 255);
            }
        }
        return c2 / c1;
    }
}
