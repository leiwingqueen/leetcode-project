package com.liyongquan.weeklycontest.spring2021;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;

//某解密游戏中，有一个 N\*M 的迷宫，迷宫地形会随时间变化而改变，迷宫出口一直位于 `(n-1,m-1)` 位置。迷宫变化规律记录于 `maze` 中，`
//maze[i]` 表示 `i` 时刻迷宫的地形状态，`"."` 表示可通行空地，`"#"` 表示陷阱。
//
//地形图初始状态记作 `maze[0]`，此时小力位于起点 `(0,0)`。此后每一时刻可选择往上、下、左、右其一方向走一步，或者停留在原地。
//
//小力背包有以下两个魔法卷轴（卷轴使用一次后消失）：
//+ 临时消除术：将指定位置在下一个时刻变为空地；
//+ 永久消除术：将指定位置永久变为空地。
//
//请判断在迷宫变化结束前（含最后时刻），小力能否在不经过任意陷阱的情况下到达迷宫出口呢？
//
//**注意： 输入数据保证起点和终点在所有时刻均为空地。**
//
//**示例 1：**
//>输入：`maze = [[".#.","#.."],["...",".#."],[".##",".#."],["..#",".#."]]`
//>
//>输出：`true`
//>
//>解释：
//![maze.gif](https://pic.leetcode-cn.com/1615892239-SCIjyf-maze.gif)
//
//
//**示例 2：**
//>输入：`maze = [[".#.","..."],["...","..."]]`
//>
//>输出：`false`
//>
//>解释：由于时间不够，小力无法到达终点逃出迷宫。
//
//**示例 3：**
//>输入：`maze = [["...","...","..."],[".##","###","##."],[".##","###","##."],[".##
//","###","##."],[".##","###","##."],[".##","###","##."],[".##","###","##."]]`
//>
//>输出：`false`
//>
//>解释：由于道路不通，小力无法到达终点逃出迷宫。
//
//**提示：**
//- `1 <= maze.length <= 100`
//- `1 <= maze[i].length, maze[i][j].length <= 50`
//- `maze[i][j]` 仅包含 `"."`、`"#"` 👍 9 👎 0

@Slf4j
public class EscapeMaze {
    public static final int[][] DIR = {
            {0, 0},
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1}
    };

    /**
     * 回溯解法
     * <p>
     * 超时
     *
     * 如何剪枝？
     *
     * @param maze
     * @return
     */
    public boolean escapeMaze(List<List<String>> maze) {
        return backtrace(maze, new LinkedList<>(), 0, new int[]{0, 0}, 1, 1, new int[]{});
    }

    private boolean backtrace(List<List<String>> maze, LinkedList<int[]> path,
                              int idx, int[] cur, int magic1, int magic2, int[] safePoint) {
        //log.info("位置:[{},{}],idx:{},magic1:{},magic2:{}", cur[0], cur[1], idx, magic1, magic2);
        if (idx >= maze.size()) {
            return false;
        }
        try {
            path.offerLast(new int[]{cur[0], cur[1]});
            List<String> map = maze.get(idx);
            int row = map.size(), col = map.get(0).length();
            if (cur[0] == row - 1 && cur[1] == col - 1) {
                //输出结果
                while (!path.isEmpty()) {
                    int[] p = path.pollFirst();
                    log.info("[{},{}]", p[0], p[1]);
                }
                return true;
            }
            if (idx == maze.size() - 1) {
                return false;
            }
            map = maze.get(idx + 1);
            for (int[] dir : DIR) {
                int x = cur[0] + dir[0], y = cur[1] + dir[1];
                if (x >= 0 && x < row && y >= 0 && y < col) {
                    if (map.get(x).charAt(y) == '.' || (safePoint.length > 0 && safePoint[0] == x && safePoint[1] == y)) {
                        if (backtrace(maze, path, idx + 1, new int[]{x, y}, magic1, magic2, safePoint)) {
                            return true;
                        }
                    } else if (magic1 > 0) {
                        if (backtrace(maze, path, idx + 1, new int[]{x, y}, magic1 - 1, magic2, safePoint)) {
                            return true;
                        }
                    } else if (magic2 > 0) {
                        if (backtrace(maze, path, idx + 1, new int[]{x, y}, magic1, magic2 - 1, new int[]{x, y})) {
                            return true;
                        }
                    }
                }
            }
        } finally {
            //还原现场，这样写就不容易遗漏了
            path.pollLast();
        }
        return false;
    }
}
