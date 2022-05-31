package com.liyongquan.dp;

public class AttackBlue {
    /**
     * 求最大攻击力
     * attack.length==cost.length
     *
     * @param attack 每张卡的攻击力
     * @param cost   每张卡对应的消耗
     * @param total  总的蓝条
     * @return
     */
    public int cal(int[] attack, int[] cost, int total) {
        int len = attack.length;
        int[][] dp = new int[len][total + 1];
        for (int i = 0; i <= total; i++) {
            if (cost[0] <= i) {
                dp[0][i] = attack[0];
            }
        }
        for (int i = 1; i < len; i++) {
            for (int j = 1; j <= total; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= cost[i - 1]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - cost[i]] + attack[i]);
                }
            }
        }
        return dp[len - 1][total];
    }
}
