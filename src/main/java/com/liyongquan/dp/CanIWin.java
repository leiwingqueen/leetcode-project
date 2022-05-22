package com.liyongquan.dp;

//464. 我能赢吗
//在 "100 game" 这个游戏中，两名玩家轮流选择从 1 到 10 的任意整数，累计整数和，先使得累计整数和 达到或超过  100 的玩家，即为胜者。
//
//如果我们将游戏规则改为 “玩家 不能 重复使用整数” 呢？
//
//例如，两个玩家可以轮流从公共整数池中抽取从 1 到 15 的整数（不放回），直到累计整数和 >= 100。
//
//给定两个整数 maxChoosableInteger （整数池中可选择的最大数）和 desiredTotal（累计和），若先出手的玩家是否能稳赢则返回 true ，否则返回 false 。假设两位玩家游戏时都表现 最佳 。
//
// 
//
//示例 1：
//
//输入：maxChoosableInteger = 10, desiredTotal = 11
//输出：false
//解释：
//无论第一个玩家选择哪个整数，他都会失败。
//第一个玩家可以选择从 1 到 10 的整数。
//如果第一个玩家选择 1，那么第二个玩家只能选择从 2 到 10 的整数。
//第二个玩家可以通过选择整数 10（那么累积和为 11 >= desiredTotal），从而取得胜利.
//同样地，第一个玩家选择任意其他整数，第二个玩家都会赢。
//示例 2:
//
//输入：maxChoosableInteger = 10, desiredTotal = 0
//输出：true
//示例 3:
//
//输入：maxChoosableInteger = 10, desiredTotal = 1
//输出：true
// 
//
//提示:
//
//1 <= maxChoosableInteger <= 20
//0 <= desiredTotal <= 300
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/can-i-win
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

public class CanIWin {
    /**
     * dp+状态压缩
     *
     * 超时
     *
     * @param maxChoosableInteger
     * @param desiredTotal
     * @return
     */
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        int mx = 1 << maxChoosableInteger;
        boolean[][] dp = new boolean[desiredTotal + 1][mx];
        for (int i = 0; i < mx; i++) {
            dp[0][i] = true;
        }
        for (int i = 1; i <= desiredTotal; i++) {
            for (int j = 0; j < mx; j++) {
                //选择的整数
                for (int k = 1; k <= maxChoosableInteger; k++) {
                    if ((j & (1 << (k - 1))) == 0) {
                        if (k >= i) {
                            dp[i][j] = true;
                            break;
                        } else {
                            if (!dp[i - k][j | (1 << (k - 1))]) {
                                dp[i][j] = true;
                                break;
                            }
                        }
                    }
                }
            }
        }
        return dp[desiredTotal][0];
    }
}
