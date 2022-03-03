package com.liyongquan.weeklycontest.bwc70;

//在一个图书馆的长廊里，有一些座位和装饰植物排成一列。给你一个下标从 0 开始，长度为 n 的字符串 corridor ，它包含字母 'S' 和 'P' ，其中每个 'S' 表示一个座位，每个 'P' 表示一株植物。
//
//在下标 0 的左边和下标 n - 1 的右边 已经 分别各放了一个屏风。你还需要额外放置一些屏风。每一个位置 i - 1 和 i 之间（1 <= i <= n - 1），至多能放一个屏风。
//
//请你将走廊用屏风划分为若干段，且每一段内都 恰好有两个座位 ，而每一段内植物的数目没有要求。可能有多种划分方案，如果两个方案中有任何一个屏风的位置不同，那么它们被视为 不同 方案。
//
//请你返回划分走廊的方案数。由于答案可能很大，请你返回它对 109 + 7 取余 的结果。如果没有任何方案，请返回 0 。
//
// 
//
//示例 1：
//
//
//
//输入：corridor = "SSPPSPS"
//输出：3
//解释：总共有 3 种不同分隔走廊的方案。
//上图中黑色的竖线表示已经放置好的屏风。
//上图每种方案中，每一段都恰好有 两个 座位。
//示例 2：
//
//
//
//输入：corridor = "PPSPSP"
//输出：1
//解释：只有 1 种分隔走廊的方案，就是不放置任何屏风。
//放置任何的屏风都会导致有一段无法恰好有 2 个座位。
//示例 3：
//
//
//
//输入：corridor = "S"
//输出：0
//解释：没有任何方案，因为总是有一段无法恰好有 2 个座位。
// 
//
//提示：
//
//n == corridor.length
//1 <= n <= 105
//corridor[i] 要么是 'S' ，要么是 'P' 。
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/number-of-ways-to-divide-a-long-corridor
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

public class NumberOfWays {
    /**
     * 这个DP理解起来有点绕，但是总算做出来了
     *
     * @param corridor
     * @return
     */
    public int numberOfWays(String corridor) {
        int mod = 1_000_000_007;
        int n = corridor.length();
        int[] dp0 = new int[n],
                dp1 = new int[n],
                dp2 = new int[n];
        if (corridor.charAt(0) == 'S') {
            dp0[0] = 0;
            dp1[0] = 1;
            dp2[0] = 0;
        } else {
            dp0[0] = 1;
            dp1[0] = 0;
            dp2[0] = 0;
        }
        //dp迭代
        for (int i = 1; i < n; i++) {
            if (corridor.charAt(i) == 'S') {
                dp2[i] = dp1[i - 1];
                dp1[i] = dp0[i - 1];
                dp0[i] = dp1[i - 1];
            } else {
                dp2[i] = dp2[i - 1];
                dp1[i] = dp1[i - 1];
                dp0[i] = (dp2[i] + dp0[i - 1]) % mod;
            }
        }
        return dp2[n - 1];
    }


    /**
     * 空间优化
     *
     * @param corridor
     * @return
     */
    public int numberOfWay2(String corridor) {
        int mod = 1_000_000_007;
        int n = corridor.length();
        int dp0 = 0, dp1 = 0, dp2 = 0;
        if (corridor.charAt(0) == 'S') {
            dp1 = 1;
        } else {
            dp0 = 1;
        }
        //dp迭代
        for (int i = 1; i < n; i++) {
            int ndp2, ndp1, ndp0;
            if (corridor.charAt(i) == 'S') {
                ndp2 = dp1;
                ndp1 = dp0;
                ndp0 = dp1;
            } else {
                ndp2 = dp2;
                ndp1 = dp1;
                ndp0 = (dp2 + dp0) % mod;
            }
            dp2 = ndp2;
            dp1 = ndp1;
            dp0 = ndp0;
        }
        return dp2;
    }

    //TODO:还有一种解法，每两个座位分组。然后计算中间的植物数量
}
