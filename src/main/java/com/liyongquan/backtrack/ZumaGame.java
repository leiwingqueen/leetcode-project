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

import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @author liyongquan
 * @date 2021/11/9
 */
@Slf4j
public class ZumaGame {
    /**
     * 超时，看能否做一些剪枝
     *
     * @param board
     * @param hand
     * @return
     */
    private int res = -1;

    /**
     * 最后一个用例超时，状态压缩后使用记忆？
     *
     * @param board
     * @param hand
     * @return
     */
    public int findMinStep(String board, String hand) {
        //'R'、'Y'、'B'、'G'、'W'
        Map<Character, Integer> mp = new HashMap<>();
        mp.put('R', 0);
        mp.put('Y', 1);
        mp.put('B', 2);
        mp.put('G', 3);
        mp.put('W', 4);
        int[] bo = new int[board.length()];
        for (int i = 0; i < board.length(); i++) {
            Integer num = mp.get(board.charAt(i));
            bo[i] = num;
        }
        int[] ha = new int[5];
        for (int i = 0; i < hand.length(); i++) {
            ha[mp.get(hand.charAt(i))]++;
        }
        backtrace(bo, ha, 0);
        return res;
    }

    private void backtrace(int[] board, int[] hand, int cur) {
        if (board.length == 0) {
            if ((res == -1 || cur < res)) {
                res = cur;
            }
            return;
        }
        //剪枝
        if (res >= 0 && cur >= res) {
            return;
        }
        //先扫描一遍，看下是否能够直接删除
        int l = 0, r = 0;
        while (r < board.length) {
            if (l == r || board[r] == board[r - 1]) {
                r++;
            } else {
                if (r - l >= 3) {
                    //直接消除[l,r)这个区间的数字
                    int[] nb = remove(board, l, r);
                    backtrace(nb, hand, cur);
                    return;
                }
                l = r;
            }
        }
        if (r - l >= 3) {
            //直接消除[l,r)这个区间的数字
            int[] nb = remove(board, l, r);
            backtrace(nb, hand, cur);
            return;
        }
        //优先尝试找相邻的地方插入
        l = 0;
        r = 0;
        while (r < board.length) {
            if (l == r || board[r] == board[r - 1]) {
                r++;
            } else {
                //尝试插入看够不够数字
                if (hand[board[l]] + r - l >= 3) {
                    int[] nb = remove(board, l, r);
                    int use = 3 - r + l;
                    hand[board[l]] -= use;
                    backtrace(nb, hand, cur + use);
                    hand[board[l]] += use;
                }
                l = r;
            }
        }
        //尝试插入看够不够数字
        if (hand[board[l]] + r - l >= 3) {
            int[] nb = remove(board, l, r);
            int use = 3 - r + l;
            hand[board[l]] -= use;
            backtrace(nb, hand, cur + use);
            hand[board[l]] += use;
        }
        //上面的方式都不行，则只能穷举
        for (int i = 0; i <= board.length; i++) {
            for (int j = 0; j < 5; j++) {
                if (hand[j] > 0) {
                    int[] nb = add(board, i, j);
                    hand[j]--;
                    backtrace(nb, hand, cur + 1);
                    hand[j]++;
                }
            }
        }
    }

    private int[] remove(int[] board, int l, int r) {
        int[] nb = new int[board.length - (r - l)];
        int idx = 0;
        for (int i = 0; i < l; i++) {
            nb[idx++] = board[i];
        }
        for (int i = r; i < board.length; i++) {
            nb[idx++] = board[i];
        }
        return nb;
    }

    private int[] add(int[] board, int idx, int num) {
        int[] nb = new int[board.length + 1];
        int j = 0;
        for (int i = 0; i < idx; i++) {
            nb[j++] = board[i];
        }
        nb[j++] = num;
        for (int i = idx; i < board.length; i++) {
            nb[j++] = board[i];
        }
        return nb;
    }
}
