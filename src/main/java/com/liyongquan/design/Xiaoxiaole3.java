package com.liyongquan.design;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 烽火三国-算法更新
 */
public class Xiaoxiaole3 {
    public static final int[][] DIRS = {
            {0, -1},
            {0, 1},
            {-1, 0},
            {1, 0}
    };

    /**
     * 判断从start到pos是否存在一条通路
     *
     * @param matrix 前端的坐标是反的，这里可能要处理下。我这里是按正常的行列坐标表达
     *               1表示已占领，0表示还没有占领
     * @param start
     * @param pos
     * @return
     */
    public boolean canDomain(int[][] matrix, int[] start, int[] pos) {
        //m行,n列。前端的坐标是反的，这里可能要处理下
        int m = matrix.length;
        int n = matrix[0].length;
        //bfs过程
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visit = new boolean[m][n];
        queue.add(start);
        visit[start[0]][start[1]] = true;
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            if (poll[0] == pos[0] && poll[1] == pos[1]) {
                return true;
            }
            for (int[] dir : DIRS) {
                int x = poll[0] + dir[0];
                int y = poll[1] + dir[1];
                if (x >= 0 && x < m && y >= 0 && y < n && matrix[x][y] == 1 && !visit[x][y]) {
                    queue.add(new int[]{x, y});
                    visit[x][y] = true;
                }
            }
        }
        return false;
    }
}
