package com.liyongquan.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 设想有个机器人坐在一个网格的左上角，网格 r 行 c 列。机器人只能向下或向右移动，但不能走到一些被禁止的网格（有障碍物）。设计一种算法，寻找机器人从左上角移动到右下角的路径。
 * <p>
 * <p>
 * <p>
 * 网格中的障碍物和空位置分别用 1 和 0 来表示。
 * <p>
 * 返回一条可行的路径，路径由经过的网格的行号和列号组成。左上角为 0 行 0 列。如果没有可行的路径，返回空数组。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * [
 *   [0,0,0],
 *   [0,1,0],
 *   [0,0,0]
 * ]
 * 输出: [[0,0],[0,1],[0,2],[1,2],[2,2]]
 * 解释:
 * 输入中标粗的位置即为输出表示的路径，即
 * 0行0列（左上角） -> 0行1列 -> 0行2列 -> 1行2列 -> 2行2列（右下角）
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/robot-in-a-grid-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class PathWithObstacles {
    /**
     * 回溯法
     *
     * <p>
     * 题目要求只要返回一条可行的路径即可
     *
     * @param obstacleGrid
     * @return
     */
    public List<List<Integer>> pathWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid.length == 0) {
            return Collections.EMPTY_LIST;
        }
        int row = obstacleGrid.length, col = obstacleGrid[0].length;
        List<List<Integer>> path = new ArrayList<>(row + col - 1);
        path.add(Arrays.asList(0, 0));
        //需要增加这个访问记录，避免计算重复的解，不然会超时
        //剪枝的重点
        int[][] visit = new int[row][col];
        return dfs(obstacleGrid, 0, 0, row - 1, col - 1, path, visit);
    }

    private List<List<Integer>> dfs(int[][] grid, int x, int y, int tx, int ty, List<List<Integer>> path, int[][] visit) {
        if (x > tx || y > ty || visit[x][y] == 1 || grid[x][y] == 1) {
            return Collections.EMPTY_LIST;
        }
        if (x == tx && y == ty) {
            //这里一定要深度拷贝一个新的对象，不能直接返回path，不然path会因为公用对象，回溯导致返回只有[0,0]
            List<List<Integer>> result = new ArrayList<>(path.size());
            for (int i = 0; i < path.size(); i++) {
                result.add(Arrays.asList(path.get(i).get(0), path.get(i).get(1)));
            }
            return result;
        }
        visit[x][y] = 1;
        //往下
        if (x < tx && grid[x + 1][y] == 0) {
            path.add(Arrays.asList(x + 1, y));
            List<List<Integer>> down = dfs(grid, x + 1, y, tx, ty, path, visit);
            //回溯
            path.remove(path.size() - 1);
            if (down.size() > 0) {
                return down;
            }
        }
        //往右
        if (y < ty && grid[x][y + 1] == 0) {
            path.add(Arrays.asList(x, y + 1));
            List<List<Integer>> right = dfs(grid, x, y + 1, tx, ty, path, visit);
            //回溯
            path.remove(path.size() - 1);
            if (right.size() > 0) {
                return right;
            }
        }
        return Collections.EMPTY_LIST;
    }

    /**
     * dp解法
     * 假设f(i,j)为[0,0]是否能到达[i,j]。
     * 则有f(i,j)=f(i-1,j)||f(i,j-1)
     * <p>
     * 得到一个m*n的矩阵后，直接从(0,0)一路选择到终点即可(选择f(i,j)=true)，性能是O(m+n)
     * <p>
     * 问题：为什么f(i,j)不直接存储路径，这样就不需要再做一次遍历？
     * 答：空间损耗会比较多，相当于每个节点都要存储一份完整的路径，另外路径的拷贝也有时间损耗。
     * <p>
     * 时间复杂度O(m*n)，空间复杂度O(m*n)
     *
     * @param obstacleGrid
     * @return
     */
    public List<List<Integer>> pathWithObstacles2(int[][] obstacleGrid) {
        if (obstacleGrid.length == 0) {
            return Collections.EMPTY_LIST;
        }
        int row = obstacleGrid.length, col = obstacleGrid[0].length;
        boolean[][] dp = new boolean[row][col];
        //初始化
        dp[0][0] = obstacleGrid[0][0] == 0;
        for (int i = 1; i < row; i++) {
            if (obstacleGrid[i][0] == 1) {
                dp[i][0] = false;
            } else {
                dp[i][0] = dp[i - 1][0];
            }
        }
        for (int i = 1; i < col; i++) {
            if (obstacleGrid[0][i] == 1) {
                dp[0][i] = false;
            } else {
                dp[0][i] = dp[0][i - 1];
            }
        }
        //dp迭代
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = false;
                } else {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                }
            }
        }
        if (!dp[row - 1][col - 1]) {
            return Collections.EMPTY_LIST;
        }
        //遍历一次，找到路径，需要反向查找
        List[] result = new List[row + col - 1];
        int x = row-1, y = col-1;
        for (int i = row + col - 2; i >= 0; i--) {
            result[i] = Arrays.asList(x, y);
            if (x > 0 && dp[x - 1][y]) {
                x--;
            } else {
                y--;
            }
        }
        return Arrays.asList(result);
    }
}
