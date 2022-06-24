package com.liyongquan.dp;

/**
 * 174. 地下城游戏
 * <p>
 * 一些恶魔抓住了公主（P）并将她关在了地下城的右下角。地下城是由 M x N 个房间组成的二维网格。我们英勇的骑士（K）最初被安置在左上角的房间里，他必须穿过地下城并通过对抗恶魔来拯救公主。
 * <p>
 * 骑士的初始健康点数为一个正整数。如果他的健康点数在某一时刻降至 0 或以下，他会立即死亡。
 * <p>
 * 有些房间由恶魔守卫，因此骑士在进入这些房间时会失去健康点数（若房间里的值为负整数，则表示骑士将损失健康点数）；其他房间要么是空的（房间里的值为 0），要么包含增加骑士健康点数的魔法球（若房间里的值为正整数，则表示骑士将增加健康点数）。
 * <p>
 * 为了尽快到达公主，骑士决定每次只向右或向下移动一步。
 * <p>
 *  
 * <p>
 * 编写一个函数来计算确保骑士能够拯救到公主所需的最低初始健康点数。
 * <p>
 * 例如，考虑到如下布局的地下城，如果骑士遵循最佳路径 右 -> 右 -> 下 -> 下，则骑士的初始健康点数至少为 7。
 * <p>
 * -2 (K)	-3	3
 * -5	-10	1
 * 10	30	-5 (P)
 *  
 * <p>
 * 说明:
 * <p>
 * 骑士的健康点数没有上限。
 * <p>
 * 任何房间都可能对骑士的健康点数造成威胁，也可能增加骑士的健康点数，包括骑士进入的左上角房间以及公主被监禁的右下角房间。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/dungeon-game
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class DungeonGame {
    /**
     * 思路错误
     *
     * @param dungeon
     * @return
     */
    public int calculateMinimumHP(int[][] dungeon) {
        int row = dungeon.length, col = dungeon[0].length;
        int[][] dp0 = new int[row][col], dp1 = new int[row][col];
        //初始化
        dp0[0][0] = dungeon[0][0];
        dp1[0][0] = dungeon[0][0];
        for (int i = 1; i < row; i++) {
            dp1[i][0] = dp1[i - 1][0] + dungeon[i][0];
            dp0[i][0] = Math.min(dp0[i - 1][0], dp1[i][0]);
        }
        for (int i = 1; i < col; i++) {
            dp1[0][i] = dp1[0][i - 1] + dungeon[0][i];
            dp0[0][i] = Math.min(dp0[0][i - 1], dp1[0][i]);
        }
        //dp迭代
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                //两种方案的最低点
                int s1 = Math.min(dp0[i - 1][j], dp1[i - 1][j] + dungeon[i][j]);
                int s2 = Math.min(dp0[i][j - 1], dp1[i][j - 1] + dungeon[i][j]);
                if (s1 > s2) {
                    dp0[i][j] = s1;
                    dp1[i][j] = dp1[i - 1][j] + dungeon[i][j];
                } else {
                    dp0[i][j] = s2;
                    dp1[i][j] = dp1[i][j - 1] + dungeon[i][j];
                }
            }
        }
        return dp0[row - 1][col - 1] > 0 ? 1 : 1 - dp0[row - 1][col - 1];
    }

    /**
     * @param dungeon
     * @return
     */
    public int calculateMinimumHP2(int[][] dungeon) {
        int row = dungeon.length, col = dungeon[0].length;
        int sumPlus = 0, sumMinus = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (dungeon[i][j] >= 0) {
                    sumPlus += dungeon[i][j];
                } else {
                    sumMinus += dungeon[i][j];
                }
            }
        }
        //最大值的范围
        int k = sumPlus - sumMinus + 1;
        int[][][] dp2 = new int[row][col][k + 1];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                for (int l = 1; l <= k; l++) {
                    if (i == 0 && j == 0) {
                        dp2[0][0][l] = l - dungeon[0][0];
                    } else {
                        int tmp = Integer.MAX_VALUE;
                        if (i > 0) {
                            int s = l - dungeon[i][j] >= 1 ? dp2[i - 1][j][l - dungeon[i][j]] : dp2[i - 1][j][1];
                            tmp = Math.min(s, tmp);
                        }
                        if (j > 0) {
                            int s = l - dungeon[i][j] >= 1 ? dp2[i][j - 1][l - dungeon[i][j]] : dp2[i][j - 1][1];
                            tmp = Math.min(s, tmp);
                        }
                        dp2[i][j][l] = tmp;
                    }
                }
            }
        }
        return dp2[row - 1][col - 1][1];
    }
}
