package com.liyongquan.array;

import java.rmi.MarshalException;

/**
 * 编写一种算法，若M × N矩阵中某个元素为0，则将其所在的行与列清零。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：
 * [
 * [1,1,1],
 * [1,0,1],
 * [1,1,1]
 * ]
 * 输出：
 * [
 * [1,0,1],
 * [0,0,0],
 * [1,0,1]
 * ]
 * 示例 2：
 * <p>
 * 输入：
 * [
 * [0,1,2,0],
 * [3,4,5,2],
 * [1,3,1,5]
 * ]
 * 输出：
 * [
 * [0,0,0,0],
 * [0,4,5,0],
 * [0,3,1,0]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zero-matrix-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SetZeroes {

    /**
     * 先尝试使用m*n的额外空间
     *
     * @param matrix
     */
    public void setZeroes2(int[][] matrix) {
        if (matrix.length == 0) {
            return;
        }
        int row = matrix.length, col = matrix[0].length;
        int[][] tmp = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                tmp[i][j] = matrix[i][j];
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (tmp[i][j] == 0) {
                    for (int k = 0; k < col; k++) {
                        matrix[i][k] = 0;
                    }
                    for (int k = 0; k < row; k++) {
                        matrix[k][j] = 0;
                    }
                }
            }
        }
    }

    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        //分别为0,1的行
        int[] row = new int[m];
        int[] col = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = 1;
                    col[j] = 1;
                }
            }
        }
        //设置0
        for (int i = 0; i < m; i++) {
            if (row[i] == 1) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (col[i] == 1) {
                for (int j = 0; j < m; j++) {
                    matrix[j][i] = 0;
                }
            }
        }
    }

    public static void main(String[] args) {
        SetZeroes sz = new SetZeroes();
        int[][] matrix = {
                {0, 1, 2, 0},
                {3, 4, 5, 2},
                {1, 3, 1, 5}};
        sz.setZeroes(matrix);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + ",");
            }
            System.out.println();
        }
    }
}
