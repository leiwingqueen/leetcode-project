package com.liyongquan.unionfind;

import java.util.Currency;

/**
 * n 块石头放置在二维平面中的一些整数坐标点上。每个坐标点上最多只能有一块石头。
 * <p>
 * 如果一块石头的 同行或者同列 上有其他石头存在，那么就可以移除这块石头。
 * <p>
 * 给你一个长度为 n 的数组 stones ，其中 stones[i] = [xi, yi] 表示第 i 块石头的位置，返回 可以移除的石子 的最大数量。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
 * 输出：5
 * 解释：一种移除 5 块石头的方法如下所示：
 * 1. 移除石头 [2,2] ，因为它和 [2,1] 同行。
 * 2. 移除石头 [2,1] ，因为它和 [0,1] 同列。
 * 3. 移除石头 [1,2] ，因为它和 [1,0] 同行。
 * 4. 移除石头 [1,0] ，因为它和 [0,0] 同列。
 * 5. 移除石头 [0,1] ，因为它和 [0,0] 同行。
 * 石头 [0,0] 不能移除，因为它没有与另一块石头同行/列。
 * 示例 2：
 * <p>
 * 输入：stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
 * 输出：3
 * 解释：一种移除 3 块石头的方法如下所示：
 * 1. 移除石头 [2,2] ，因为它和 [2,0] 同行。
 * 2. 移除石头 [2,0] ，因为它和 [0,0] 同列。
 * 3. 移除石头 [0,2] ，因为它和 [0,0] 同行。
 * 石头 [0,0] 和 [1,1] 不能移除，因为它们没有与另一块石头同行/列。
 * 示例 3：
 * <p>
 * 输入：stones = [[0,0]]
 * 输出：0
 * 解释：[0,0] 是平面上唯一一块石头，所以不可以移除它。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= stones.length <= 1000
 * 0 <= xi, yi <= 104
 * 不会有两块石头放在同一个坐标点上
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/most-stones-removed-with-same-row-or-column
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RemoveStones {
    /**
     * 本质上还是求连通图的数量。我们可以这么理解，对于一个连通图，最多剩下的就是一个节点。
     * 因此最多删除的数量为n-c，n为节点数量,c为连通图数量
     *
     * @param stones
     * @return
     */
    public int removeStones(int[][] stones) {
        //构建无向图
        int len = stones.length;
        int[][] conn = new int[len][len];
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                conn[i][j] = stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1] ? 1 : 0;
                //对角位置同时赋值
                conn[j][i] = conn[i][j];
            }
        }
        int count = 0;
        int[] visit = new int[len];
        for (int i = 0; i < len; i++) {
            if (visit[i] == 0) {
                count++;
                dfs(conn, len, i, visit);
            }
        }
        return len - count;
    }

    /**
     * 这里用bfs和dfs均可
     *
     * @return
     */
    private void dfs(int[][] conn, int len, int cur, int[] visit) {
        visit[cur] = 1;
        for (int i = 0; i < len; i++) {
            if (visit[i] == 0 && conn[cur][i] == 1) {
                dfs(conn, len, i, visit);
            }
        }
    }
}
