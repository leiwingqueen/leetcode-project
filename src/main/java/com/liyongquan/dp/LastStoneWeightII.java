package com.liyongquan.dp;

/**
 * 1049. 最后一块石头的重量 II
 * <p>
 * 有一堆石头，用整数数组 stones 表示。其中 stones[i] 表示第 i 块石头的重量。
 * <p>
 * 每一回合，从中选出任意两块石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
 * <p>
 * 如果 x == y，那么两块石头都会被完全粉碎；
 * 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
 * 最后，最多只会剩下一块 石头。返回此石头 最小的可能重量 。如果没有石头剩下，就返回 0。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：stones = [2,7,4,1,8,1]
 * 输出：1
 * 解释：
 * 组合 2 和 4，得到 2，所以数组转化为 [2,7,1,8,1]，
 * 组合 7 和 8，得到 1，所以数组转化为 [2,1,1,1]，
 * 组合 2 和 1，得到 1，所以数组转化为 [1,1,1]，
 * 组合 1 和 1，得到 0，所以数组转化为 [1]，这就是最优值。
 * 示例 2：
 * <p>
 * 输入：stones = [31,26,33,21,40]
 * 输出：5
 * 示例 3：
 * <p>
 * 输入：stones = [1,2]
 * 输出：1
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= stones.length <= 30
 * 1 <= stones[i] <= 100
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/last-stone-weight-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LastStoneWeightII {
    /**
     * 回溯解法
     *
     * 超时
     *
     * @param stones
     * @return
     */
    public int lastStoneWeightII(int[] stones) {
        return backtrace(stones, 0, stones.length, 0);
    }

    private int backtrace(int[] stones, int idx, int len, int target) {
        if (idx == len) {
            return Math.abs(target);
        }
        if (idx == len - 1) {
            return Math.abs(stones[len - 1] - target);
        }
        int min = Integer.MAX_VALUE;
        for (int i = idx; i < len; i++) {
            swap(stones, i, idx);
            int r1 = backtrace(stones, idx + 1, len, target + stones[idx]);
            int r2 = backtrace(stones, idx + 1, len, Math.abs(target - stones[idx]));
            min = Math.min(Math.min(r1, r2), min);
            //还原现场
            swap(stones, i, idx);
        }
        return min;
    }

    private void swap(int[] stones, int i, int j) {
        int tmp = stones[i];
        stones[i] = stones[j];
        stones[j] = tmp;
    }
}
