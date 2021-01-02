package com.liyongquan.dp;

import lombok.extern.slf4j.Slf4j;

/**
 * 我们正在玩一个猜数游戏，游戏规则如下：
 * <p>
 * 我从 1 到 n 之间选择一个数字，你来猜我选了哪个数字。
 * <p>
 * 每次你猜错了，我都会告诉你，我选的数字比你的大了或者小了。
 * <p>
 * 然而，当你猜了数字 x 并且猜错了的时候，你需要支付金额为 x 的现金。直到你猜到我选的数字，你才算赢得了这个游戏。
 * <p>
 * 示例:
 * <p>
 * n = 10, 我选择了8.
 * <p>
 * 第一轮: 你猜我选择的数字是5，我会告诉你，我的数字更大一些，然后你需要支付5块。
 * 第二轮: 你猜是7，我告诉你，我的数字更大一些，你支付7块。
 * 第三轮: 你猜是9，我告诉你，我的数字更小一些，你支付9块。
 * <p>
 * 游戏结束。8 就是我选的数字。
 * <p>
 * 你最终要支付 5 + 7 + 9 = 21 块钱。
 * 给定 n ≥ 1，计算你至少需要拥有多少现金才能确保你能赢得这个游戏。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/guess-number-higher-or-lower-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
@Slf4j
public class GuessNumber2 {
    /**
     * 贪心算法
     * <p>
     * 不通过
     *
     * @param n
     * @return
     */
    public int getMoneyAmount(int n) {
        if (n == 1) {
            return 0;
        }
        int sum = 0;
        int start = 1, end = n;
        while (start < end) {
            int middle = start + (end - start) / 2;
            log.info("{}", middle);
            sum += middle;
            start = middle + 1;
        }
        return sum;
    }

    /**
     * 动态规划。
     * <p>
     * 最坏的情况是用户选的是最后一个数字，我们最后一次选择应该是n-1，这样就能确保我们能选中最后一个数字
     * <p>
     * dp表达式：
     * f(n)=f(n-2)+n-1
     * <p>
     * 初始化：
     * f(1)=0;
     * f(2)=1;
     * <p>
     * 不通过
     *
     * @param n
     * @return
     */
    public int getMoneyAmount2(int n) {
        if (n == 1) {
            return 0;
        }
        if (n == 2) {
            return 1;
        }
        int f1 = 0, f2 = 1;
        for (int i = 3; i <= n; i++) {
            log.info("f1:{},f2:{}", f1, f2);
            int tmp = f1 + n - 1;
            f1 = f2;
            f2 = tmp;
        }
        return f2;
    }

    /**
     * 暴力解法
     *
     * @param n
     * @return
     */
    public int getMoneyAmount3(int n) {
        return recursive(1, n);
    }

    private int recursive(int start, int end) {
        if (end <= start) {
            return 0;
        }
        int res = Integer.MAX_VALUE;
        for (int i = (start + end) / 2; i <= end; i++) {
            //左半边
            int left = recursive(start, i - 1);
            int right = recursive(i + 1, end);
            res = Math.min(Math.max(left, right) + i, res);
        }
        return res;
    }


    /**
     * 在暴力解法的基础上稍微加一些剪枝
     * <p>
     * f(s,e)=min{f(s,i-1),f(i+1,e)+i} (s<=i<=e)
     * <p>
     * dp解法
     *
     * @param n
     * @return
     */
    public int getMoneyAmount4(int n) {
        if (n <= 1) {
            return 1;
        }
        //初始化(为了兼容边界情况，空间上多使用两行)
        int[][] dp = new int[n + 2][n + 2];
        //dp表达式
        for (int end = 2; end <= n; end++) {
            for (int start = 1; start < end; start++) {
                //计算最小值
                int min = Integer.MAX_VALUE;
                for (int i = (start + end) / 2; i <= end; i++) {
                    int tmp = Math.max(dp[start][i - 1], dp[i + 1][end]) + i;
                    min = Math.min(tmp, min);
                }
                log.info("dp[{}][{}]={}", start, end, min);
                dp[start][end] = min;
            }
        }
        return dp[1][n];
    }
}
