package com.liyongquan.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * <p>
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向或竖直方向上相邻的陆地连接形成。
 * <p>
 * 此外，你可以假设该网格的四条边均被水包围。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * [
 * ['1','1','1','1','0'],
 * ['1','1','0','1','0'],
 * ['1','1','0','0','0'],
 * ['0','0','0','0','0']
 * ]
 * 输出: 1
 * 示例 2:
 * <p>
 * 输入:
 * [
 * ['1','1','0','0','0'],
 * ['1','1','0','0','0'],
 * ['0','0','1','0','0'],
 * ['0','0','0','1','1']
 * ]
 * 输出: 3
 * 解释: 每座岛屿只能由水平和/或竖直方向上相邻的陆地连接而成。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-islands
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NumIslands {
    /**
     * 找到一块没有访问过的陆地，然后用bfs算法把周围的陆地标记。
     *
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        if (grid.length == 0) {
            return 0;
        }
        int row = grid.length, col = grid[0].length;
        int[][] visit = new int[row][col];
        int count = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (visit[i][j] == 0 && grid[i][j] == '1') {
                    bfs(grid, visit, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    private void bfs(char[][] grid, int[][] visit, int x, int y) {
        Queue<Position> queue = new LinkedList<>();
        queue.add(new Position(x, y));
        visit[x][y] = 1;
        while (!queue.isEmpty()) {
            Position position = queue.poll();
            //写在这里会超时，这是由于在出栈的时候处理会导致重复入队，关键点，标红！
            //visit[position.x][position.y] = 1;
            if (position.x - 1 >= 0 && visit[position.x - 1][position.y] == 0 && grid[position.x - 1][position.y] == '1') {
                queue.add(new Position(position.x - 1, position.y));
                visit[position.x - 1][position.y] = 1;
            }
            if (position.x + 1 < grid.length && visit[position.x + 1][position.y] == 0 && grid[position.x + 1][position.y] == '1') {
                queue.add(new Position(position.x + 1, position.y));
                visit[position.x + 1][position.y] = 1;
            }
            if (position.y - 1 >= 0 && visit[position.x][position.y - 1] == 0 && grid[position.x][position.y - 1] == '1') {
                queue.add(new Position(position.x, position.y - 1));
                visit[position.x][position.y - 1] = 1;
            }
            if (position.y + 1 < grid[0].length && visit[position.x][position.y + 1] == 0 && grid[position.x][position.y + 1] == '1') {
                queue.add(new Position(position.x, position.y + 1));
                visit[position.x][position.y + 1] = 1;
            }
        }
    }

    class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
