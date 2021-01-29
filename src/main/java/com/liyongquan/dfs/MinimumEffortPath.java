package com.liyongquan.dfs;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

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
}
