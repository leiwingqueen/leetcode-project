package com.liyongquan.weeklycontest.wc238;

/**
 * 在一座城市里，你需要建 n 栋新的建筑。这些新的建筑会从 1 到 n 编号排成一列。
 * <p>
 * 这座城市对这些新建筑有一些规定：
 * <p>
 * 每栋建筑的高度必须是一个非负整数。
 * 第一栋建筑的高度 必须 是 0 。
 * 任意两栋相邻建筑的高度差 不能超过  1 。
 * 除此以外，某些建筑还有额外的最高高度限制。这些限制会以二维整数数组 restrictions 的形式给出，其中 restrictions[i] = [idi, maxHeighti] ，表示建筑 idi 的高度 不能超过 maxHeighti 。
 * <p>
 * 题目保证每栋建筑在 restrictions 中 至多出现一次 ，同时建筑 1 不会 出现在 restrictions 中。
 * <p>
 * 请你返回 最高 建筑能达到的 最高高度 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：n = 5, restrictions = [[2,1],[4,1]]
 * 输出：2
 * 解释：上图中的绿色区域为每栋建筑被允许的最高高度。
 * 我们可以使建筑高度分别为 [0,1,2,1,2] ，最高建筑的高度为 2 。
 * 示例 2：
 * <p>
 * <p>
 * 输入：n = 6, restrictions = []
 * 输出：5
 * 解释：上图中的绿色区域为每栋建筑被允许的最高高度。
 * 我们可以使建筑高度分别为 [0,1,2,3,4,5] ，最高建筑的高度为 5 。
 * 示例 3：
 * <p>
 * <p>
 * 输入：n = 10, restrictions = [[5,3],[2,5],[7,4],[10,3]]
 * 输出：5
 * 解释：上图中的绿色区域为每栋建筑被允许的最高高度。
 * 我们可以使建筑高度分别为 [0,1,2,3,3,4,4,5,4,3] ，最高建筑的高度为 5 。
 *  
 * <p>
 * 提示：
 * <p>
 * 2 <= n <= 109
 * 0 <= restrictions.length <= min(n - 1, 105)
 * 2 <= idi <= n
 * idi 是 唯一的 。
 * 0 <= maxHeighti <= 109
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-building-height
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaxBuilding {
    /**
     * 从数据量上算法的时间复杂度只能锁定在O(log(n))
     *
     * @param n
     * @param restrictions
     * @return
     */
    public int maxBuilding(int n, int[][] restrictions) {
        int len = restrictions.length;
        int l = 0;
        int h1 = 0;
        for (int i = 0; i < len; i++) {
            int r = restrictions[i][0] - 1;
            int high = restrictions[i][1];
            int h2 = Math.min(h1 + r - l, high);
            //TODO:计算[l,r]之间的最大高度
        }
        return 0;
    }
}
