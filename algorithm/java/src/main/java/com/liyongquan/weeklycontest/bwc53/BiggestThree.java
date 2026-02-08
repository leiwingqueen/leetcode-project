package com.liyongquan.weeklycontest.bwc53;

import lombok.extern.slf4j.Slf4j;

import java.util.PriorityQueue;

/**
 * 给你一个 m x n 的整数矩阵 grid 。
 * <p>
 * 菱形和 指的是 grid 中一个正菱形 边界 上的元素之和。本题中的菱形必须为正方形旋转45度，且四个角都在一个格子当中。下图是四个可行的菱形，每个菱形和应该包含的格子都用了相应颜色标注在图中。
 * <p>
 * <p>
 * <p>
 * <p>
 * 注意，菱形可以是一个面积为 0 的区域，如上图中右下角的紫色菱形所示。
 * <p>
 * 请你按照 降序 返回 grid 中三个最大的 互不相同的菱形和 。如果不同的和少于三个，则将它们全部返回。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：grid = [[3,4,5,1,3],[3,3,4,2,3],[20,30,200,40,10],[1,5,5,4,1],[4,3,2,2,5]]
 * 输出：[228,216,211]
 * 解释：最大的三个菱形和如上图所示。
 * - 蓝色：20 + 3 + 200 + 5 = 228
 * - 红色：200 + 2 + 10 + 4 = 216
 * - 绿色：5 + 200 + 4 + 2 = 211
 * 示例 2：
 * <p>
 * <p>
 * 输入：grid = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[20,9,8]
 * 解释：最大的三个菱形和如上图所示。
 * - 蓝色：4 + 2 + 6 + 8 = 20
 * - 红色：9 （右下角红色的面积为 0 的菱形）
 * - 绿色：8 （下方中央面积为 0 的菱形）
 * 示例 3：
 * <p>
 * 输入：grid = [[7,7,7]]
 * 输出：[7]
 * 解释：所有三个可能的菱形和都相同，所以返回 [7] 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 100
 * 1 <= grid[i][j] <= 105
 */
@Slf4j
public class BiggestThree {
    /**
     * 暴力
     *
     * @param grid
     * @return
     */
    public int[] getBiggestThree(int[][] grid) {
        int row = grid.length, col = grid[0].length;
        PriorityQueue<Integer> pq = new PriorityQueue<>(4);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int len = Math.min(j + 1, col - j);
                len = Math.min(len, (row - i + 1) / 2);
                for (int k = 1; k <= len; k++) {
                    int sum = k == 1 ? grid[i][j] : grid[i][j] + grid[i + k - 1][j - k + 1] + grid[i + k - 1][j + k - 1] + grid[i + 2 * k - 2][j];
                    log.info("[{},{}],len:{},sum:{}", i, j, k, sum);
                    if (pq.isEmpty() || sum > pq.peek()) {
                        pq.add(sum);
                        if (pq.size() > 3) {
                            pq.poll();
                        }
                    }
                }
            }
        }
        int size = pq.size();
        int[] res = new int[size];
        for (int i = size - 1; i >= 0; i--) {
            res[i] = pq.poll();
        }
        return res;
    }
}
