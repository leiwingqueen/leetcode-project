package com.liyongquan.backtrack;

/**
 * 79. 单词搜索
 * <p>
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 * <p>
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 * <p>
 *  
 * <p>
 * 示例:
 * <p>
 * board =
 * [
 * ['A','B','C','E'],
 * ['S','F','C','S'],
 * ['A','D','E','E']
 * ]
 * <p>
 * 给定 word = "ABCCED", 返回 true
 * 给定 word = "SEE", 返回 true
 * 给定 word = "ABCB", 返回 false
 *  
 * <p>
 * 提示：
 * <p>
 * board 和 word 中只包含大写和小写英文字母。
 * 1 <= board.length <= 200
 * 1 <= board[i].length <= 200
 * 1 <= word.length <= 10^3
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-search
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class WordSearch {
    public static final int[][] DIR = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1}
    };

    /**
     * 回溯算法
     *
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        int row = board.length, col = board[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (word.charAt(0) == board[i][j]) {
                    if (backtrace(board, word, 0, i, j, row, col)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean backtrace(char[][] board, String word, int wordIdx, int x, int y, int row, int col) {
        if (wordIdx >= word.length()) {
            return true;
        }
        if (x < 0 || x >= row || y < 0 || y >= col) {
            return false;
        }
        if (board[x][y] != word.charAt(wordIdx)) {
            return false;
        }
        board[x][y] = ' ';
        //遍历4个方向
        for (int[] d : DIR) {
            int nx = x + d[0];
            int ny = y + d[1];
            if (backtrace(board, word, wordIdx + 1, nx, ny, row, col)) {
                return true;
            }
        }
        //回溯
        board[x][y] = word.charAt(wordIdx);
        return false;
    }
}
