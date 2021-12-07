package com.liyongquan.graph;

//1034. 边界着色
//给你一个大小为 m x n 的整数矩阵 grid ，表示一个网格。另给你三个整数 row、col 和 color 。网格中的每个值表示该位置处的网格块的颜色。
//
//当两个网格块的颜色相同，而且在四个方向中任意一个方向上相邻时，它们属于同一 连通分量 。
//
//连通分量的边界 是指连通分量中的所有与不在分量中的网格块相邻（四个方向上）的所有网格块，或者在网格的边界上（第一行/列或最后一行/列）的所有网格块。
//
//请你使用指定颜色 color 为所有包含网格块 grid[row][col] 的 连通分量的边界 进行着色，并返回最终的网格 grid 。
//
// 
//
//示例 1：
//
//输入：grid = [[1,1],[1,2]], row = 0, col = 0, color = 3
//输出：[[3,3],[3,2]]
//示例 2：
//
//输入：grid = [[1,2,2],[2,3,2]], row = 0, col = 1, color = 3
//输出：[[1,3,3],[2,3,3]]
//示例 3：
//
//输入：grid = [[1,1,1],[1,1,1],[1,1,1]], row = 1, col = 1, color = 2
//输出：[[2,2,2],[2,1,2],[2,2,2]]
// 
//
//提示：
//
//m == grid.length
//n == grid[i].length
//1 <= m, n <= 50
//1 <= grid[i][j], color <= 1000
//0 <= row < m
//0 <= col < n
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/coloring-a-border
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author liyongquan
 * @date 2021/12/7
 */
public class ColorBorder {
    public static final int[][] DIRS = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1}
    };

    /**
     * bfs，查找连通分量的过程如果发现当前左边是边界，则标记起来
     *
     * 性能击败30%
     *
     * @param grid
     * @param row
     * @param col
     * @param color
     * @return
     */
    public int[][] colorBorder(int[][] grid, int row, int col, int color) {
        int m = grid.length, n = grid[0].length;
        int c = grid[row][col];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{row, col});
        List<int[]> edges = new LinkedList<>();
        if (isEdge(grid, m, n, row, col)) {
            edges.add(new int[]{row, col});
        }
        boolean[][] visit = new boolean[m][n];
        visit[row][col] = true;
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            for (int[] dir : DIRS) {
                int x = poll[0] + dir[0];
                int y = poll[1] + dir[1];
                if (x >= 0 && x < m && y >= 0 && y < n && !visit[x][y] && grid[x][y] == c) {
                    int[] pos = {x, y};
                    queue.add(pos);
                    visit[x][y] = true;
                    if (isEdge(grid, m, n, x, y)) {
                        edges.add(pos);
                    }
                }
            }
        }
        //边缘节点颜色修改
        for (int[] edge : edges) {
            grid[edge[0]][edge[1]] = color;
        }
        return grid;
    }

    private boolean isEdge(int[][] grid, int m, int n, int x, int y) {
        //边缘节点直接返回true
        if (x == 0 || x == m - 1 || y == 0 || y == n - 1) {
            return true;
        }
        for (int[] dir : DIRS) {
            int x1 = x + dir[0];
            int y1 = y + dir[1];
            if (x1 >= 0 && x1 < m && y1 >= 0 && y1 < n && grid[x1][y1] != grid[x][y]) {
                return true;
            }
        }
        return false;
    }
}
