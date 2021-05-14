package com.liyongquan.dp;

/**
 * 1269. 停在原地的方案数
 * <p>
 * 有一个长度为 arrLen 的数组，开始有一个指针在索引 0 处。
 * <p>
 * 每一步操作中，你可以将指针向左或向右移动 1 步，或者停在原地（指针不能被移动到数组范围外）。
 * <p>
 * 给你两个整数 steps 和 arrLen ，请你计算并返回：在恰好执行 steps 次操作以后，指针仍然指向索引 0 处的方案数。
 * <p>
 * 由于答案可能会很大，请返回方案数 模 10^9 + 7 后的结果。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：steps = 3, arrLen = 2
 * 输出：4
 * 解释：3 步后，总共有 4 种不同的方法可以停在索引 0 处。
 * 向右，向左，不动
 * 不动，向右，向左
 * 向右，不动，向左
 * 不动，不动，不动
 * 示例  2：
 * <p>
 * 输入：steps = 2, arrLen = 4
 * 输出：2
 * 解释：2 步后，总共有 2 种不同的方法可以停在索引 0 处。
 * 向右，向左
 * 不动，不动
 * 示例 3：
 * <p>
 * 输入：steps = 4, arrLen = 2
 * 输出：8
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= steps <= 500
 * 1 <= arrLen <= 10^6
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-ways-to-stay-in-the-same-place-after-some-steps
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NumWaysStep {
    /**
     * dp解法
     * <p>
     * s-移动步数,n-数组长度
     * 时间复杂度O(s*n^2)
     * 空间复杂度O(s*n^2)
     * <p>
     * 必然超时
     *
     * @param steps
     * @param arrLen
     * @return
     */
    public int numWays(int steps, int arrLen) {
        int mod = 1000000007;
        int[][][] dp = new int[steps][arrLen][arrLen];
        //初始化
        for (int i = 0; i < arrLen; i++) {
            for (int j = 0; j < arrLen; j++) {
                dp[0][i][j] = Math.abs(i - j) <= 1 ? 1 : 0;
            }
        }
        //dp迭代
        for (int i = 1; i < steps; i++) {
            for (int j = 0; j < arrLen; j++) {
                for (int k = 0; k < arrLen; k++) {
                    long sum = 0;
                    sum += dp[i - 1][j][k];
                    if (j < arrLen - 1) {
                        sum += dp[i - 1][j + 1][k];
                    }
                    if (j > 0) {
                        sum += dp[i - 1][j - 1][k];
                    }
                    dp[i][j][k] = (int) (sum % mod);
                }
            }
        }
        return dp[steps - 1][0][0];
    }
}
