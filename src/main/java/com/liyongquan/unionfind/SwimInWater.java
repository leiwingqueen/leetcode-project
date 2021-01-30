package com.liyongquan.unionfind;

import com.liyongquan.dfs.MinimumEffortPath;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * 在一个 N x N 的坐标方格 grid 中，每一个方格的值 grid[i][j] 表示在位置 (i,j) 的平台高度。
 * <p>
 * 现在开始下雨了。当时间为 t 时，此时雨水导致水池中任意位置的水位为 t 。你可以从一个平台游向四周相邻的任意一个平台，但是前提是此时水位必须同时淹没这两个平台。假定你可以瞬间移动无限距离，也就是默认在方格内部游动是不耗时的。当然，在你游泳的时候你必须待在坐标方格里面。
 * <p>
 * 你从坐标方格的左上平台 (0，0) 出发。最少耗时多久你才能到达坐标方格的右下平台 (N-1, N-1)？
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: [[0,2],[1,3]]
 * 输出: 3
 * 解释:
 * 时间为0时，你位于坐标方格的位置为 (0, 0)。
 * 此时你不能游向任意方向，因为四个相邻方向平台的高度都大于当前时间为 0 时的水位。
 * <p>
 * 等时间到达 3 时，你才可以游向平台 (1, 1). 因为此时的水位是 3，坐标方格中的平台没有比水位 3 更高的，所以你可以游向坐标方格中的任意位置
 * 示例2:
 * <p>
 * 输入: [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
 * 输出: 16
 * 解释:
 * 0  1  2  3  4
 * 24 23 22 21  5
 * 12 13 14 15 16
 * 11 17 18 19 20
 * 10  9  8  7  6
 * <p>
 * 最终的路线用加粗进行了标记。
 * 我们必须等到时间为 16，此时才能保证平台 (0, 0) 和 (4, 4) 是连通的
 *  
 * <p>
 * 提示:
 * <p>
 * 2 <= N <= 50.
 * grid[i][j] 是 [0, ..., N*N - 1] 的排列。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/swim-in-rising-water
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SwimInWater {
    /**
     * 跟 最小体力消耗路径 解法类似。
     * <p>
     * 先把格子之间建立边(边的权值为两个格子的最大高度)，把边进行排序，逐步添加边，左上和右下构成一个连通图
     *
     * @param grid
     * @return
     */
    public int swimInWater(int[][] grid) {
        int row = grid.length, col = grid[0].length;
        //构造无向图(为了减少重复边的数量，我们只需要考虑右下两个方向即可)
        List<Edge> edges = new LinkedList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                //右
                if (j + 1 < col) {
                    int weight = Math.max(grid[i][j], grid[i][j + 1]);
                    edges.add(new Edge(toIdx(i, j, col), toIdx(i, j + 1, col), weight));
                }
                //下
                if (i + 1 < row) {
                    int weight = Math.max(grid[i][j], grid[i + 1][j]);
                    edges.add(new Edge(toIdx(i, j, col), toIdx(i + 1, j, col), weight));
                }
            }
        }
        Collections.sort(edges, Comparator.comparingInt(o -> o.weight));
        //一直添加边，直到左上和右下变成联通
        UnionFind uf = new UnionFind(row * col);
        for (Edge edge : edges) {
            uf.union(edge.x, edge.y);
            int root1 = uf.find(0);
            int root2 = uf.find(row * col - 1);
            if (root1 == root2) {
                return edge.weight;
            }
        }
        //实际上不会跑到这个逻辑
        return -1;
    }

    /**
     * 二维数组转一维
     *
     * @param x
     * @param y
     * @param col
     * @return
     */
    private static int toIdx(int x, int y, int col) {
        return x * col + y;
    }

    private static class Edge {
        int x;
        int y;
        int weight;

        public Edge(int x, int y, int weight) {
            this.x = x;
            this.y = y;
            this.weight = weight;
        }
    }

    /**
     * 并查集模板
     */
    private static class UnionFind {
        private int[] parent;
        private int count;

        public UnionFind(int size) {
            this.parent = new int[size];
            //初始化，每个节点的父节点就是自己
            for (int i = 0; i < size; i++) {
                parent[i] = i;
                count++;
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
