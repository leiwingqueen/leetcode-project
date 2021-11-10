package com.liyongquan.backtrack;

//488. 祖玛游戏
//你正在参与祖玛游戏的一个变种。
//
//在这个祖玛游戏变体中，桌面上有 一排 彩球，每个球的颜色可能是：红色 'R'、黄色 'Y'、蓝色 'B'、绿色 'G' 或白色 'W' 。你的手中也有一些彩球。
//
//你的目标是 清空 桌面上所有的球。每一回合：
//
//从你手上的彩球中选出 任意一颗 ，然后将其插入桌面上那一排球中：两球之间或这一排球的任一端。
//接着，如果有出现 三个或者三个以上 且 颜色相同 的球相连的话，就把它们移除掉。
//如果这种移除操作同样导致出现三个或者三个以上且颜色相同的球相连，则可以继续移除这些球，直到不再满足移除条件。
//如果桌面上所有球都被移除，则认为你赢得本场游戏。
//重复这个过程，直到你赢了游戏或者手中没有更多的球。
//给你一个字符串 board ，表示桌面上最开始的那排球。另给你一个字符串 hand ，表示手里的彩球。请你按上述操作步骤移除掉桌上所有球，计算并返回所需的 最少 球数。如果不能移除桌上所有的球，返回 -1 。
//
// 
//
//示例 1：
//
//输入：board = "WRRBBW", hand = "RB"
//输出：-1
//解释：无法移除桌面上的所有球。可以得到的最好局面是：
//- 插入一个 'R' ，使桌面变为 WRRRBBW 。WRRRBBW -> WBBW
//- 插入一个 'B' ，使桌面变为 WBBBW 。WBBBW -> WW
//桌面上还剩着球，没有其他球可以插入。
//示例 2：
//
//输入：board = "WWRRBBWW", hand = "WRBRW"
//输出：2
//解释：要想清空桌面上的球，可以按下述步骤：
//- 插入一个 'R' ，使桌面变为 WWRRRBBWW 。WWRRRBBWW -> WWBBWW
//- 插入一个 'B' ，使桌面变为 WWBBBWW 。WWBBBWW -> WWWW -> empty
//只需从手中出 2 个球就可以清空桌面。
//示例 3：
//
//输入：board = "G", hand = "GGGGG"
//输出：2
//解释：要想清空桌面上的球，可以按下述步骤：
//- 插入一个 'G' ，使桌面变为 GG 。
//- 插入一个 'G' ，使桌面变为 GGG 。GGG -> empty
//只需从手中出 2 个球就可以清空桌面。
//示例 4：
//
//输入：board = "RBYYBBRRB", hand = "YRBGB"
//输出：3
//解释：要想清空桌面上的球，可以按下述步骤：
//- 插入一个 'Y' ，使桌面变为 RBYYYBBRRB 。RBYYYBBRRB -> RBBBRRB -> RRRB -> B
//- 插入一个 'B' ，使桌面变为 BB 。
//- 插入一个 'B' ，使桌面变为 BBB 。BBB -> empty
//只需从手中出 3 个球就可以清空桌面。
// 
//
//提示：
//
//1 <= board.length <= 16
//1 <= hand.length <= 5
//board 和 hand 由字符 'R'、'Y'、'B'、'G' 和 'W' 组成
//桌面上一开始的球中，不会有三个及三个以上颜色相同且连着的球
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/zuma-game
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author liyongquan
 * @date 2021/11/9
 */
public class ZumaGame {
    public int findMinStep(String board, String hand) {
        return 0;
    }

    private int backtrace(int[] board, int[] hand, int len, int left) {
        if (len == 0) {
            return 0;
        }
        if (left == 0) {
            return -1;
        }
        //先把连续>=3的子串给删掉
        Deque<Integer> deque = new LinkedList<>();
        int cnt = 0;
        for (int i = 0; i < len; i++) {
            if (i == 0 || board[i] == board[i - 1]) {
                deque.offerLast(board[i]);
                cnt++;
            } else {
                if (cnt >= 3) {
                    while (!deque.isEmpty() && deque.peekLast() == board[i - 1]) {
                        deque.pollLast();
                    }
                }
                deque.offerLast(board[i]);
                cnt = 1;
            }
        }
        //找到连续最长的子串
        int l = 0, r = 0;
        while (r < len) {
            if (r == 0 || board[r] == board[r - 1]) {
                r++;
            } else {
                //尝试插入
                l = r;
            }
        }
        return 0;
    }
}
