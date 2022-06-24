package com.liyongquan.graph;

//1001. 网格照明
//在大小为 n x n 的网格 grid 上，每个单元格都有一盏灯，最初灯都处于 关闭 状态。
//
//给你一个由灯的位置组成的二维数组 lamps ，其中 lamps[i] = [rowi, coli] 表示 打开 位于 grid[rowi][coli] 的灯。即便同一盏灯可能在 lamps 中多次列出，不会影响这盏灯处于 打开 状态。
//
//当一盏灯处于打开状态，它将会照亮 自身所在单元格 以及同一 行 、同一 列 和两条 对角线 上的 所有其他单元格 。
//
//另给你一个二维数组 queries ，其中 queries[j] = [rowj, colj] 。对于第 j 个查询，如果单元格 [rowj, colj] 是被照亮的，则查询结果为 1 ，否则为 0 。在第 j 次查询之后 [按照查询的顺序] ，关闭 位于单元格 grid[rowj][colj] 上及相邻 8 个方向上（与单元格 grid[rowi][coli] 共享角或边）的任何灯。
//
//返回一个整数数组 ans 作为答案， ans[j] 应等于第 j 次查询 queries[j] 的结果，1 表示照亮，0 表示未照亮。
//
// 
//
//示例 1：
//
//
//输入：n = 5, lamps = [[0,0],[4,4]], queries = [[1,1],[1,0]]
//输出：[1,0]
//解释：最初所有灯都是关闭的。在执行查询之前，打开位于 [0, 0] 和 [4, 4] 的灯。第 0 次查询检查 grid[1][1] 是否被照亮（蓝色方框）。该单元格被照亮，所以 ans[0] = 1 。然后，关闭红色方框中的所有灯。
//
//第 1 次查询检查 grid[1][0] 是否被照亮（蓝色方框）。该单元格没有被照亮，所以 ans[1] = 0 。然后，关闭红色矩形中的所有灯。
//
//示例 2：
//
//输入：n = 5, lamps = [[0,0],[4,4]], queries = [[1,1],[1,1]]
//输出：[1,1]
//示例 3：
//
//输入：n = 5, lamps = [[0,0],[0,4]], queries = [[0,4],[0,1],[1,4]]
//输出：[1,1,0]
// 
//
//提示：
//
//1 <= n <= 109
//0 <= lamps.length <= 20000
//0 <= queries.length <= 20000
//lamps[i].length == 2
//0 <= rowi, coli < n
//queries[j].length == 2
//0 <= rowj, colj < n
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/grid-illumination
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import java.util.*;

public class GridIllumination {
    public static final int[][] DIRS = {
            {0, -1},
            {0, 0},
            {0, 1},
            {-1, -1},
            {-1, 0},
            {-1, 1},
            {1, -1},
            {1, 0},
            {1, 1},
    };

    /**
     * 这个算法有点类似游戏里面的2D的光照系统的设计
     *
     * @param n
     * @param lamps
     * @param queries
     * @return
     */
    public int[] gridIllumination(int n, int[][] lamps, int[][] queries) {
        int[] res = new int[queries.length];
        Set<Position> lampSet = new HashSet<>();
        for (int[] lamp : lamps) {
            lampSet.add(new Position(lamp));
        }
        //生成光源的图。为每个光源的x轴,y轴，对角线分别计算光源数量
        Map<Integer, Integer> xLine = new HashMap<>(),
                yLine = new HashMap<>(),
                diagonal1 = new HashMap<>(),
                diagonal2 = new HashMap<>();
        for (Position pos : lampSet) {
            int x = pos.pos[0], y = pos.pos[1];
            xLine.put(x, xLine.getOrDefault(x, 0) + 1);
            yLine.put(y, yLine.getOrDefault(y, 0) + 1);
            diagonal1.put(x - y, diagonal1.getOrDefault(x - y, 0) + 1);
            diagonal2.put(x + y, diagonal2.getOrDefault(x + y, 0) + 1);
        }
        //遍历每个查询
        for (int i = 0; i < queries.length; i++) {
            int x = queries[i][0], y = queries[i][1];
            if (xLine.containsKey(x) || yLine.containsKey(y) || diagonal1.containsKey(x - y) || diagonal2.containsKey(x + y)) {
                res[i] = 1;
            }
            //更新光源
            for (int[] dir : DIRS) {
                int nx = x + dir[0], ny = y + dir[1];
                if (nx >= 0 && nx < n && ny >= 0 && ny < n && lampSet.contains(new Position(nx, ny))) {
                    lampSet.remove(new Position(nx, ny));
                    if (xLine.get(nx) == 1) {
                        xLine.remove(nx);
                    } else {
                        xLine.put(nx, xLine.get(nx) - 1);
                    }
                    if (yLine.get(ny) == 1) {
                        yLine.remove(ny);
                    } else {
                        yLine.put(ny, yLine.get(ny) - 1);
                    }
                    if (diagonal1.get(nx - ny) == 1) {
                        diagonal1.remove(nx - ny);
                    } else {
                        diagonal1.put(nx - ny, diagonal1.get(nx - ny) - 1);
                    }
                    if (diagonal2.get(nx + ny) == 1) {
                        diagonal2.remove(nx + ny);
                    } else {
                        diagonal2.put(nx + ny, diagonal2.get(nx + ny) - 1);
                    }
                }
            }
        }
        return res;
    }

    private static class Position {
        int[] pos;

        public Position(int[] pos) {
            this.pos = pos;
        }

        public Position(int x, int y) {
            this.pos = new int[]{x, y};
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position position = (Position) o;
            return Arrays.equals(pos, position.pos);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(pos);
        }
    }
}
