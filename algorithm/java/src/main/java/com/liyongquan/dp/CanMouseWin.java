package com.liyongquan.dp;

//一只猫和一只老鼠在玩一个叫做猫和老鼠的游戏。
//
//它们所处的环境设定是一个 rows x cols 的方格 grid ，其中每个格子可能是一堵墙、一块地板、一位玩家（猫或者老鼠）或者食物。
//
//玩家由字符 'C' （代表猫）和 'M' （代表老鼠）表示。
//地板由字符 '.' 表示，玩家可以通过这个格子。
//墙用字符 '#' 表示，玩家不能通过这个格子。
//食物用字符 'F' 表示，玩家可以通过这个格子。
//字符 'C' ， 'M' 和 'F' 在 grid 中都只会出现一次。
//猫和老鼠按照如下规则移动：
//
//老鼠 先移动 ，然后两名玩家轮流移动。
//每一次操作时，猫和老鼠可以跳到上下左右四个方向之一的格子，他们不能跳过墙也不能跳出 grid 。
//catJump 和 mouseJump 是猫和老鼠分别跳一次能到达的最远距离，它们也可以跳小于最大距离的长度。
//它们可以停留在原地。
//老鼠可以跳跃过猫的位置。
//游戏有 4 种方式会结束：
//
//如果猫跟老鼠处在相同的位置，那么猫获胜。
//如果猫先到达食物，那么猫获胜。
//如果老鼠先到达食物，那么老鼠获胜。
//如果老鼠不能在 1000 次操作以内到达食物，那么猫获胜。
//给你 rows x cols 的矩阵 grid 和两个整数 catJump 和 mouseJump ，双方都采取最优策略，如果老鼠获胜，那么请你返回 true ，否则返回 false 。
//
// 
//
//示例 1：
//
//
//
//输入：grid = ["####F","#C...","M...."], catJump = 1, mouseJump = 2
//输出：true
//解释：猫无法抓到老鼠，也没法比老鼠先到达食物。
//示例 2：
//
//
//
//输入：grid = ["M.C...F"], catJump = 1, mouseJump = 4
//输出：true
//示例 3：
//
//输入：grid = ["M.C...F"], catJump = 1, mouseJump = 3
//输出：false
//示例 4：
//
//输入：grid = ["C...#","...#F","....#","M...."], catJump = 2, mouseJump = 5
//输出：false
//示例 5：
//
//输入：grid = [".M...","..#..","#..#.","C#.#.","...#F"], catJump = 3, mouseJump = 1
//输出：true
// 
//
//提示：
//
//rows == grid.length
//cols = grid[i].length
//1 <= rows, cols <= 8
//grid[i][j] 只包含字符 'C' ，'M' ，'F' ，'.' 和 '#' 。
//grid 中只包含一个 'C' ，'M' 和 'F' 。
//1 <= catJump, mouseJump <= 8
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/cat-and-mouse-ii
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import java.util.Arrays;

public class CanMouseWin {
    private int row;
    private int col;
    private int[][] map;
    private int[] target;
    private int catJump;
    private int mouseJump;

    /**
     * 先试试简单的回溯
     *
     * @param grid
     * @param catJump
     * @param mouseJump
     * @return
     */
    public boolean canMouseWin(String[] grid, int catJump, int mouseJump) {
        row = grid.length;
        col = grid[0].length();
        map = new int[row][col];
        this.catJump = catJump;
        this.mouseJump = mouseJump;
        int[] cat = null, mouse = null;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                char ch = grid[i].charAt(j);
                if (ch == '#') {
                    map[i][j] = 1;
                } else if (ch == 'C') {
                    cat = new int[]{i, j};
                } else if (ch == 'M') {
                    mouse = new int[]{i, j};
                } else if (ch == 'F') {
                    target = new int[]{i, j};
                }
            }
        }
        return backtrace(cat, mouse, 1000, true);
    }

    private boolean backtrace(int[] cat, int[] mouse, int step, boolean turn) {
        if (Arrays.equals(mouse, target)) {
            return true;
        }
        if (Arrays.equals(cat, mouse) || Arrays.equals(cat, target)) {
            return false;
        }
        if (step == 0) {
            return false;
        }
        if (turn) {
            //老鼠的回合
            for (int i = Math.max(0, mouse[0] - mouseJump); i <= Math.min(row - 1, mouse[0] + mouseJump); i++) {
                if (map[i][mouse[1]] == 0) {
                    if (backtrace(cat, new int[]{i, mouse[1]}, step, false)) {
                        return true;
                    }
                }
            }
            for (int i = Math.max(0, mouse[1] - mouseJump); i < Math.min(col - 1, mouse[1] + mouseJump); i++) {
                if (map[mouse[0]][i] == 0) {
                    if (backtrace(cat, new int[]{mouse[0], i}, step, false)) {
                        return true;
                    }
                }
            }
            return false;
        } else {
            //猫的回合
            for (int i = Math.max(0, cat[0] - catJump); i <= Math.min(row - 1, cat[0] + catJump); i++) {
                if (map[i][cat[1]] == 0) {
                    if (!backtrace(new int[]{i, cat[1]}, mouse, step - 1, true)) {
                        return false;
                    }
                }
            }
            for (int i = Math.max(0, cat[1] - catJump); i < Math.min(col - 1, cat[1] + catJump); i++) {
                if (map[cat[0]][i] == 0) {
                    if (!backtrace(new int[]{cat[0], i}, mouse, step - 1, true)) {
                        return false;
                    }
                }
            }
            return true;
        }
    }
}
