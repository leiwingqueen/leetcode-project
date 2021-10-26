package com.liyongquan.bfs;

//317. 离建筑物最近的距离
//你是个房地产开发商，想要选择一片空地 建一栋大楼。你想把这栋大楼够造在一个距离周边设施都比较方便的地方，通过调研，你希望从它出发能在 最短的距离和 内抵达周边全部的建筑物。请你计算出这个最佳的选址到周边全部建筑物的 最短距离和。
//
// 
//
//提示：
//
//你只能通过向上、下、左、右四个方向上移动。
//
//给你一个由 0、1 和 2 组成的二维网格，其中：
//
//0 代表你可以自由通过和选择建造的空地
//1 代表你无法通行的建筑物
//2 代表你无法通行的障碍物
// 
//
//示例：
//
//输入：[[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]
//
//1 - 0 - 2 - 0 - 1
//|   |   |   |   |
//0 - 0 - 0 - 0 - 0
//|   |   |   |   |
//0 - 0 - 1 - 0 - 0
//输出：7
//解析：
//给定三个建筑物 (0,0)、(0,4) 和 (2,2) 以及一个位于 (0,2) 的障碍物。
//由于总距离之和 3+3+1=7 最优，所以位置 (1,2) 是符合要求的最优地点，故返回7。
// 
//
//注意：
//
//题目数据保证至少存在一栋建筑物，如果无法按照上述规则返回建房地点，则请你返回 -1。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/shortest-distance-from-all-buildings
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author liyongquan
 * @date 2021/10/26
 */
public class ShortestDistance {
    public static final int[][] DIRS = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1},
    };

    /**
     * 暴力+bfs
     * <p>
     * 性能击败5%
     *
     * @param grid
     * @return
     */
    public int shortestDistance(int[][] grid) {
        int row = grid.length, col = grid[0].length;
        //计算有多少个建筑
        int cnt = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    cnt++;
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 0) {
                    int r = bfs(grid, row, col, new int[]{i, j}, cnt);
                    if (r >= 0 && r < min) {
                        min = r;
                    }
                }
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    /**
     * 计算距离总和
     *
     * @param grid
     * @param start
     * @return
     */
    private int bfs(int[][] grid, int row, int col, int[] start, int cnt) {
        int sum = 0;
        Queue<int[]> queue = new LinkedList<>();
        Queue<Integer> depth = new LinkedList<>();
        queue.add(start);
        depth.add(0);
        boolean[][] visit = new boolean[row][col];
        visit[start[0]][start[1]] = true;
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            Integer dep = depth.poll();
            for (int[] dir : DIRS) {
                int x = poll[0] + dir[0];
                int y = poll[1] + dir[1];
                if (x >= 0 && x < row && y >= 0 && y < col && !visit[x][y] && grid[x][y] != 2) {
                    visit[x][y] = true;
                    if (grid[x][y] == 1) {
                        sum += dep + 1;
                        cnt--;
                    } else {
                        queue.add(new int[]{x, y});
                        depth.add(dep + 1);
                    }
                }
            }
        }
        return cnt > 0 ? -1 : sum;
    }

    //距离各个建筑物的距离
    private int[][] distance;
    //连接的建筑物
    private int[][] reachCnt;

    //假设建筑物多，空地少,那么是不是可以反过来计算?
    public int shortestDistanc2(int[][] grid) {
        int row = grid.length, col = grid[0].length;
        //计算有多少个建筑
        int cnt = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    cnt++;
                }
            }
        }
        distance = new int[row][col];
        reachCnt = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    bfs2(grid, row, col, new int[]{i, j});
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (reachCnt[i][j] == cnt) {
                    min = Math.min(distance[i][j], min);
                }
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    private void bfs2(int[][] grid, int row, int col, int[] start) {
        Queue<int[]> queue = new LinkedList<>();
        Queue<Integer> depth = new LinkedList<>();
        queue.add(start);
        depth.add(0);
        boolean[][] visit = new boolean[row][col];
        visit[start[0]][start[1]] = true;
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            Integer dep = depth.poll();
            for (int[] dir : DIRS) {
                int x = poll[0] + dir[0];
                int y = poll[1] + dir[1];
                if (x >= 0 && x < row && y >= 0 && y < col && !visit[x][y] && grid[x][y] == 0) {
                    visit[x][y] = true;
                    distance[x][y] += dep + 1;
                    reachCnt[x][y]++;
                    queue.add(new int[]{x, y});
                    depth.add(dep + 1);
                }
            }
        }
    }
}
