package com.liyongquan.unionfind;

import lombok.extern.slf4j.Slf4j;

/**
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * <p>
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * <p>
 * 此外，你可以假设该网格的四条边均被水包围。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：grid = [
 * ["1","1","1","1","0"],
 * ["1","1","0","1","0"],
 * ["1","1","0","0","0"],
 * ["0","0","0","0","0"]
 * ]
 * 输出：1
 * 示例 2：
 * <p>
 * 输入：grid = [
 * ["1","1","0","0","0"],
 * ["1","1","0","0","0"],
 * ["0","0","1","0","0"],
 * ["0","0","0","1","1"]
 * ]
 * 输出：3
 *  
 * <p>
 * 提示：
 * <p>
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * grid[i][j] 的值为 '0' 或 '1'
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-islands
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
@Slf4j
public class NumIslands {
    private static final int[][] DIR = new int[][]{
            {1, 0},
            {-1, 0},
            {0, -1},
            {0, 1}
    };

    public int numIslands(char[][] grid) {
        int row = grid.length;
        if (row == 0) {
            return 0;
        }
        int col = grid[0].length;
        UnionFind uf = new UnionFind(grid);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    //直接设置为0，避免重复合并
                    grid[i][j] = 0;
                    //遍历四周围，如果是陆地则进行合并
                    for (int[] dir : DIR) {
                        int x = i + dir[0], y = j + dir[1];
                        if (x >= 0 && x < row && y >= 0 && y < col && grid[x][y] == '1') {
                            //log.info("合并节点:[{},{}],[{},{}]", i, j, x, y);
                            uf.union(i * col + j, x * col + y);
                        }
                    }
                }
            }
        }
        return uf.getCount();
    }

    private static class UnionFind {
        private int[] parent;
        private int count;

        public UnionFind(char[][] grid) {
            int row = grid.length, col = grid[0].length;
            //二维转换成一维
            this.parent = new int[row * col];
            //初始化，每个节点的父节点就是自己
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (grid[i][j] == '1') {
                        parent[i * col + j] = i * col + j;
                        count++;
                    }
                }
            }
        }

        public int find(int x) {
            /*if (parent[x] == x) {
                return x;
            } else {
                return find(parent[x]);
            }*/
            while (parent[x] != x) {
                //路径压缩
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return parent[x];
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                parent[rootX] = rootY;
                count--;
            }
        }

        public int getCount() {
            return count;
        }
    }
}
