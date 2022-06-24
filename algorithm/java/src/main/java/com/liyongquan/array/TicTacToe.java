package com.liyongquan.array;

/**
 * 设计一个算法，判断玩家是否赢了井字游戏。输入是一个 N x N 的数组棋盘，由字符" "，"X"和"O"组成，其中字符" "代表一个空位。
 * <p>
 * 以下是井字游戏的规则：
 * <p>
 * 玩家轮流将字符放入空位（" "）中。
 * 第一个玩家总是放字符"O"，且第二个玩家总是放字符"X"。
 * "X"和"O"只允许放置在空位中，不允许对已放有字符的位置进行填充。
 * 当有N个相同（且非空）的字符填充任何行、列或对角线时，游戏结束，对应该字符的玩家获胜。
 * 当所有位置非空时，也算为游戏结束。
 * 如果游戏结束，玩家不允许再放置字符。
 * 如果游戏存在获胜者，就返回该游戏的获胜者使用的字符（"X"或"O"）；如果游戏以平局结束，则返回 "Draw"；如果仍会有行动（游戏未结束），则返回 "Pending"。
 * <p>
 * 示例 1：
 * <p>
 * 输入： board = ["O X"," XO","X O"]
 * 输出： "X"
 * 示例 2：
 * <p>
 * 输入： board = ["OOX","XXO","OXO"]
 * 输出： "Draw"
 * 解释： 没有玩家获胜且不存在空位
 * 示例 3：
 * <p>
 * 输入： board = ["OOX","XXO","OX "]
 * 输出： "Pending"
 * 解释： 没有玩家获胜且仍存在空位
 * 提示：
 * <p>
 * 1 <= board.length == board[i].length <= 100
 * 输入一定遵循井字棋规则
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/tic-tac-toe-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class TicTacToe {
    /**
     * 暴力解法，写得超级复杂。。
     *
     * @param board
     * @return
     */
    public String tictactoe(String[] board) {
        int n = board.length;
        boolean full = true;
        //所有横线遍历
        for (int i = 0; i < n; i++) {
            char pre = board[i].charAt(0);
            if (pre == ' ') {
                full = false;
                continue;
            }
            int j = 1;
            while (j < n && board[i].charAt(j) == pre) {
                j++;
            }
            if (j == n) {
                return new String(new char[]{pre});
            }
        }
        //所有竖线
        for (int i = 0; i < n; i++) {
            char pre = board[0].charAt(i);
            if (pre == ' ') {
                full = false;
                continue;
            }
            int j = 1;
            while (j < n && board[j].charAt(i) == pre) {
                j++;
            }
            if (j == n) {
                return new String(new char[]{pre});
            }
        }
        //两个斜线
        if (board[0].charAt(0) == ' ') {
            full = false;
        } else {
            int i = 0, j = 0;
            char pre = board[0].charAt(0);
            while (i < n && board[i].charAt(j) == pre) {
                i++;
                j++;
            }
            if (i == n) {
                return new String(new char[]{pre});
            }
        }
        if (board[0].charAt(n - 1) == ' ') {
            full = false;
        } else {
            int i = 0, j = n - 1;
            char pre = board[i].charAt(j);
            while (i < n && board[i].charAt(j) == pre) {
                i++;
                j--;
            }
            if (i == n) {
                return new String(new char[]{pre});
            }
        }
        return full ? "Draw" : "Pending";
    }

    public static void main(String[] args) {
        String[] xo = {
                "OXXOX",
                "XXOXO",
                "OOXOO",
                "OXXXX",
                "OXOOO"
        };
        TicTacToe tt = new TicTacToe();
        String r = tt.tictactoe(xo);
        System.out.println(r);
    }
}
