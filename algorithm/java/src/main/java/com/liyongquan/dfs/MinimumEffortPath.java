package com.liyongquan.dfs;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * 1631. 最小体力消耗路径
 * <p>
 * <p>
 * 你准备参加一场远足活动。给你一个二维 rows x columns 的地图 heights ，其中 heights[row][col] 表示格子 (row, col) 的高度。一开始你在最左上角的格子 (0, 0) ，且你希望去最右下角的格子 (rows-1, columns-1) （注意下标从 0 开始编号）。你每次可以往 上，下，左，右 四个方向之一移动，你想要找到耗费 体力 最小的一条路径。
 * <p>
 * 一条路径耗费的 体力值 是路径上相邻格子之间 高度差绝对值 的 最大值 决定的。
 * <p>
 * 请你返回从左上角走到右下角的最小 体力消耗值 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：heights = [[1,2,2],[3,8,2],[5,3,5]]
 * 输出：2
 * 解释：路径 [1,3,5,3,5] 连续格子的差值绝对值最大为 2 。
 * 这条路径比路径 [1,2,2,2,5] 更优，因为另一条路径差值最大值为 3 。
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：heights = [[1,2,3],[3,8,4],[5,3,5]]
 * 输出：1
 * 解释：路径 [1,2,3,4,5] 的相邻格子差值绝对值最大为 1 ，比路径 [1,3,5,3,5] 更优。
 * 示例 3：
 * <p>
 * <p>
 * 输入：heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
 * 输出：0
 * 解释：上图所示路径不需要消耗任何体力。
 *  
 * <p>
 * 提示：
 * <p>
 * rows == heights.length
 * columns == heights[i].length
 * 1 <= rows, columns <= 100
 * 1 <= heights[i][j] <= 106
 * 通过次数7,651提交次数17,984
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/path-with-minimum-effort
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
@Slf4j
public class MinimumEffortPath {
    public static final int[][] DIR = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1},
    };

    /**
     * dfs解法，维护一个visit已经访问的标记，先不加任何剪枝
     * <p>
     * 增加记忆，减少重复路径的访问
     * <p>
     * 还是超出时间限制>_<
     *
     * @param heights
     * @return
     */
    public int minimumEffortPath(int[][] heights) {
        int row = heights.length, col = heights[0].length;
        int[][] visit = new int[row][col];
        visit[0][0] = 1;
        Map<int[], Integer> cache = new HashMap<>();
        return dfs(heights, 0, 0, row, col, visit, 0, cache);
    }

    private int dfs(int[][] heights, int x, int y, int row, int col, int[][] visit, int max, Map<int[], Integer> cache) {
        //log.info("访问节点.[{},{}]", x, y);
        if (x == row - 1 && y == col - 1) {
            return max;
        }
        //遍历上下左右4个方向
        int min = Integer.MAX_VALUE;
        for (int[] dir : DIR) {
            int nx = x + dir[0];
            int ny = y + dir[1];
            if (nx >= 0 && nx < row && ny >= 0 && ny < col && visit[nx][ny] == 0) {
                int nMax = Math.max(max, Math.abs(heights[nx][ny] - heights[x][y]));
                visit[nx][ny] = 1;
                int res;
                int[] key = {nx, ny};
                //增加记忆，减少重复的路径搜索
                if (cache.containsKey(key)) {
                    res = cache.get(key);
                } else {
                    res = dfs(heights, nx, ny, row, col, visit, nMax, cache);
                    cache.put(key, res);
                }
                min = Math.min(res, min);
                //回溯
                visit[nx][ny] = 0;
            }
        }
        return min;
    }

    //**********************************并查集算法***************************

    /**
     * Consider the grid as a graph, where adjacent cells have an edge with cost of the difference between the cells.
     * <p>
     * If you are given threshold k, check if it is possible to go from (0, 0) to (n-1, m-1) using only edges of ≤ k cost.
     * <p>
     * 按照提示做二分查找。庆幸的是1 <= heights[i][j] <= 106，log(106)=6.72792，大概7次就能得到结果
     * <p>
     * 假设边的数量为n，max(height)=h那么扫描一次大概需要O(nlog(n))的时间效率。总的时间复杂度大概为O(nlog(n)log(h))
     */
    public int minimumEffortPath2(int[][] heights) {
        int row = heights.length, col = heights[0].length;
        //构造无向图(为了减少重复边的数量，我们只需要考虑右下两个方向即可)
        List<Edge> edges = new LinkedList<>();
        int threshold = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                //右
                if (j + 1 < col) {
                    int weight = Math.abs(heights[i][j] - heights[i][j + 1]);
                    edges.add(new Edge(toIdx(i, j, col), toIdx(i, j + 1, col), weight));
                    threshold = Math.max(weight, threshold);
                }
                //下
                if (i + 1 < row) {
                    int weight = Math.abs(heights[i][j] - heights[i + 1][j]);
                    edges.add(new Edge(toIdx(i, j, col), toIdx(i + 1, j, col), weight));
                    threshold = Math.max(weight, threshold);
                }
            }
        }
        //二分查找+并查集
        int l = 0, r = threshold;
        while (l < r) {
            UnionFind uf = new UnionFind(row * col);
            int middle = (l + r) / 2;
            for (Edge edge : edges) {
                if (edge.weight <= middle) {
                    uf.union(edge.x, edge.y);
                }
            }
            int root1 = uf.find(0);
            int root2 = uf.find(row * col - 1);
            if (root1 == root2) {
                r = middle;
            } else {
                l = middle + 1;
            }
        }
        return l;
    }


    //**********************************并查集算法2，优化***************************

    /**
     * 先排序，然后逐步添加
     *
     * @param heights
     * @return
     */
    public int minimumEffortPath3(int[][] heights) {
        int row = heights.length, col = heights[0].length;
        //构造无向图(为了减少重复边的数量，我们只需要考虑右下两个方向即可)
        List<Edge> edges = new LinkedList<>();
        int threshold = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                //右
                if (j + 1 < col) {
                    int weight = Math.abs(heights[i][j] - heights[i][j + 1]);
                    edges.add(new Edge(toIdx(i, j, col), toIdx(i, j + 1, col), weight));
                    threshold = Math.max(weight, threshold);
                }
                //下
                if (i + 1 < row) {
                    int weight = Math.abs(heights[i][j] - heights[i + 1][j]);
                    edges.add(new Edge(toIdx(i, j, col), toIdx(i + 1, j, col), weight));
                    threshold = Math.max(weight, threshold);
                }
            }
        }
        Collections.sort(edges, Comparator.comparingInt(o -> o.weight));
        //一直添加边，直到左上和右下变成联通
        int res = 0;
        UnionFind uf = new UnionFind(row * col);
        for (Edge edge : edges) {
            uf.union(edge.x, edge.y);
            int root1 = uf.find(0);
            int root2 = uf.find(row * col - 1);
            res = Math.max(res, edge.weight);
            if (root1 == root2) {
                return res;
            }
        }
        return res;
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

    //TODO:Dijkstra算法
}
