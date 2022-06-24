package com.liyongquan.dp;

/**
 * 877. 石子游戏
 * <p>
 * <p>
 * 亚历克斯和李用几堆石子在做游戏。偶数堆石子排成一行，每堆都有正整数颗石子 piles[i] 。
 * <p>
 * 游戏以谁手中的石子最多来决出胜负。石子的总数是奇数，所以没有平局。
 * <p>
 * 亚历克斯和李轮流进行，亚历克斯先开始。 每回合，玩家从行的开始或结束处取走整堆石头。 这种情况一直持续到没有更多的石子堆为止，此时手中石子最多的玩家获胜。
 * <p>
 * 假设亚历克斯和李都发挥出最佳水平，当亚历克斯赢得比赛时返回 true ，当李赢得比赛时返回 false 。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：[5,3,4,5]
 * 输出：true
 * 解释：
 * 亚历克斯先开始，只能拿前 5 颗或后 5 颗石子 。
 * 假设他取了前 5 颗，这一行就变成了 [3,4,5] 。
 * 如果李拿走前 3 颗，那么剩下的是 [4,5]，亚历克斯拿走后 5 颗赢得 10 分。
 * 如果李拿走后 5 颗，那么剩下的是 [3,4]，亚历克斯拿走后 4 颗赢得 9 分。
 * 这表明，取前 5 颗石子对亚历克斯来说是一个胜利的举动，所以我们返回 true 。
 *  
 * <p>
 * 提示：
 * <p>
 * 2 <= piles.length <= 500
 * piles.length 是偶数。
 * 1 <= piles[i] <= 500
 * sum(piles) 是奇数。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/stone-game
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class StoneGame {
    /**
     * 递归解法
     * <p>
     * 超时
     *
     * @param piles
     * @return
     */
    public boolean stoneGame(int[] piles) {
        return dfs(piles, 0, piles.length - 1) > 0;
    }

    private int dfs(int[] piles, int start, int end) {
        if (start == end) {
            return piles[start];
        }
        //取第一个
        int first = piles[start] - dfs(piles, start + 1, end);
        //最后一个
        int last = piles[end] - dfs(piles, start, end - 1);
        return Math.max(first, last);
    }


    /**
     * dp解法
     * <p>
     * f(i,j)=max{p[i]-f(i+1,j),p[j]-f(i,j-1)}
     *
     * @param piles
     * @return
     */
    public boolean stoneGame2(int[] piles) {
        int len = piles.length;
        int[][] dp = new int[len][len];
        //初始化对角线
        for (int i = 0; i < len; i++) {
            dp[i][i] = piles[i];
        }
        //dp迭代
        for (int i = 1; i < len; i++) {
            int row = 0;
            int col = i;
            while (col < len) {
                dp[row][col] = Math.max(piles[row] - dp[row + 1][col], piles[col] - dp[row][col - 1]);
                row++;
                col++;
            }
        }
        return dp[0][len - 1] > 0;
    }

}
