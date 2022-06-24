package com.liyongquan.dfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * <p>
 * <p>
 * <p>
 * 上图为 8 皇后问题的一种解法。
 * <p>
 * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
 * <p>
 * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：4
 * 输出：[
 * [".Q..",  // 解法 1
 * "...Q",
 * "Q...",
 * "..Q."],
 * <p>
 * ["..Q.",  // 解法 2
 * "Q...",
 * "...Q",
 * ".Q.."]
 * ]
 * 解释: 4 皇后问题存在两个不同的解法。
 *  
 * <p>
 * 提示：
 * <p>
 * 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/n-queens
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NQueens {
    /**
     * 回溯
     *
     * @param n
     * @return
     */
    public List<List<String>> solveNQueens(int n) {
        int[][] grid = new int[n][n];
        return dfs(grid, n, 0);
    }

    private List<List<String>> dfs(int[][] grid, int n, int row) {
        if (row == n - 1) {
            List<List<String>> result = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                if (canSet(grid, n, row, i)) {
                    grid[row][i] = 1;
                    result.add(makeStr(grid, n));
                    //回溯
                    grid[row][i] = 0;
                }
            }
            return result;
        }
        List<List<String>> result = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (canSet(grid, n, row, i)) {
                grid[row][i] = 1;
                List<List<String>> r = dfs(grid, n, row + 1);
                //回溯
                grid[row][i] = 0;
                result.addAll(r);
            }
        }
        return result;
    }

    private List<String> makeStr(int[][] grid, int n) {
        List<String> result = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder(n);
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    sb.append('Q');
                } else {
                    sb.append('.');
                }
            }
            result.add(sb.toString());
        }
        return result;
    }

    /**
     * 这里的判断时间复杂度是O(n)，我们可以尝试把它简化成O(1)
     *
     * @param grid
     * @param n
     * @param x
     * @param y
     * @return
     */
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


    /**
     * 回溯解法2
     * 1.记录前面的移动我们简化成使用1维数据
     * 2.判断是否能放置皇后的逻辑我们简化成O(1)的复杂度
     *
     * @param n
     * @return
     */
    public List<List<String>> solveNQueens2(int n) {
        int[] queens = new int[n], col = new int[n], dia1 = new int[2 * n - 1], dia2 = new int[2 * n - 1];
        return dfs2(queens, n, 0, col, dia1, dia2);
    }


    private List<List<String>> dfs2(int[] queens, int n, int row, int[] col, int[] dia1, int[] dia2) {
        if (row == n - 1) {
            List<List<String>> result = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                if (canSet2(n, row, i, col, dia1, dia2)) {
                    queens[row] = i;
                    result.add(makeStr2(queens, n));
                    //回溯
                    queens[row] = 0;
                }
            }
            return result;
        }
        List<List<String>> result = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (canSet2(n, row, i, col, dia1, dia2)) {
                queens[row] = i;
                col[i] = 1;
                dia1[row - i + n - 1] = 1;
                dia2[row + i] = 1;
                List<List<String>> r = dfs2(queens, n, row + 1, col, dia1, dia2);
                //回溯
                queens[row] = 0;
                col[i] = 0;
                dia1[row - i + n - 1] = 0;
                dia2[row + i] = 0;

                result.addAll(r);
            }
        }
        return result;
    }

    private boolean canSet2(int n, int x, int y, int[] col, int[] dia1, int[] dia2) {
        //正上方
        if (col[y] == 1) {
            return false;
        }
        //左斜上方
        if (dia1[x - y + n - 1] == 1) {
            return false;
        }
        //右斜上方
        if (dia2[x + y] == 1) {
            return false;
        }
        return true;
    }

    private List<String> makeStr2(int[] queens, int n) {
        List<String> result = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder(n);
            for (int j = 0; j < n; j++) {
                if (queens[i] == j) {
                    sb.append('Q');
                } else {
                    sb.append('.');
                }
            }
            result.add(sb.toString());
        }
        return result;
    }


    public static void main(String[] args) {
        NQueens nq = new NQueens();
        List<List<String>> lists = nq.solveNQueens2(4);
        for (List<String> list : lists) {
            System.out.println("===================");
            for (String s : list) {
                System.out.println(s);
            }
        }
    }
}
