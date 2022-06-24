package com.liyongquan.bfs;

import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个包含了一些 0 和 1 的非空二维数组 grid 。
 * <p>
 * 一个 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在水平或者竖直方向上相邻。你可以假设 grid 的四个边缘都被 0（代表水）包围着。
 * <p>
 * 找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为 0 。)
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * [[0,0,1,0,0,0,0,1,0,0,0,0,0],
 * [0,0,0,0,0,0,0,1,1,1,0,0,0],
 * [0,1,1,0,1,0,0,0,0,0,0,0,0],
 * [0,1,0,0,1,1,0,0,1,0,1,0,0],
 * [0,1,0,0,1,1,0,0,1,1,1,0,0],
 * [0,0,0,0,0,0,0,0,0,0,1,0,0],
 * [0,0,0,0,0,0,0,1,1,1,0,0,0],
 * [0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * 对于上面这个给定矩阵应返回 6。注意答案不应该是 11 ，因为岛屿只能包含水平或垂直的四个方向的 1 。
 * <p>
 * 示例 2:
 * <p>
 * [[0,0,0,0,0,0,0,0]]
 * 对于上面这个给定的矩阵, 返回 0。
 * <p>
 *  
 * <p>
 * 注意: 给定的矩阵grid 的长度和宽度都不超过 50。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/max-area-of-island
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaxAreaOfIsland {
    public int maxAreaOfIsland(int[][] grid) {
        if (grid.length == 0) {
            return 0;
        }
        int row = grid.length, col = grid[0].length;
        int[][] visit = new int[row][col];
        int max = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (visit[i][j] == 0 && grid[i][j] == 1) {
                    int bfs = bfs(grid, visit, i, j);
                    if (bfs > max) {
                        max = bfs;
                    }
                }
            }
        }
        return max;
    }

    private int bfs(int[][] grid, int[][] visit, int x, int y) {
        Queue<Position> queue = new LinkedList<>();
        queue.add(new Position(x, y));
        visit[x][y] = 1;
        int count = 1;
        while (!queue.isEmpty()) {
            Position position = queue.poll();
            //写在这里会超时，这是由于在出栈的时候处理会导致重复入队，关键点，标红！
            //visit[position.x][position.y] = 1;
            if (position.x - 1 >= 0 && visit[position.x - 1][position.y] == 0 && grid[position.x - 1][position.y] == 1) {
                queue.add(new Position(position.x - 1, position.y));
                visit[position.x - 1][position.y] = 1;
                count++;
            }
            if (position.x + 1 < grid.length && visit[position.x + 1][position.y] == 0 && grid[position.x + 1][position.y] == 1) {
                queue.add(new Position(position.x + 1, position.y));
                visit[position.x + 1][position.y] = 1;
                count++;
            }
            if (position.y - 1 >= 0 && visit[position.x][position.y - 1] == 0 && grid[position.x][position.y - 1] == 1) {
                queue.add(new Position(position.x, position.y - 1));
                visit[position.x][position.y - 1] = 1;
                count++;
            }
            if (position.y + 1 < grid[0].length && visit[position.x][position.y + 1] == 0 && grid[position.x][position.y + 1] == 1) {
                queue.add(new Position(position.x, position.y + 1));
                visit[position.x][position.y + 1] = 1;
                count++;
            }
        }
        return count;
    }

    class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    /**
     * 优化2。减少一个visit的变量，我们把访问过的陆地直接变成海洋即可
     *
     * @param grid
     * @return
     */
    public int maxAreaOfIsland2(int[][] grid) {
        if (grid.length == 0) {
            return 0;
        }
        int row = grid.length, col = grid[0].length;
        int max = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    int bfs = bfs2(grid, i, j);
                    if (bfs > max) {
                        max = bfs;
                    }
                }
            }
        }
        return max;
    }

    public static final int[][] direct = new int[][]{
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1}
    };

    private int bfs2(int[][] grid, int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        grid[x][y] = 0;
        int count = 1;
        while (!queue.isEmpty()) {
            int[] position = queue.poll();
            //写在这里会超时，这是由于在出栈的时候处理会导致重复入队，关键点，标红！
            //visit[position.x][position.y] = 1;
            for (int i = 0; i < direct.length; i++) {
                int x1 = position[0] + direct[i][0];
                int y1 = position[1] + direct[i][1];
                if (x1 < 0 || x1 >= grid.length || y1 < 0 || y1 >= grid[0].length || grid[x1][y1] == 0) {
                    continue;
                }
                grid[x1][y1] = 0;
                count++;
                queue.add(new int[]{x1, y1});
            }
        }
        return count;
    }
}
