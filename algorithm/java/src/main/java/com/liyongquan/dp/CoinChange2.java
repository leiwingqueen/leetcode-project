package com.liyongquan.dp;

/**
 * 给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。 
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: amount = 5, coins = [1, 2, 5]
 * 输出: 4
 * 解释: 有四种方式可以凑成总金额:
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 * 示例 2:
 * <p>
 * 输入: amount = 3, coins = [2]
 * 输出: 0
 * 解释: 只用面额2的硬币不能凑成总金额3。
 * 示例 3:
 * <p>
 * 输入: amount = 10, coins = [10]
 * 输出: 1
 *  
 * <p>
 * 注意:
 * <p>
 * 你可以假设：
 * <p>
 * 0 <= amount (总金额) <= 5000
 * 1 <= coin (硬币面额) <= 5000
 * 硬币种类不超过 500 种
 * 结果符合 32 位符号整数
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/coin-change-2
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CoinChange2 {
    /**
     * https://leetcode-cn.com/problems/coin-change-2/solution/ling-qian-dui-huan-iihe-pa-lou-ti-wen-ti-dao-di-yo/
     * <p>
     * 这个问题的难点在于我们要求解的是组合数，并不是排序数。如果构造dp方程把重复的解过滤？
     * <p>
     * --原文解释，再深入咀嚼一下
     * <p>
     * 但是当你运行之后，却发现这个代码并不正确，得到的结果比预期的大。究其原因，该代码计算的结果是排列数，而不是组合数，也就是代码会把1,2和2,1当做两种情况。但更加根本的原因是我们子问题定义出现了错误。
     * <p>
     * 正确的子问题定义应该是，problem(k,i) = problem(k-1, i) + problem(k, i-k)
     * <p>
     * 即前k个硬币凑齐金额i的组合数等于前k-1个硬币凑齐金额i的组合数加上在原来i-k的基础上使用硬币的组合数。说的更加直白一点，那就是用前k的硬币凑齐金额i，要分为两种情况开率，一种是没有用前k-1个硬币就凑齐了，一种是前面已经凑到了i-k，现在就差第k个硬币了。
     * <p>
     * 作者：xu-zhou-geng
     * 链接：https://leetcode-cn.com/problems/coin-change-2/solution/ling-qian-dui-huan-iihe-pa-lou-ti-wen-ti-dao-di-yo/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param amount
     * @param coins
     * @return
     */
    public int change(int amount, int[] coins) {
        if (amount == 0) {
            return 1;
        }
        int len = coins.length;
        int[][] dp = new int[amount + 1][len];
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < len; j++) {
                if (i - coins[j] == 0) {
                    dp[i][j] = 1;
                } else if (i - coins[j] > 0) {
                    for (int k = 0; k <= j; k++) {
                        dp[i][j] += dp[i - coins[j]][k];
                    }
                }
            }
        }
        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += dp[amount][i];
        }
        return sum;
    }
}
