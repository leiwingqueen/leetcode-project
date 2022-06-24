package com.liyongquan.array;

//36. 有效的数独
//请你判断一个 9x9 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可。
//
//数字 1-9 在每一行只能出现一次。
//数字 1-9 在每一列只能出现一次。
//数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
//数独部分空格内已填入了数字，空白格用 '.' 表示。
//
//注意：
//
//一个有效的数独（部分已被填充）不一定是可解的。
//只需要根据以上规则，验证已经填入的数字是否有效即可。
// 
//
//示例 1：
//
//
//输入：board =
//[["5","3",".",".","7",".",".",".","."]
//,["6",".",".","1","9","5",".",".","."]
//,[".","9","8",".",".",".",".","6","."]
//,["8",".",".",".","6",".",".",".","3"]
//,["4",".",".","8",".","3",".",".","1"]
//,["7",".",".",".","2",".",".",".","6"]
//,[".","6",".",".",".",".","2","8","."]
//,[".",".",".","4","1","9",".",".","5"]
//,[".",".",".",".","8",".",".","7","9"]]
//输出：true
//示例 2：
//
//输入：board =
//[["8","3",".",".","7",".",".",".","."]
//,["6",".",".","1","9","5",".",".","."]
//,[".","9","8",".",".",".",".","6","."]
//,["8",".",".",".","6",".",".",".","3"]
//,["4",".",".","8",".","3",".",".","1"]
//,["7",".",".",".","2",".",".",".","6"]
//,[".","6",".",".",".",".","2","8","."]
//,[".",".",".","4","1","9",".",".","5"]
//,[".",".",".",".","8",".",".","7","9"]]
//输出：false
//解释：除了第一行的第一个数字从 5 改为 8 以外，空格内其他数字均与 示例1 相同。 但由于位于左上角的 3x3 宫内有两个 8 存在, 因此这个数独是无效的。
// 
//
//提示：
//
//board.length == 9
//board[i].length == 9
//board[i][j] 是一位数字或者 '.'
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/valid-sudoku
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

/**
 * @author liyongquan
 * @date 2021/9/17
 */
public class ValidSudoku {
    public static final int LEN = 9;

    public boolean isValidSudoku(char[][] board) {
        //行列判断
        if (!rowColCheck(board, true) || !rowColCheck(board, false)) {
            return false;
        }
        int[] start = {0, 0};
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!gridCheck(board, new int[]{start[0] + 3 * i, start[1] + 3 * j})) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean rowColCheck(char[][] board, boolean row) {
        for (int i = 0; i < LEN; i++) {
            int[] visit = new int[LEN];
            for (int j = 0; j < LEN; j++) {
                char ch = row ? board[i][j] : board[j][i];
                if (ch != '.') {
                    if (visit[ch - '1'] == 1) {
                        return false;
                    }
                    visit[ch - '1'] = 1;
                }
            }
        }
        return true;
    }

    private boolean gridCheck(char[][] board, int[] start) {
        int len = 3;
        int[] visit = new int[LEN];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                char ch = board[start[0] + i][start[1] + j];
                if (ch != '.') {
                    if (visit[ch - '1'] == 1) {
                        return false;
                    }
                    visit[ch - '1'] = 1;
                }
            }
        }
        return true;
    }

    //TODO:一次遍历能实现吗？

    /**
     * 每一行、每一列、每个格子用一个整形维护
     *
     * @param board
     * @return
     */
    public boolean isValidSudoku2(char[][] board) {
        int[] row = new int[LEN];
        int[] col = new int[LEN];
        int[] grid = new int[LEN];
        for (int i = 0; i < LEN; i++) {
            for (int j = 0; j < LEN; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '0';
                    int n = 1 << (num - 1);
                    if ((row[i] & n) != 0) {
                        return false;
                    }
                    row[i] |= n;
                    if ((col[j] & n) != 0) {
                        return false;
                    }
                    col[j] |= n;
                    int idx = i * 3 + (j / 3);
                    if ((grid[idx] & n) != 0) {
                        return false;
                    }
                    grid[idx] |= n;
                }
            }
        }
        return true;
    }
}
