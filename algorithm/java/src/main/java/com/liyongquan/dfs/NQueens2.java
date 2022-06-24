package com.liyongquan.dfs;

/**
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * <p>
 * <p>
 * <p>
 * 上图为 8 皇后问题的一种解法。
 * <p>
 * 给定一个整数 n，返回 n 皇后不同的解决方案的数量。
 * <p>
 * 示例:
 * <p>
 * 输入: 4
 * 输出: 2
 * 解释: 4 皇后问题存在如下两个不同的解法。
 * [
 *  [".Q..",  // 解法 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 * <p>
 *  ["..Q.",  // 解法 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 *  
 * <p>
 * 提示：
 * <p>
 * 皇后，是国际象棋中的棋子，意味着国王的妻子。皇后只做一件事，那就是“吃子”。当她遇见可以吃的棋子时，就迅速冲上去吃掉棋子。当然，她横、竖、斜都可走一或 N-1 步，可进可退。（引用自 百度百科 - 皇后 ）
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/n-queens-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NQueens2 {
    /**
     * 经典的回溯解法
     * 时间复杂度O(n!)，空间复杂度O(n^2)
     *
     * @param n
     * @return
     */
    public int totalNQueens(int n) {
        int[][] grid = new int[n][n];
        return dfs(grid, n, 0);
    }

    private int dfs(int[][] grid, int n, int row) {
        if (row == n - 1) {
            int count = 0;
            for (int i = 0; i < n; i++) {
                if (canSet(grid, n, row, i)) {
                    count++;
                }
            }
            return count;
        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (canSet(grid, n, row, i)) {
                grid[row][i] = 1;
                int r = dfs(grid, n, row + 1);
                //回溯
                grid[row][i] = 0;
                count += r;
            }
        }
        return count;
    }

    private boolean canSet(int[][] grid, int n, int x, int y) {
        //正上方
        for (int i = 0; i <= x - 1; i++) {
            if (grid[i][y] == 1) {
                return false;
            }
        }
        //左斜上方
        int x1 = x - 1, y1 = y - 1;
        while (x1 >= 0 && y1 >= 0) {
            if (grid[x1--][y1--] == 1) {
                return false;
            }
        }
        //右斜上方
        x1 = x - 1;
        y1 = y + 1;
        while (x1 >= 0 && y1 < n) {
            if (grid[x1--][y1++] == 1) {
                return false;
            }
        }
        return true;
    }
}
