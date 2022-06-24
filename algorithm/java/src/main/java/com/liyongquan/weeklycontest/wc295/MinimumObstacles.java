package com.liyongquan.weeklycontest.wc295;

//6081. 到达角落需要移除障碍物的最小数目
//给你一个下标从 0 开始的二维整数数组 grid ，数组大小为 m x n 。每个单元格都是两个值之一：
//
//0 表示一个 空 单元格，
//1 表示一个可以移除的 障碍物 。
//你可以向上、下、左、右移动，从一个空单元格移动到另一个空单元格。
//
//现在你需要从左上角 (0, 0) 移动到右下角 (m - 1, n - 1) ，返回需要移除的障碍物的 最小 数目。
//
// 
//
//示例 1：
//
//
//
//输入：grid = [[0,1,1],[1,1,0],[1,1,0]]
//输出：2
//解释：可以移除位于 (0, 1) 和 (0, 2) 的障碍物来创建从 (0, 0) 到 (2, 2) 的路径。
//可以证明我们至少需要移除两个障碍物，所以返回 2 。
//注意，可能存在其他方式来移除 2 个障碍物，创建出可行的路径。
//示例 2：
//
//
//
//输入：grid = [[0,1,0,0,0],[0,1,0,1,0],[0,0,0,1,0]]
//输出：0
//解释：不移除任何障碍物就能从 (0, 0) 到 (2, 4) ，所以返回 0 。
// 
//
//提示：
//
//m == grid.length
//n == grid[i].length
//1 <= m, n <= 105
//2 <= m * n <= 105
//grid[i][j] 为 0 或 1
//grid[0][0] == grid[m - 1][n - 1] == 0
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/minimum-obstacle-removal-to-reach-corner
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import com.liyongquan.util.RightBinarySearch;

import java.util.LinkedList;
import java.util.Queue;

public class MinimumObstacles {
    public static final int[][] DIRS = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1}
    };

    /**
     * 这个逻辑是否相当于佛洛依德算法?
     *
     * @param grid
     * @return
     */
    public int minimumObstacles(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int[][] distance = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                distance[i][j] = -1;
            }
        }
        distance[0][0] = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});
        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            for (int[] dir : DIRS) {
                int x = node[0] + dir[0];
                int y = node[1] + dir[1];
                if (x >= 0 && x < row && y >= 0 && y < col) {
                    int dis = distance[node[0]][node[1]] + (grid[x][y] == 0 ? 0 : 1);
                    if (distance[x][y] < 0 || dis < distance[x][y]) {
                        distance[x][y] = dis;
                        queue.add(new int[]{x, y});
                    }
                }
            }
        }
        return distance[row - 1][col - 1];
    }

    //TODO:Dijkstra算法
}
