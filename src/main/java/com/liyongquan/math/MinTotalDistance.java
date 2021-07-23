package com.liyongquan.math;

//有一队人（两人或以上）想要在一个地方碰面，他们希望能够最小化他们的总行走距离。
//
// 给你一个 2D 网格，其中各个格子内的值要么是 0，要么是 1。
//
// 1 表示某个人的家所处的位置。这里，我们将使用 曼哈顿距离 来计算，其中 distance(p1, p2) = |p2.x - p1.x| + |p2.y
// - p1.y|。
//
// 示例：
//
// 输入:
//
//1 - 0 - 0 - 0 - 1
//|   |   |   |   |
//0 - 0 - 0 - 0 - 0
//|   |   |   |   |
//0 - 0 - 1 - 0 - 0
//
//输出: 6
//
//解析: 给定的三个人分别住在(0,0)，(0,4) 和 (2,2):
//     (0,2) 是一个最佳的碰面点，其总行走距离为 2 + 2 + 2 = 6，最小，因此返回 6。
// Related Topics 数组 数学 矩阵 排序
// 👍 62 👎 0

import java.util.LinkedList;
import java.util.List;

/**
 * @author liyongquan
 * @date 2021/7/23
 */
public class MinTotalDistance {
    /**
     * 这也能过，就这？
     * @param grid
     * @return
     */
    public int minTotalDistance(int[][] grid) {
        int row = grid.length, col = grid[0].length;
        List<int[]> list = new LinkedList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    list.add(new int[]{i, j});
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int dis = sumDistance(new int[]{i, j}, list);
                min = Math.min(dis, min);
            }
        }
        return min;
    }

    private int sumDistance(int[] pos, List<int[]> list) {
        int sum = 0;
        for (int[] p : list) {
            sum += Math.abs(pos[0] - p[0]) + Math.abs(pos[1] - p[1]);
        }
        return sum;
    }
}
