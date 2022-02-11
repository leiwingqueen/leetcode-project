package com.liyongquan.bfs;

//给你一个大小为 m x n 的二进制矩阵 grid ，其中 0 表示一个海洋单元格、1 表示一个陆地单元格。
//
//一次 移动 是指从一个陆地单元格走到另一个相邻（上、下、左、右）的陆地单元格或跨过 grid 的边界。
//
//返回网格中 无法 在任意次数的移动中离开网格边界的陆地单元格的数量。
//
// 
//
//示例 1：
//
//
//输入：grid = [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
//输出：3
//解释：有三个 1 被 0 包围。一个 1 没有被包围，因为它在边界上。
//示例 2：
//
//
//输入：grid = [[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]
//输出：0
//解释：所有 1 都在边界上或可以到达边界。
// 
//
//提示：
//
//m == grid.length
//n == grid[i].length
//1 <= m, n <= 500
//grid[i][j] 的值为 0 或 1
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/number-of-enclaves
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;

public class NumEnclaves {
    public static final int[][] DIRS = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1},
    };

    public int numEnclaves(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int cnt = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    Pair<Integer, Boolean> pair = bfs(grid, m, n, new int[]{i, j});
                    if (!pair.getValue()) {
                        cnt += pair.getKey();
                    }
                }
            }
        }
        return cnt;
    }

    private Pair<Integer, Boolean> bfs(int[][] grid, int m, int n, int[] pos) {
        boolean flag = false;
        if (pos[0] == 0 || pos[0] == m - 1 || pos[1] == 0 || pos[1] == n - 1) {
            flag = true;
        }
        int cnt = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(pos);
        cnt++;
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int nx = pos[0] + poll[0], ny = pos[1] + poll[1];
            if (nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] == 1) {
                if (nx == 0 || nx == m - 1 || ny == 0 || ny == n - 1) {
                    flag = true;
                }
                grid[nx][ny] = 0;
                cnt++;
                queue.add(new int[]{nx, ny});
            }
        }
        return new Pair<>(cnt, flag);
    }
}
