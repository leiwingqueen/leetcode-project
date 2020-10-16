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
     * 回溯法(超时)
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
        return dfs(obstacleGrid, 0, 0, row - 1, col - 1, path);
    }

    private List<List<Integer>> dfs(int[][] grid, int x, int y, int tx, int ty, List<List<Integer>> path) {
        if (grid[x][y] == 1) {
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
        if (x > tx || y > ty) {
            return Collections.EMPTY_LIST;
        }
        //往下
        if (x < tx && grid[x + 1][y] == 0) {
            path.add(Arrays.asList(x + 1, y));
            List<List<Integer>> down = dfs(grid, x + 1, y, tx, ty, path);
            //回溯
            path.remove(path.size() - 1);
            if (down.size() > 0) {
                return down;
            }
        }
        //往右
        if (y < ty && grid[x][y + 1] == 0) {
            path.add(Arrays.asList(x, y + 1));
            List<List<Integer>> right = dfs(grid, x, y + 1, tx, ty, path);
            //回溯
            path.remove(path.size() - 1);
            if (right.size() > 0) {
                return right;
            }
        }
        return Collections.EMPTY_LIST;
    }
}
