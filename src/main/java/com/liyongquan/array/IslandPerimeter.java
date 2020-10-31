package com.liyongquan.array;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个包含 0 和 1 的二维网格地图，其中 1 表示陆地 0 表示水域。
 * <p>
 * 网格中的格子水平和垂直方向相连（对角线方向不相连）。整个网格被水完全包围，但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。
 * <p>
 * 岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100 。计算这个岛屿的周长。
 * <p>
 *  
 * <p>
 * 示例 :
 * <p>
 * 输入:
 * [[0,1,0,0],
 * [1,1,1,0],
 * [0,1,0,0],
 * [1,1,0,0]]
 * <p>
 * 输出: 16
 * <p>
 * 解释: 它的周长是下面图片中的 16 个黄色的边：
 * <p>
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/island-perimeter
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class IslandPerimeter {
    /**
     * 找规律，如果grid[i][j]==1，他的边=周围的海的数量(如果到达边界的也算一条边)
     *
     * @param grid
     * @return
     */
    public int islandPerimeter(int[][] grid) {
        if (grid.length == 0) {
            return 0;
        }
        int row = grid.length, col = grid[0].length;
        int result = 0;
        //上下左右
        int[][] dir = new int[][]{
                {-1, 0},
                {1, 0},
                {0, -1},
                {0, 1}
        };
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }
                //遍历4个方向
                for (int[] d : dir) {
                    int x = i + d[0];
                    int y = j + d[1];
                    if (x < 0 || y < 0 || x >= row || y >= col || grid[x][y] == 0) {
                        result++;
                    }
                }
            }
        }
        return result;
    }

    /**
     * 由于只有一个岛屿，使用bfs减少遍历的数次
     *
     * @param grid
     * @return
     */
    public int islandPerimeter2(int[][] grid) {
        if (grid.length == 0) {
            return 0;
        }
        int row = grid.length, col = grid[0].length;
        //找到第一个陆地
        int[] first = findFirst(grid, row, col);
        if (first.length == 0) {
            return 0;
        }
        int result = 0;
        //上下左右
        int[][] dir = new int[][]{
                {-1, 0},
                {1, 0},
                {0, -1},
                {0, 1}
        };
        Queue<int[]> queue = new LinkedList<>();
        queue.add(first);
        //标记已经访问
        grid[first[0]][first[1]] = 2;
        while (queue.size() > 0) {
            int[] poll = queue.poll();
            for (int[] d : dir) {
                int x = poll[0] + d[0];
                int y = poll[1] + d[1];
                if (x < 0 || y < 0 || x >= row || y >= col || grid[x][y] == 0) {
                    result++;
                } else if (grid[x][y] == 1) {
                    queue.add(new int[]{x, y});
                    grid[x][y] = 2;
                }
            }
        }
        return result;
    }

    private int[] findFirst(int[][] grid, int row, int col) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{};
    }
}
