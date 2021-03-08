package com.liyongquan.design;

import org.junit.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.Assert.*;

public class PenguinTrap2Test {
    private PenguinTrap2 pt = new PenguinTrap2();

    @Test
    public void testNext() {
        int[][] matrix = {
                {-1, -1, -1, 0, 0, 0, 0},
                {-1, -1, 0, 0, 0, 0, 0},
                {-1, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 1, 1},
                {0, 0, 0, 0, 0, 0, -1},
                {0, 0, 0, 0, 0, -1, -1},
                {0, 0, 0, 0, -1, -1, -1},
        };
        test(matrix);
    }

    @Test
    public void testNext2() {
        int[][] matrix = {
                {-1, -1, -1, 1, 0, 0, 0},
                {-1, -1, 0, 1, 0, 0, 0},
                {-1, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 1, 1, 1, 1},
                {0, 0, 1, 0, 0, 0, -1},
                {0, 1, 0, 0, 0, -1, -1},
                {1, 0, 0, 0, -1, -1, -1},
        };
        test(matrix);
    }

    private void test(int[][] matrix) {
        System.out.println("================更新前==============");
        print(matrix);
        int[][] next = pt.next(matrix);
        System.out.println("================更新后==============");
        print(next);
    }

    private void print(int[][] matrix) {
        int row = matrix.length, col = matrix[0].length;
        for (int i = 0; i < row; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == -1) {
                    sb.append("* ");
                } else {
                    sb.append(matrix[i][j] + " ");
                }
            }
            System.out.println(sb.toString());
        }
        System.out.println();
    }
}