package com.liyongquan.dp;

/**
 * 把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。
 * <p>
 *  
 * <p>
 * 你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1
 * 输出: [0.16667,0.16667,0.16667,0.16667,0.16667,0.16667]
 * 示例 2:
 * <p>
 * 输入: 2
 * 输出: [0.02778,0.05556,0.08333,0.11111,0.13889,0.16667,0.13889,0.11111,0.08333,0.05556,0.02778]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/nge-tou-zi-de-dian-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NNums {
    /**
     * 假设dp[i][j]为i个骰子总和为j的所有组合，则结果为dp[n][j]的所有组合，其中j的范围为[n,6*n]
     * dp方程：
     * dp[i][j]=dp[i-1][j-1]+dp[i-1][j-2]+...+dp[i-1][j-6];
     *
     * @param n
     * @return
     */
    public double[] twoSum(int n) {
        int[][] dp = new int[n][6 * n];
        int length = 5 * n + 1;
        double[] r = new double[length];
        double pow = Math.pow(6, n);
        for (int i = 0; i < n; i++) {
            for (int j = i; j < 6 * (i + 1); j++) {
                //初始化结果
                if (i == 0) {
                    dp[i][j] = 1;
                } else {
                    for (int k = 1; k <= 6; k++) {
                        if (j>=k) {
                            dp[i][j] += dp[i - 1][j - k];
                        }
                    }
                }
                //如果到最后一行，直接计算最终结果
                if (i == n - 1) {
                    r[j-i] = dp[i][j] / pow;
                }
            }
        }
        return r;
    }
}
