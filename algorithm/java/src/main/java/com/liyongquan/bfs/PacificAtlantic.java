package com.liyongquan.bfs;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

//给定一个 m x n 的非负整数矩阵来表示一片大陆上各个单元格的高度。“太平洋”处于大陆的左边界和上边界，而“大西洋”处于大陆的右边界和下边界。
//
//规定水流只能按照上、下、左、右四个方向流动，且只能从高到低或者在同等高度上流动。
//
//请找出那些水流既可以流动到“太平洋”，又能流动到“大西洋”的陆地单元的坐标。
//
// 
//
//提示：
//
//输出坐标的顺序不重要
//m 和 n 都小于150
// 
//
//示例：
//
// 
//
//给定下面的 5x5 矩阵:
//
//  太平洋 ~   ~   ~   ~   ~
//       ~  1   2   2   3  (5) *
//       ~  3   2   3  (4) (4) *
//       ~  2   4  (5)  3   1  *
//       ~ (6) (7)  1   4   5  *
//       ~ (5)  1   1   2   4  *
//          *   *   *   *   * 大西洋
//
//返回:
//
//[[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (上图中带括号的单元).
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/pacific-atlantic-water-flow
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

public class PacificAtlantic {
    public static final int[][] DIRS = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1},
    };

    /**
     * 先用最简单的bfs，多源的BFS
     * <p>
     * 果然是性能击败5%
     *
     * @param heights
     * @return
     */
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int row = heights.length, col = heights[0].length;
        List<List<Integer>> res = new LinkedList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (bfs(heights, row, col, new int[]{i, j})) {
                    res.add(Arrays.asList(i, j));
                }
            }
        }
        return res;
    }

    private boolean bfs(int[][] heights, int row, int col, int[] pos) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(pos);
        boolean[][] visit = new boolean[row][col];
        visit[pos[0]][pos[1]] = true;
        //太平洋、大西洋
        boolean f1 = false, f2 = false;
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            for (int[] dir : DIRS) {
                int x = poll[0] + dir[0];
                int y = poll[1] + dir[1];
                if (x < 0 || y < 0) {
                    f1 = true;
                }
                if (x >= row || y >= col) {
                    f2 = true;
                }
                if (f1 && f2) {
                    return true;
                }
                if (x >= 0 && x < row && y >= 0 && y < col && !visit[x][y] && heights[x][y] <= heights[poll[0]][poll[1]]) {
                    visit[x][y] = true;
                    queue.add(new int[]{x, y});
                }
            }
        }
        return false;
    }

    /**
     * 同样是BFS
     * https://leetcode-cn.com/problems/pacific-atlantic-water-flow/solution/shui-wang-gao-chu-liu-by-xiaohu9527-xxsx/
     *
     * @param heights
     * @return
     */
    public List<List<Integer>> pacificAtlantic2(int[][] heights) {
        int row = heights.length, col = heights[0].length;
        Set<Integer> s1 = bfs2(heights, row, col, () -> {
            List<int[]> list = new LinkedList<>();
            for (int i = 0; i < row; i++) {
                list.add(new int[]{i, 0});
            }
            for (int i = 0; i < col; i++) {
                list.add(new int[]{0, i});
            }
            return list;
        });
        Set<Integer> s2 = bfs2(heights, row, col, () -> {
            List<int[]> list = new LinkedList<>();
            for (int i = 0; i < row; i++) {
                list.add(new int[]{i, col - 1});
            }
            for (int i = 0; i < col; i++) {
                list.add(new int[]{row - 1, i});
            }
            return list;
        });
        //取交集
        List<List<Integer>> res = new LinkedList<>();
        for (Integer p : s1) {
            if (s2.contains(p)) {
                res.add(Arrays.asList(p / col, p % col));
            }
        }
        return res;
    }

    private Set<Integer> bfs2(int[][] heights, int row, int col, Supplier<List<int[]>> initFun) {
        Queue<int[]> queue = new LinkedList<>();
        List<int[]> list = initFun.get();
        boolean[][] visit = new boolean[row][col];
        for (int[] pos : list) {
            queue.add(pos);
            visit[pos[0]][pos[1]] = true;
        }
        Set<Integer> res = new HashSet<>();
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            res.add(poll[0] * col + poll[1]);
            for (int[] dir : DIRS) {
                int x = poll[0] + dir[0];
                int y = poll[1] + dir[1];
                if (x >= 0 && x < row && y >= 0 && y < col && !visit[x][y] && heights[x][y] >= heights[poll[0]][poll[1]]) {
                    visit[x][y] = true;
                    queue.add(new int[]{x, y});
                }
            }
        }
        return res;
    }
}
