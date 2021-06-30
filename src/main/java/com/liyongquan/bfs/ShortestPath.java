package com.liyongquan.bfs;

import javafx.util.Pair;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//给你一个 m * n 的网格，其中每个单元格不是 0（空）就是 1（障碍物）。每一步，您都可以在空白单元格中上、下、左、右移动。
//
//如果您 最多 可以消除 k 个障碍物，请找出从左上角 (0, 0) 到右下角 (m-1, n-1) 的最短路径，并返回通过该路径所需的步数。如果找不到这样的路径，则返回 -1。
//
// 
//
//示例 1：
//
//输入：
//grid =
//[[0,0,0],
// [1,1,0],
// [0,0,0],
// [0,1,1],
// [0,0,0]],
//k = 1
//输出：6
//解释：
//不消除任何障碍的最短路径是 10。
//消除位置 (3,2) 处的障碍后，最短路径是 6 。该路径是 (0,0) -> (0,1) -> (0,2) -> (1,2) -> (2,2) -> (3,2) -> (4,2).
// 
//
//示例 2：
//
//输入：
//grid =
//[[0,1,1],
// [1,1,1],
// [1,0,0]],
//k = 1
//输出：-1
//解释：
//我们至少需要消除两个障碍才能找到这样的路径。
// 
//
//提示：
//
//grid.length == m
//grid[0].length == n
//1 <= m, n <= 40
//1 <= k <= m*n
//grid[i][j] == 0 or 1
//grid[0][0] == grid[m-1][n-1] == 0
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/shortest-path-in-a-grid-with-obstacles-elimination
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class ShortestPath {
    public static final int[][] DIR = {
            {-1, 0},
            {1, 0},
            {0, 1},
            {0, -1},
    };

    /**
     * 如果地图确定，用bfs即可找到最短路径，那么我们的思路是把所有地图枚举出来
     *
     * @param grid
     * @param k
     * @return
     */
    public int shortestPath(int[][] grid, int k) {
        int row = grid.length, col = grid[0].length;
        //统计障碍数量
        int block = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    block += 1;
                }
            }
        }
        //统计地图数量
        List<int[][]> mapList = new LinkedList<>();
        if (block <= k) {
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    grid[i][j] = 0;
                }
            }
            mapList.add(grid);
        } else {
            backtrace(grid, row, col, block, k, new int[]{0, 0}, mapList);
        }
        //bfs找到最优路径
        int min = Integer.MAX_VALUE;
        for (int[][] map : mapList) {
            int r = bfs(map, row, col);
            if (r >= 0) {
                min = Math.min(r, min);
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    private int bfs(int[][] grid, int row, int col) {
        if (grid[0][0] == 1 || grid[row - 1][col - 1] == 1) {
            return -1;
        }
        if (row == 1 && col == 1) {
            return 0;
        }
        Queue<Pair<int[], Integer>> queue = new LinkedList<>();
        queue.add(new Pair<>(new int[]{0, 0}, 0));
        int[][] visit = new int[row][col];
        visit[0][0] = 1;
        while (!queue.isEmpty()) {
            Pair<int[], Integer> poll = queue.poll();
            int[] pos = poll.getKey();
            Integer depth = poll.getValue();
            for (int[] dir : DIR) {
                int nx = pos[0] + dir[0];
                int ny = pos[1] + dir[1];
                if (nx >= 0 && nx < row && ny >= 0 && ny < col && visit[nx][ny] == 0 && grid[nx][ny] == 0) {
                    if (nx == row - 1 && ny == col - 1) {
                        return depth + 1;
                    }
                    queue.add(new Pair<>(new int[]{nx, ny}, depth + 1));
                    visit[nx][ny] = 1;
                }
            }
        }
        return -1;
    }

    private void backtrace(int[][] path, int row, int col, int block,
                           int k, int[] pos, List<int[][]> res) {
        int x = pos[0], y = pos[1];
        if (block == 0 || k == 0 || x >= row || y >= col) {
            res.add(clone(path));
            return;
        }
        if (block <= k) {
            int[][] r = clone(path);
            //后面所有的障碍全部消除
            for (int i = x; i < row; i++) {
                for (int j = y; j < col; j++) {
                    r[i][j] = 0;
                }
            }
            res.add(r);
            return;
        }
        int nx = x, ny = y;
        ny++;
        if (ny == col) {
            ny = 0;
            nx++;
        }
        if (path[x][y] == 0) {
            backtrace(path, row, col, block, k, new int[]{nx, ny}, res);
        } else {
            //消除/不消除
            backtrace(path, row, col, block - 1, k, new int[]{nx, ny}, res);
            path[x][y] = 0;
            backtrace(path, row, col, block - 1, k - 1, new int[]{nx, ny}, res);
            path[x][y] = 1;
        }
    }

    private int[][] clone(int[][] grid) {
        int row = grid.length, col = grid[0].length;
        int[][] res = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                res[i][j] = grid[i][j];
            }
        }
        return res;
    }
}
