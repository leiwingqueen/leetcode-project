package com.liyongquan.bfs;

import java.util.LinkedList;
import java.util.List;

//给定一个 m x n 二维字符网格 board 和一个单词（字符串）列表 words，找出所有同时在二维网格和字典中出现的单词。
//
//单词必须按照字母顺序，通过 相邻的单元格 内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。
//
// 
//
//示例 1：
//
//
//输入：board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words = ["oath","pea","eat","rain"]
//输出：["eat","oath"]
//示例 2：
//
//
//输入：board = [["a","b"],["c","d"]], words = ["abcb"]
//输出：[]
// 
//
//提示：
//
//m == board.length
//n == board[i].length
//1 <= m, n <= 12
//board[i][j] 是一个小写英文字母
//1 <= words.length <= 3 * 104
//1 <= words[i].length <= 10
//words[i] 由小写英文字母组成
//words 中的所有字符串互不相同
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/word-search-ii
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

/**
 * @author liyongquan
 * @date 2021/9/16
 */
public class FindWords {
    int[][] DIRS = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1}
    };

    /**
     * 回溯解法，性能击败5%
     *
     * @param board
     * @param words
     * @return
     */
    public List<String> findWords(char[][] board, String[] words) {
        int m = board.length, n = board[0].length;
        List<String> res = new LinkedList<>();
        for (String word : words) {
            if (match(board, m, n, word)) {
                res.add(word);
            }
        }
        return res;
    }

    private boolean match(char[][] board, int m, int n, String word) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                boolean[][] visit = new boolean[m][n];
                if (match(board, m, n, i, j, visit, word, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean match(char[][] board, int m, int n,
                          int x, int y, boolean[][] visit, String word, int idx) {
        if (idx == word.length()) {
            return true;
        }
        if (x < 0 || x >= m || y < 0 || y >= n
                || board[x][y] != word.charAt(idx) || visit[x][y]) {
            return false;
        }
        visit[x][y] = true;
        for (int[] dir : DIRS) {
            int nx = x + dir[0];
            int ny = y + dir[1];
            if (match(board, m, n, nx, ny, visit, word, idx + 1)) {
                return true;
            }
        }
        visit[x][y] = false;
        return false;
    }

    //TODO:优化方案？
}
