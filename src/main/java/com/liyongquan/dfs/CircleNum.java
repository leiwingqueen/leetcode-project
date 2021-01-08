package com.liyongquan.dfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
 * <p>
 * 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
 * <p>
 * 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。
 * <p>
 * 返回矩阵中 省份 的数量。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：isConnected = [[1,1,0],[1,1,0],[0,0,1]]
 * 输出：2
 * 示例 2：
 * <p>
 * <p>
 * 输入：isConnected = [[1,0,0],[0,1,0],[0,0,1]]
 * 输出：3
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 200
 * n == isConnected.length
 * n == isConnected[i].length
 * isConnected[i][j] 为 1 或 0
 * isConnected[i][i] == 1
 * isConnected[i][j] == isConnected[j][i]
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-provinces
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CircleNum {
    /**
     * bfs和dfs都可以，这里用bfs
     * <p>
     * 随便选择一个没有访问的节点，查看这个结点联通的所有的节点(bfs)。
     * <p>
     * 如果还有没有访问的节点，重复上面的步骤
     *
     * @param isConnected
     * @return
     */
    public int findCircleNum(int[][] isConnected) {
        if (isConnected.length == 0) {
            return 0;
        }
        int len = isConnected.length;
        int[] visit = new int[len];
        int count = 0;
        //遍历每个结点
        for (int i = 0; i < len; i++) {
            Queue<Integer> queue = new LinkedList<>();
            if (visit[i] == 1) {
                continue;
            }
            visit[i] = 1;
            queue.add(i);
            count++;
            //针对每个结点做bfs
            while (queue.size() > 0) {
                Integer poll = queue.poll();
                for (int j = 0; j < len; j++) {
                    if (isConnected[poll][j] == 1 && visit[j] == 0) {
                        visit[j] = 1;
                        queue.add(j);
                    }
                }
            }
        }
        return count;
    }
}
