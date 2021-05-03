package com.liyongquan.interview;

/**
 * 1、2、5三种硬币，一共使用k个，要凑成target共有几种方式？
 * （滴滴面试题）
 */
public class CoinCount {
    /**
     * 假设求的是排列，也就是1,2,1和1,1,2是两种不同的方案
     *
     * @param k
     * @param target
     * @return
     */
    public int coinCount(int k, int target) {
        int[][] dp = new int[k][target + 1];
        //dp初始化
        for (int i = 1; i <= target; i++) {
            if (i == 1 || i == 2 || i == 5) {
                dp[0][i] = 1;
            }
        }
        //dp迭代
        for (int i = 1; i < k; i++) {
            for (int j = 1; j <= target; j++) {
                if (j - 1 >= 0) {
                    dp[i][j] += dp[i - 1][j - 1];
                }
                if (j - 2 >= 0) {
                    dp[i][j] += dp[i - 1][j - 2];
                }
                if (j - 5 >= 0) {
                    dp[i][j] += dp[i - 1][j - 5];
                }
            }
        }
        return dp[k - 1][target];
    }

    /**
     * 去掉重复解-回溯解法
     * <p>
     * 超时
     *
     * @param k
     * @param target
     * @return
     */
    public int coinCount2(int k, int target) {
        return backtrace(k, target, 0);
    }

    private int backtrace(int k, int target, int last) {
        if (target <= 0) {
            return 0;
        }
        if (k == 1) {
            return target == 1 || target == 2 || target == 5 ? 1 : 0;
        }
        int[] coins = {1, 2, 5};
        int cnt = 0;
        for (int coin : coins) {
            if (coin >= last) {
                cnt += backtrace(k - 1, target - coin, coin);
            }
        }
        return cnt;
    }

    /**
     * 去掉重复解-dp解法
     * <p>
     * 建立3个dp,最后一个数字分别为1,2,5的数量
     * <p>
     * dp1[k,t]=k==t?1:0
     * dp2[k,t]=dp1[k-1,t-2]+dp2[k-1,t-2]
     * dp3[k,t]=dp1[k-1,t-5]+dp2[k-1,t-5]+dp5[k-1,t-5];
     *
     * @param k
     * @param target
     * @return
     */
    public int coinCount3(int k, int target) {
        //dp1不需要存储，直接O(1)可以计算出来
        //int[][] dp1 = new int[k][target + 1];
        int[][] dp2 = new int[k][target + 1];
        int[][] dp5 = new int[k][target + 1];
        //dp初始化
        for (int i = 1; i <= target; i++) {
            if (i == 2) {
                dp2[0][i] = 1;
            }
            if (i == 5) {
                dp5[0][i] = 1;
            }
        }
        //dp迭代
        for (int i = 1; i < k; i++) {
            for (int j = 2; j <= target; j++) {
                if (i == j - 2) {
                    dp2[i][j] += 1;
                }
                dp2[i][j] += dp2[i - 1][j - 2];
            }
        }
        for (int i = 1; i < k; i++) {
            for (int j = 5; j <= target; j++) {
                if (i == j - 5) {
                    dp5[i][j] += 1;
                }
                dp5[i][j] += dp2[i - 1][j - 5] + dp5[i - 1][j - 5];
            }
        }
        return (k == target ? 1 : 0) + dp2[k - 1][target] + dp5[k - 1][target];
    }

}
