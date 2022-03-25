package com.liyongquan.design;

import java.util.*;

/**
 * 简单的消消乐算法
 */
public class Xiaoxiaole2 {
    //只需要两个方向遍历即可
    public static final int[][] DIRS = {
            //{0, -1},
            {0, 1},
            //{-1, 0},
            {1, 0}
    };

    /**
     * grid表示消消乐的图，其中0表示为空，>0表示对应的道具。k表示至少相连k个才能消除
     *
     * @param grid
     * @param k
     */
    public void scan(int[][] grid, int k) {
        //计算每个连通分量的大小
        int m = grid.length;
        int n = grid[0].length;
        UnionFind uf = new UnionFind(grid);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }
                for (int[] dir : DIRS) {
                    int x = i + dir[0];
                    int y = j + dir[1];
                    if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == grid[i][j]) {
                        //合并两个连通分量
                        uf.union(i * n + j, x * n + y);
                    }
                }
            }
        }
        //扫描一遍，连通分量的大小>=k进行删除
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (uf.getCount(i * n + j) >= k) {
                    grid[i][j] = 0;
                }
            }
        }
    }

    private static class UnionFind {
        private int[] parent;
        //每个连通块的大小
        private Map<Integer, Integer> cnt;

        public UnionFind(int[][] grid) {
            int row = grid.length, col = grid[0].length;
            //二维转换成一维
            this.parent = new int[row * col];
            cnt = new HashMap<>();
            //初始化，每个节点的父节点就是自己
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (grid[i][j] != 0) {
                        int idx = i * col + j;
                        parent[idx] = idx;
                        cnt.put(idx, 1);
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
                //更新合并后的连通块大小
                cnt.put(rootY, cnt.get(rootX) + cnt.get(rootY));
                cnt.remove(rootX);
            }
        }

        /**
         * 获取连通块的大小
         *
         * @param x
         * @return
         */
        public int getCount(int x) {
            int parent = find(x);
            return cnt.get(parent);
        }
    }
}
