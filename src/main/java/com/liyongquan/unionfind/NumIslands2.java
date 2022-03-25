package com.liyongquan.unionfind;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.CheckedOutputStream;

//假设你设计一个游戏，用一个 m 行 n 列的 2D 网格来存储你的游戏地图。
//
// 起始的时候，每个格子的地形都被默认标记为「水」。我们可以通过使用 addLand 进行操作，将位置 (row, col) 的「水」变成「陆地」。
//
// 你将会被给定一个列表，来记录所有需要被操作的位置，然后你需要返回计算出来 每次 addLand 操作后岛屿的数量。
//
// 注意：一个岛的定义是被「水」包围的「陆地」，通过水平方向或者垂直方向上相邻的陆地连接而成。你可以假设地图网格的四边均被无边无际的「水」所包围。
//
// 请仔细阅读下方示例与解析，更加深入了解岛屿的判定。
//
// 示例:
//
// 输入: m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]]
//输出: [1,1,2,3]
//
//
// 解析:
//
// 起初，二维网格 grid 被全部注入「水」。（0 代表「水」，1 代表「陆地」）
//
// 0 0 0
//0 0 0
//0 0 0
//
//
// 操作 #1：addLand(0, 0) 将 grid[0][0] 的水变为陆地。
//
// 1 0 0
//0 0 0   Number of islands = 1
//0 0 0
//
//
// 操作 #2：addLand(0, 1) 将 grid[0][1] 的水变为陆地。
//
// 1 1 0
//0 0 0   岛屿的数量为 1
//0 0 0
//
//
// 操作 #3：addLand(1, 2) 将 grid[1][2] 的水变为陆地。
//¬
// 1 1 0
//0 0 1   岛屿的数量为 2
//0 0 0
//
//
// 操作 #4：addLand(2, 1) 将 grid[2][1] 的水变为陆地。
//
// 1 1 0
//0 0 1   岛屿的数量为 3
//0 1 0
//
//
// 拓展：
//
// 你是否能在 O(k log mn) 的时间复杂度程度内完成每次的计算？（k 表示 positions 的长度）
// Related Topics 并查集
// 👍 74 👎 0

public class NumIslands2 {
    private static final int[][] DIR = new int[][]{
            {1, 0},
            {-1, 0},
            {0, -1},
            {0, 1},
    };

    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        int[][] grid = new int[m][n];
        UnionFind uf = new UnionFind(grid);
        List<Integer> res = new ArrayList<>(positions.length);
        for (int[] position : positions) {
            int x = position[0], y = position[1];
            if (grid[x][y] == 0) {
                grid[x][y] = 1;
                uf.count++;
                uf.parent[x * n + y] = x * n + y;
                //遍历四周围，如果是陆地则进行合并
                for (int[] dir : DIR) {
                    int nx = x + dir[0], ny = y + dir[1];
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] == 1) {
                        //log.info("合并节点:[{},{}],[{},{}]", i, j, x, y);
                        uf.union(nx * n + ny, x * n + y);
                    }
                }
            }
            res.add(uf.getCount());
        }
        return res;
    }

    private static class UnionFind {
        private int[] parent;
        private int count;

        public UnionFind(int[][] grid) {
            int row = grid.length, col = grid[0].length;
            //二维转换成一维
            this.parent = new int[row * col];
            //初始化，每个节点的父节点就是自己
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (grid[i][j] == 1) {
                        parent[i * col + j] = i * col + j;
                        count++;
                    }
                }
            }
        }

        public int find(int x) {
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
