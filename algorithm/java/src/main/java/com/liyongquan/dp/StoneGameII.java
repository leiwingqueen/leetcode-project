package com.liyongquan.dp;

// 爱丽丝和鲍勃继续他们的石子游戏。许多堆石子 排成一行，每堆都有正整数颗石子 piles[i]。游戏以谁手中的石子最多来决出胜负。
//
//爱丽丝和鲍勃轮流进行，爱丽丝先开始。最初，M = 1。
//
//在每个玩家的回合中，该玩家可以拿走剩下的 前 X 堆的所有石子，其中 1 <= X <= 2M。然后，令 M = max(M, X)。
//
//游戏一直持续到所有石子都被拿走。
//
//假设爱丽丝和鲍勃都发挥出最佳水平，返回爱丽丝可以得到的最大数量的石头。
//
// 
//
//示例 1：
//
//输入：piles = [2,7,9,4,4]
//输出：10
//解释：如果一开始Alice取了一堆，Bob取了两堆，然后Alice再取两堆。爱丽丝可以得到2 + 4 + 4 = 10堆。如果Alice一开始拿走了两堆，那么Bob可以拿走剩下的三堆。在这种情况下，Alice得到2 + 7 = 9堆。返回10，因为它更大。
//示例 2:
//
//输入：piles = [1,2,3,4,5,100]
//输出：104
// 
//
//提示：
//
//1 <= piles.length <= 100
//1 <= piles[i] <= 104
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/stone-game-ii
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

public class StoneGameII {
    // 回溯解法，超时
    public int stoneGameII(int[] piles) {
        int[] res = dfs(piles, 0, 1);
        return res[0];
    }

    // 从start开始，m的值
    private int[] dfs(int[] arr, int start, int m) {
        if (start == arr.length) {
            return new int[]{0, 0};
        }
        if (start + 2 * m >= arr.length) {
            int s = 0;
            for (int i = start; i < arr.length; i++) {
                s += arr[i];
            }
            return new int[]{s, 0};
        }
        int d = Math.min(arr.length - start, 2 * m);
        int mx = 0;
        int[] res = {0, 0};
        int sum = 0;
        for (int i = 1; i <= d; i++) {
            sum += arr[start + i - 1];
            int[] sub = dfs(arr, start + i, Math.max(i, m));
            int s = sub[1] + sum;
            if (s > mx) {
                mx = s;
                res[0] = s;
                res[1] = sub[0];
            }
        }
        return res;
    }

    // dp，既然能用回溯写，必然能改成DP，>_<。从上面的回溯翻译过来
    public int stoneGameII2(int[] piles) {
        int n = piles.length;
        int[] preSum = new int[n + 1];
        // 后缀和，为了简化后面的统计一段范围的石子总和
        for (int i = 0; i < n; i++) {
            preSum[i + 1] = preSum[i] + piles[n - i - 1];
        }
        // f(n,m,k)，n为后n堆石子，m是选择的范围因子M，k=0为先手，=1为后手分别取得的最大值
        int[][][] dp = new int[n][n][2];
        for (int i = 0; i < n; i++) {
            dp[0][i][0] = piles[n - 1];
            dp[0][i][1] = 0;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 后i+1个元素,m为j+1选择，既然能一次性取完就直接取完
                if (2 * (j + 1) >= i + 1) {
                    dp[i][j][0] = preSum[i + 1];
                    dp[i][j][1] = 0;
                } else {
                    // 否则遍历[1,2*(j+1)]的范围选最优值
                    for (int k = 1; k <= 2 * (j + 1); k++) {
                        // 下一次取的m值
                        int j1 = Math.max(k - 1, j);
                        int sub = dp[i - k][j1][1] + preSum[i + 1] - preSum[i + 1 - k];
                        if (sub > dp[i][j][0]) {
                            dp[i][j][0] = sub;
                            dp[i][j][1] = dp[i - k][j1][0];
                        }
                    }
                }
            }
        }
        return dp[n - 1][0][0];
    }
}
