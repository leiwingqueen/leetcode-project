package com.liyongquan.dp;

/**
 * 887. 鸡蛋掉落
 * <p>
 * 给你 k 枚相同的鸡蛋，并可以使用一栋从第 1 层到第 n 层共有 n 层楼的建筑。
 * <p>
 * 已知存在楼层 f ，满足 0 <= f <= n ，任何从 高于 f 的楼层落下的鸡蛋都会碎，从 f 楼层或比它低的楼层落下的鸡蛋都不会破。
 * <p>
 * 每次操作，你可以取一枚没有碎的鸡蛋并把它从任一楼层 x 扔下（满足 1 <= x <= n）。如果鸡蛋碎了，你就不能再次使用它。如果某枚鸡蛋扔下后没有摔碎，则可以在之后的操作中 重复使用 这枚鸡蛋。
 * <p>
 * 请你计算并返回要确定 f 确切的值 的 最小操作次数 是多少？
 * <p>
 *  
 * 示例 1：
 * <p>
 * 输入：k = 1, n = 2
 * 输出：2
 * 解释：
 * 鸡蛋从 1 楼掉落。如果它碎了，肯定能得出 f = 0 。
 * 否则，鸡蛋从 2 楼掉落。如果它碎了，肯定能得出 f = 1 。
 * 如果它没碎，那么肯定能得出 f = 2 。
 * 因此，在最坏的情况下我们需要移动 2 次以确定 f 是多少。
 * 示例 2：
 * <p>
 * 输入：k = 2, n = 6
 * 输出：3
 * 示例 3：
 * <p>
 * 输入：k = 3, n = 14
 * 输出：4
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= k <= 100
 * 1 <= n <= 104
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/super-egg-drop
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SuperEggDrop {
    /**
     * dp方程错误
     *
     * @param k
     * @param n
     * @return
     */
    public int superEggDrop(int k, int n) {
        int[][] dp = new int[k][n + 1];
        //初始化
        for (int i = 1; i <= n; i++) {
            dp[0][i] = i;
        }
        for (int i = 0; i < k; i++) {
            dp[i][1] = 1;
        }
        for (int i = 1; i < k; i++) {
            for (int j = 2; j <= n; j++) {
                if (j % 2 == 0) {
                    dp[i][j] = dp[i - 1][j / 2] + 1;
                } else {
                    dp[i][j] = dp[i - 1][j / 2 + 1] + 1;
                }
            }
        }
        return dp[k - 1][n];
    }

    /**
     * 时间复杂度O(k*n^2)
     * <p>
     * 超时
     *
     * @param k
     * @param n
     * @return
     */
    public int superEggDrop2(int k, int n) {
        int[][] dp = new int[k][n + 1];
        //初始化
        for (int i = 1; i <= n; i++) {
            dp[0][i] = i;
        }
        for (int i = 0; i < k; i++) {
            dp[i][1] = 1;
        }
        for (int i = 1; i < k; i++) {
            for (int j = 1; j <= n; j++) {
                int min = Integer.MAX_VALUE;
                for (int l = 1; l <= j; l++) {
                    min = Math.min(Math.max(dp[i][j - l], dp[i - 1][l - 1]), min);
                }
                dp[i][j] = min + 1;
            }
        }
        return dp[k - 1][n];
    }

    /**
     * 增加二分查找进行优化
     *
     * @param k
     * @param n
     * @return
     */
    public int superEggDrop3(int k, int n) {
        int[][] dp = new int[k][n + 1];
        //初始化
        for (int i = 1; i <= n; i++) {
            dp[0][i] = i;
        }
        for (int i = 0; i < k; i++) {
            dp[i][1] = 1;
        }
        for (int i = 1; i < k; i++) {
            for (int j = 1; j <= n; j++) {
                //dp[i - 1][mid - 1] - dp[i][j - mid] 单调递增
                int l = 1, r = j;
                while (l < r) {
                    int mid = l + (r - l) / 2;
                    int sub = dp[i - 1][mid - 1] - dp[i][j - mid];
                    if (sub >= 0) {
                        r = mid;
                    } else {
                        l = mid + 1;
                    }
                }
                //没有交点的场景
                if (l == 1) {
                    dp[i][j] = Math.max(dp[i - 1][l - 1], dp[i][j - l]) + 1;
                } else {
                    int s1 = Math.max(dp[i - 1][l - 1], dp[i][j - l]);
                    int s2 = Math.max(dp[i - 1][l - 2], dp[i][j - l + 1]);
                    dp[i][j] = Math.min(s1, s2) + 1;
                }
            }
        }
        return dp[k - 1][n];
    }
}
