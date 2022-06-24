package com.liyongquan.design;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 拯救企鹅游戏-简化版(每个格子是一个正方形)
 * <p>
 * 假设有m*n的矩阵，1表示有格子在上面，0表示空。矩阵的边缘有固定的墙壁。
 * <p>
 * 砖块[i,j]不掉落的条件：
 * 对立的两面刚好挨着墙壁 [i-1,j]和[i+1,j]是墙壁，或者[i,j-1]和[i,j+1]是墙壁
 * <p>
 * 注意：墙壁相邻具有传递性：假设[i,j-1]的左侧是墙壁，那么[i,j]的左侧也相当于是墙壁
 * eg.
 */
public class PenguinTrap {
    /**
     * bfs解决，从矩阵的边缘开始计算挨着墙壁的边
     *
     * O(m*n)
     *
     * @param matrix
     * @return 更新后的砖块
     */
    public int[][] next(int[][] matrix) {
        if (matrix.length == 0) {
            return new int[][]{};
        }
        //分别从左上角和右下角做bfs
        int row = matrix.length, col = matrix[0].length;
        Block[][] blocks = new Block[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                blocks[i][j] = new Block();
            }
        }
        bfs(matrix, row, col, new int[]{0, 0}, blocks, DIR.LEFT_TOP);
        bfs(matrix, row, col, new int[]{row - 1, col - 1}, blocks, DIR.RIGHT_DOWN);
        //计算每个节点是否能保留
        int[][] res = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (blocks[i][j].exist()) {
                    res[i][j] = 1;
                }
            }
        }
        return res;
    }

    /**
     * bfs遍历
     *
     * @param matrix
     * @param row
     * @param col
     * @param start  开始扫描的位点
     * @param blocks
     * @param dir    遍历的方向
     */
    private void bfs(int[][] matrix, int row, int col, int[] start, Block[][] blocks, DIR dir) {
        int[][] visit = new int[row][col];
        //bfs，存放扫描的坐标
        Queue<int[]> queue = new LinkedList<>();
        queue.add(start);
        visit[start[0]][start[1]] = 1;
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int x = point[0], y = point[1];
            //有格子
            if (matrix[x][y] == 1) {
                //更新边
                if (DIR.LEFT_TOP == dir) {
                    if (x == 0 || matrix[x - 1][y] == 1 && blocks[x - 1][y].up) {
                        blocks[x][y].up = true;
                    }
                    if (y == 0 || matrix[x][y - 1] == 1 && blocks[x][y - 1].left) {
                        blocks[x][y].left = true;
                    }
                } else {
                    if (x == row - 1 || matrix[x + 1][y] == 1 && blocks[x + 1][y].down) {
                        blocks[x][y].down = true;
                    }
                    if (y == col - 1 || matrix[x][y + 1] == 1 && blocks[x][y + 1].right) {
                        blocks[x][y].right = true;
                    }
                }
            }
            //查找相邻节点
            if (DIR.LEFT_TOP == dir) {
                int rx = x, ry = y + 1;
                if (ry < col && visit[rx][ry] == 0) {
                    visit[rx][ry] = 1;
                    queue.add(new int[]{rx, ry});
                }
                int dx = x + 1, dy = y;
                if (dx < row && visit[dx][dy] == 0) {
                    visit[dx][dy] = 1;
                    queue.add(new int[]{dx, dy});
                }
            } else {
                int lx = x, ly = y - 1;
                if (ly >= 0 && visit[lx][ly] == 0) {
                    visit[lx][ly] = 1;
                    queue.add(new int[]{lx, ly});
                }
                int ux = x - 1, uy = y;
                if (ux >= 0 && visit[ux][uy] == 0) {
                    visit[ux][uy] = 1;
                    queue.add(new int[]{ux, uy});
                }
            }
        }
    }

    private static class Block {
        //对应4条边是否有挨着墙壁。上下左右
        boolean up;
        boolean down;
        boolean left;
        boolean right;

        public boolean exist() {
            return up && down || left && right;
        }
    }

    private enum DIR {
        LEFT_TOP,
        RIGHT_DOWN
    }
}
