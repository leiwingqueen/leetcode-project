package com.liyongquan.dp;

import com.liyongquan.dfs.Kuohao;

import java.util.Arrays;

/**
 * 你将会获得一系列视频片段，这些片段来自于一项持续时长为 T 秒的体育赛事。这些片段可能有所重叠，也可能长度不一。
 * <p>
 * 视频片段 clips[i] 都用区间进行表示：开始于 clips[i][0] 并于 clips[i][1] 结束。我们甚至可以对这些片段自由地再剪辑，例如片段 [0, 7] 可以剪切成 [0, 1] + [1, 3] + [3, 7] 三部分。
 * <p>
 * 我们需要将这些片段进行再剪辑，并将剪辑后的内容拼接成覆盖整个运动过程的片段（[0, T]）。返回所需片段的最小数目，如果无法完成该任务，则返回 -1 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：clips = [[0,2],[4,6],[8,10],[1,9],[1,5],[5,9]], T = 10
 * 输出：3
 * 解释：
 * 我们选中 [0,2], [8,10], [1,9] 这三个片段。
 * 然后，按下面的方案重制比赛片段：
 * 将 [1,9] 再剪辑为 [1,2] + [2,8] + [8,9] 。
 * 现在我们手上有 [0,2] + [2,8] + [8,10]，而这些涵盖了整场比赛 [0, 10]。
 * 示例 2：
 * <p>
 * 输入：clips = [[0,1],[1,2]], T = 5
 * 输出：-1
 * 解释：
 * 我们无法只用 [0,1] 和 [1,2] 覆盖 [0,5] 的整个过程。
 * 示例 3：
 * <p>
 * 输入：clips = [[0,1],[6,8],[0,2],[5,6],[0,4],[0,3],[6,7],[1,3],[4,7],[1,4],[2,5],[2,6],[3,4],[4,5],[5,7],[6,9]], T = 9
 * 输出：3
 * 解释：
 * 我们选取片段 [0,4], [4,7] 和 [6,9] 。
 * 示例 4：
 * <p>
 * 输入：clips = [[0,4],[2,8]], T = 5
 * 输出：2
 * 解释：
 * 注意，你可能录制超过比赛结束时间的视频。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= clips.length <= 100
 * 0 <= clips[i][0] <= clips[i][1] <= 100
 * 0 <= T <= 100
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/video-stitching
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class VideoStitching {
    /**
     * dp解法
     * <p>
     * 我们定义f(k,m,n)为前k个元素拼接成[m,n]的最少视频数量。
     * <p>
     * 则我们有dp表达式：
     * f(k,m,n)=min{f(k-1,m,n),f(k-1,m,clips[k][0])+f(k-1,clips[k][1],n)+1}
     * <p>
     * 解释：
     * 类似01背包问题，对于第k个视频，我们可以选择/不选
     * 1.不选。那么相当于直接用前k-1个视频进行剪辑
     * 2.选。则我们选择用前k-1个视频分别拼接[m,clips[k][0]]和[clips[k][1],n]。
     * 这里有个以疑问点，第二个选项中的视频是否有可能有重叠？我们假设有一个视频A同时存在于两个区间，
     * 那么这时候证明我们没必要选第k个视频，因为A视频的左右边界必然大于k视频。
     * <p>
     * 初始化：
     * k=1的场景进行初始化即可。
     * <p>
     * 时间复杂度O(k*n^2)，k视频数量，n为视频时长
     *
     * @param clips
     * @param T
     * @return
     */
    public int videoStitching(int[][] clips, int T) {
        if (clips.length <= 0 || T == 0) {
            return 0;
        }
        if (clips.length == 1) {
            return clips[0][0] == 0 && clips[0][1] >= T ? 1 : -1;
        }
        //初始化
        int[][][] dp = new int[clips.length][T][T + 1];
        for (int i = 0; i < T; i++) {
            for (int j = i + 1; j <= T; j++) {
                dp[0][i][j] = clips[0][0] == 0 && clips[0][1] >= j ? 1 : -1;
            }
        }
        //dp迭代
        for (int i = 1; i < clips.length; i++) {
            for (int j = 0; j < T; j++) {
                for (int k = j + 1; k <= T; k++) {
                    transfer(dp, i, j, k, clips);
                }
            }
        }
        return dp[clips.length - 1][0][T];
    }

    private void transfer(int[][][] dp, int i, int j, int k, int[][] clips) {
        if (clips[i][0] <= j && clips[i][1] >= k) {
            dp[i][j][k] = 1;
            return;
        }
        if ((clips[i][0] > j && dp[i - 1][j][clips[i][0]] == -1) || (clips[i][1] < k && dp[i - 1][clips[i][1]][k] == -1)) {
            dp[i][j][k] = -1;
            return;
        }
        int r = 1;
        if (clips[i][0] > j) {
            r += dp[i - 1][j][clips[i][0]];
        }
        if (clips[i][1] < k) {
            r += dp[i - 1][clips[i][1]][k];
        }
        dp[i][j][k] = dp[i - 1][j][k] == -1 ? r : Math.min(dp[i - 1][j][k], r);
    }


    /**
     * 官方解法
     *
     * @param clips
     * @param T
     * @return
     */
    public int videoStitching2(int[][] clips, int T) {
        if (T == 0) {
            return 0;
        }
        //初始化
        int[] dp = new int[T + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for (int i = 1; i <= T; i++) {
            for (int j = 0; j < clips.length; j++) {
                //clips[j]一个剪辑能够覆盖
                if (clips[j][0] == 0 && clips[j][1] >= i) {
                    dp[i] = 1;
                    break;
                }
                //能够覆盖i的尾部
                if (clips[j][0] < i && clips[j][1] >= i && dp[clips[j][0]] != -1) {
                    dp[i] = dp[i] == -1 ? dp[clips[j][0]] + 1 : Math.min(dp[i], dp[clips[j][0]] + 1);
                }
            }
        }
        return dp[T];
    }
}
