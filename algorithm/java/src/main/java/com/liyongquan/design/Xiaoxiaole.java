package com.liyongquan.design;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 简单的消消乐算法
 */
public class Xiaoxiaole {
    public static final int[][] DIRS = {
            {0, -1},
            {0, 1},
            {-1, 0},
            {1, 0}
    };

    /**
     * grid表示消消乐的图，其中0表示为空，>0表示对应的道具。k表示至少相连k个才能消除
     *
     * @param grid
     * @param k
     */
    public void scan(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }
                List<int[]> rmList = bfs(grid, new int[]{i, j}, m, n);
                if (rmList.size() >= k) {
                    //满足条件开始消除
                    for (int[] rm : rmList) {
                        grid[rm[0]][rm[1]] = 0;
                    }
                }
            }
        }
    }

    private List<int[]> bfs(int[][] grid, int[] cur, int m, int n) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visit = new boolean[m][n];
        queue.add(cur);
        int color = grid[cur[0]][cur[1]];
        visit[cur[0]][cur[1]] = true;
        List<int[]> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            res.add(poll);
            for (int[] dir : DIRS) {
                int x = poll[0] + dir[0];
                int y = poll[1] + dir[1];
                if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == color && !visit[x][y]) {
                    queue.add(new int[]{x, y});
                    visit[x][y] = true;
                }
            }
        }
        return res;
    }
}
