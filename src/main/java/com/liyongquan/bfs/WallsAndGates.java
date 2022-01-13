package com.liyongquan.bfs;

import java.util.LinkedList;
import java.util.Queue;

public class WallsAndGates {
    public static final int[][] DIRS = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1},
    };

    public void wallsAndGates(int[][] rooms) {
        int row = rooms.length;
        int col = rooms[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (rooms[i][j] == Integer.MAX_VALUE) {
                    int path = bfs(rooms, row, col, new int[]{i, j});
                    rooms[i][j] = path;
                }
            }
        }
    }

    private int bfs(int[][] rooms, int row, int col, int[] pos) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visit = new boolean[row][col];
        queue.add(pos);
        visit[pos[0]][pos[1]] = true;
        int depth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            depth++;
            for (int i = 0; i < size; i++) {
                int[] poll = queue.poll();
                for (int[] dir : DIRS) {
                    int x = poll[0] + dir[0];
                    int y = poll[1] + dir[1];
                    if (x >= 0 && x < row && y >= 0 && y < col && !visit[x][y] && rooms[x][y] >= 0) {
                        if (rooms[x][y] == 0) {
                            return depth;
                        }
                        visit[x][y] = true;
                        queue.add(new int[]{x, y});
                    }
                }
            }
        }
        return Integer.MAX_VALUE;
    }
}
