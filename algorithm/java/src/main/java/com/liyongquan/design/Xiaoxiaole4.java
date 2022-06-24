package com.liyongquan.design;

public class Xiaoxiaole4 {
    public static final int[][] DIRS = {
            {0, -1},
            {0, 1},
            {-1, 0},
            {1, 0}
    };

    private UnionFind uf;

    private int[][] matrix;

    private int m;
    private int n;

    /**
     * 构造并查集的数据结构的复杂度是O(n)，这里可以考虑构建图后保留这个数据结构，不需要每次重新构造
     *
     * @param matrix
     */
    public Xiaoxiaole4(int[][] matrix) {
        this.matrix = matrix;
        uf = new UnionFind(matrix);
        this.m = matrix.length;
        this.n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    continue;
                }
                for (int[] dir : DIRS) {
                    int x = i + dir[0];
                    int y = j + dir[1];
                    if (x >= 0 && x < m && y >= 0 && y < n && matrix[x][y] == 1) {
                        uf.union(i * n + j, x * n + y);
                    }
                }
            }
        }
    }

    /**
     * 判断从start到pos是否存在一条通路
     * <p>
     * 这里每次用户操作才进行判断
     *
     * @param start
     * @param pos
     * @return
     */
    public boolean canDomain(int[] start, int[] pos) {
        //m行,n列。前端的坐标是反的，这里可能要处理下
        int p = uf.find(start[0] * n + start[1]);
        //不能直接合并检查，因为合并后发现如果不是一个连通图就无法进行回滚，这里要通过周围的4个点进行判断
        for (int[] dir : DIRS) {
            int x = pos[0] + dir[0];
            int y = pos[1] + dir[1];
            if (x >= 0 && x < m && y >= 0 && y < n && matrix[x][y] == 1) {
                //相邻节点同一个连通块，直接返回成功
                if (uf.find(x * n + y) == p) {
                    return true;
                }
            }
        }
        //当前自身的点也算上
        if (uf.find(pos[0] * n + pos[1]) == p) {
            return true;
        }
        return false;
    }

    /**
     * 更新格子
     * <p>
     * 主要为了每次操作后同步更新地图，不需要每次重新构建并查集
     *
     * @param pos
     */
    public void doDomain(int[] pos) {
        this.matrix[pos[0]][pos[1]] = 1;
        for (int[] dir : DIRS) {
            int x = pos[0] + dir[0];
            int y = pos[1] + dir[1];
            if (x >= 0 && x < m && y >= 0 && y < n && matrix[x][y] == 1) {
                uf.union(pos[0] * n + pos[1], x * n + y);
            }
        }
    }

    private static class UnionFind {
        private int[] parent;

        public UnionFind(int[][] grid) {
            int row = grid.length, col = grid[0].length;
            //二维转换成一维
            this.parent = new int[row * col];
            //初始化，每个节点的父节点就是自己
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    //if (grid[i][j] != 0) {
                    int idx = i * col + j;
                    parent[idx] = idx;
                    //}
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
            }
        }
    }
}
