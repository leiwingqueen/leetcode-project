package com.liyongquan.design;

import org.junit.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.Assert.*;

public class PenguinTrap2Test {

    @Test
    public void next() throws InterruptedException {
        int[][] matrix = {
                {1, 1, 0, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 0, 1, 1},
        };
        char[][] graph = drawGrid(matrix);
        for (int i = 0; i < graph.length; i++) {
            System.out.println(graph[i]);
        }
    }

    public static final int GRID_WIDTH = 4;
    public static final int GRID_HEIGHT = 4;

    private char[][] drawGrid(int[][] matrix) {
        //初始化
        int row = matrix.length, col = matrix[0].length;
        int gRow = 2 * row + 2;
        int gCol = 4 * col + (row - 1) * 2;
        char[][] graph = new char[gRow][gCol];
        for (int i = 0; i < gRow; i++) {
            for (int j = 0; j < gCol; j++) {
                graph[i][j] = ' ';
            }
        }
        for (int i = 0; i < row; i++) {
            char filter = i % 2 == 0 ? '*' : 'O';
            drawLine(matrix[i], graph, new int[]{i * 2, i * 2}, filter);
        }
        return graph;
    }

    private void drawLine(int[] matrix, char[][] graph, int[] slice, char filter) {
        int len = matrix.length;
        for (int i = 0; i < len; i++) {
            if (matrix[i]==1) {
                graph[slice[0]][slice[1] + i * GRID_WIDTH + 1] = filter;
                for (int j = 1; j <= 2; j++) {
                    graph[slice[0] + j][slice[1] + i * GRID_WIDTH] = filter;
                    graph[slice[0] + j][slice[1] + i * GRID_WIDTH + 1] = filter;
                    graph[slice[0] + j][slice[1] + i * GRID_WIDTH + 2] = filter;
                }
                graph[slice[0] + 3][slice[1] + i * GRID_WIDTH + 1] = filter;
            }
        }
    }

}