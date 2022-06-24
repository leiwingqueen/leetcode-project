package com.liyongquan.design;

import org.junit.Test;

import static org.junit.Assert.*;

public class PenguinTrapTest {
    private PenguinTrap pt = new PenguinTrap();

    @Test
    public void next1() {
        int[][] matrix = {
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {1, 1, 1, 1, 1},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
        };
        test(matrix);
    }

    @Test
    public void next2() {
        int[][] matrix = {
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {1, 1, 0, 1, 1},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
        };
        test(matrix);
    }

    @Test
    public void next3() {
        int[][] matrix = {
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
        };
        test(matrix);
    }

    @Test
    public void next4() {
        int[][] matrix = {
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {1, 1, 1, 0, 0},
                {0, 0, 0, 1, 0},
                {0, 0, 0, 0, 1},
        };
        test(matrix);
    }

    @Test
    public void next5() {
        int[][] matrix = {
                {0, 0, 1, 0, 1},
                {0, 0, 1, 0, 1},
                {1, 1, 0, 0, 1},
                {0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1},
        };
        test(matrix);
    }

    @Test
    public void next6() {
        int[][] matrix = {
                {0, 0, 1, 0, 1},
                {0, 0, 1, 0, 1},
                {1, 1, 0, 0, 1},
                {0, 0, 0, 0, 1},
                {1, 1, 0, 1, 1},
        };
        test(matrix);
    }

    @Test
    public void next7() {
        int[][] matrix = {
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {1, 1, 1, 1, 1},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
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
                sb.append(matrix[i][j] + " ");
            }
            System.out.println(sb.toString());
        }
        System.out.println();
    }
}