package com.liyongquan.array;

import java.util.*;

/**
 * 给出 R 行 C 列的矩阵，其中的单元格的整数坐标为 (r, c)，满足 0 <= r < R 且 0 <= c < C。
 * <p>
 * 另外，我们在该矩阵中给出了一个坐标为 (r0, c0) 的单元格。
 * <p>
 * 返回矩阵中的所有单元格的坐标，并按到 (r0, c0) 的距离从最小到最大的顺序排，其中，两单元格(r1, c1) 和 (r2, c2) 之间的距离是曼哈顿距离，|r1 - r2| + |c1 - c2|。（你可以按任何满足此条件的顺序返回答案。）
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：R = 1, C = 2, r0 = 0, c0 = 0
 * 输出：[[0,0],[0,1]]
 * 解释：从 (r0, c0) 到其他单元格的距离为：[0,1]
 * 示例 2：
 * <p>
 * 输入：R = 2, C = 2, r0 = 0, c0 = 1
 * 输出：[[0,1],[0,0],[1,1],[1,0]]
 * 解释：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2]
 * [[0,1],[1,1],[0,0],[1,0]] 也会被视作正确答案。
 * 示例 3：
 * <p>
 * 输入：R = 2, C = 3, r0 = 1, c0 = 2
 * 输出：[[1,2],[0,2],[1,1],[0,1],[1,0],[0,0]]
 * 解释：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2,2,3]
 * 其他满足题目要求的答案也会被视为正确，例如 [[1,2],[1,1],[0,2],[1,0],[0,1],[0,0]]。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= R <= 100
 * 1 <= C <= 100
 * 0 <= r0 < R
 * 0 <= c0 < C
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/matrix-cells-in-distance-order
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class AllCellsDistOrder {
    /**
     * 先输出所有列表，然后再进行排序
     * 时间复杂度(nlogn)
     *
     * @param R
     * @param C
     * @param r0
     * @param c0
     * @return
     */
    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        List<int[]> list = new ArrayList<>(R * C);
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                list.add(new int[]{i, j});
            }
        }
        Collections.sort(list, Comparator.comparingInt(o -> distance(o, new int[]{r0, c0})));
        int[][] r = new int[R * C][2];
        for (int i = 0; i < R * C; i++) {
            r[i] = list.get(i);
        }
        return r;
    }

    private int distance(int[] p1, int[] p2) {
        return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
    }

    public static final int[][] DIR = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1}
    };

    /**
     * 换个思路，改成广度优先策略进行遍历
     * <p>
     * 广度优先搜索的顺序本质跟哈夫曼距离是一致的。
     *
     * @param R
     * @param C
     * @param r0
     * @param c0
     * @return
     */
    public int[][] allCellsDistOrder2(int R, int C, int r0, int c0) {
        int[][] visit = new int[R][C];
        int[][] result = new int[R * C][2];
        int idx = 0;
        Queue<int[]> queue = new LinkedList<>();
        visit[r0][c0] = 1;
        queue.add(new int[]{r0, c0});
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            result[idx][0] = poll[0];
            result[idx][1] = poll[1];
            idx++;
            for (int[] d : DIR) {
                int nx = poll[0] + d[0];
                int ny = poll[1] + d[1];
                if (nx >= 0 && nx < R && ny >= 0 && ny < C && visit[nx][ny] == 0) {
                    visit[nx][ny] = 1;
                    queue.add(new int[]{nx, ny});
                }
            }
        }
        return result;
    }
}
