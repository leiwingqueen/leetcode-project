package com.liyongquan.weeklycontest.ubiquant2022;

public class LakeCount {
    public static final int[][] DIRS = {
            {-1, -1},
            {-1, 0},
            {-1, 1},
            {0, -1},
            {0, 1},
            {1, -1},
            {1, 0},
            {1, 1},
    };

    public int lakeCount(String[] field) {
        UnionFind uf = new UnionFind(field);
        int m = field.length;
        int n = field[0].length();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (field[i].charAt(j) == 'W') {
                    for (int[] dir : DIRS) {
                        int x = i + dir[0];
                        int y = j + dir[1];
                        if (x >= 0 && x < m && y >= 0 && y < n && field[x].charAt(y) == 'W') {
                            uf.union(i * n + j, x * n + y);
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

        public UnionFind(String[] grid) {
            int row = grid.length, col = grid[0].length();
            //二维转换成一维
            this.parent = new int[row * col];
            //初始化，每个节点的父节点就是自己
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (grid[i].charAt(j) == 'W') {
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
