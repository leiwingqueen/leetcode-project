package com.liyongquan.weeklycontest.bwc52;

public class RotateTheBox {
    public char[][] rotateTheBox(char[][] box) {
        //先把矩阵旋转
        int row = box.length, col = box[0].length;
        char[][] matrix = new char[col][row];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix[j][row - i - 1] = box[i][j];
            }
        }
        //更新矩阵
        for (int i = 0; i < row; i++) {
            for (int j = col - 1; j >= 0; j--) {
                if (matrix[j][i] == '#') {
                    //尽可能向下移动
                    int bottom = j;
                    while (bottom + 1 < col && matrix[bottom + 1][i] == '.') {
                        bottom++;
                    }
                    if (bottom != j) {
                        matrix[bottom][i] = '#';
                        matrix[j][i] = '.';
                    }
                }
            }
        }
        return matrix;
    }
}
