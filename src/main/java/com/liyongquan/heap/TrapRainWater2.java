package com.liyongquan.heap;

import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 给你一个 m x n 的矩阵，其中的值均为非负整数，代表二维高度图每个单元的高度，请计算图中形状最多能接多少体积的雨水。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 给出如下 3x6 的高度图:
 * [
 * [1,4,3,1,3,2],
 * [3,2,1,3,2,4],
 * [2,3,3,2,3,1]
 * ]
 * <p>
 * 返回 4 。
 * <p>
 * <p>
 * 如上图所示，这是下雨前的高度图[[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]] 的状态。
 * <p>
 *  
 * <p>
 * <p>
 * <p>
 * 下雨后，雨水将会被存储在这些方块中。总的接雨水量是4。
 * <p>
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= m, n <= 110
 * 0 <= heightMap[i][j] <= 20000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/trapping-rain-water-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
@Slf4j
public class TrapRainWater2 {
    public static final int[][] DIR = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1},
            //8个方向
            //{1, 1},
            //{-1, -1},
            //{-1, 1},
            //{1, -1}
    };

    /**
     * 暴力解法，这个跟上一题唯一的区别是由二维变成三维。我们可以用bfs找到边界的最高点
     * <p>
     * 难，这个不能通过
     *
     * @param heightMap
     * @return
     */
    public int trapRainWater(int[][] heightMap) {
        if (heightMap.length == 0) {
            return 0;
        }
        int row = heightMap.length, col = heightMap[0].length;
        int water = 0;
        for (int i = 1; i < row - 1; i++) {
            for (int j = 1; j < col; j++) {
                int height = bfs(heightMap, row, col, i, j);
                System.out.println(String.format("x:%s,y:%s,h:%s", row, col, height));
                if (height > heightMap[i][j]) {
                    water += height - heightMap[i][j];
                }
            }
        }
        return water;
    }

    private int bfs(int[][] heightMap, int row, int col, int i, int j) {
        //一圈圈扫描周围的最高点。bfs
        Queue<int[]> queue = new LinkedList<>();
        int[][] visit = new int[row][col];
        queue.add(new int[]{i, j});
        visit[i][j] = 1;
        int max = 0;
        int depth = 0;
        while (queue.size() > 0) {
            int len = queue.size();
            //计算同一层的最小值
            int min = Integer.MAX_VALUE;
            for (int k = 0; k < len; k++) {
                int[] poll = queue.poll();
                for (int[] d : DIR) {
                    int x = poll[0] + d[0];
                    int y = poll[1] + d[1];
                    //到边界，可以直接返回
                    if (x < 0 || x >= row || y < 0 || y >= col) {
                        return max;
                    }
                    if (visit[x][y] == 1) {
                        continue;
                    }
                    min = Math.min(min, heightMap[x][y]);
                    queue.add(new int[]{x, y});
                    visit[x][y] = 1;
                }
                System.out.println(String.format("第%s层的高度为%s", ++depth, min));
            }
            max = Math.max(min, max);
        }
        return max;
    }

    /**
     * 这道题其实真的有点难，代码不长，但是不容易想到
     *
     * @param heightMap
     * @return
     */
    public int trapRainWater2(int[][] heightMap) {
        int row = heightMap.length, col = heightMap[0].length;
        if (row < 3 || col < 3) {
            return 0;
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));
        int[][] visit = new int[row][col];
        //四条边入队列
        for (int i = 0; i < row; i++) {
            pq.add(new int[]{i, 0, heightMap[i][0]});
            visit[i][0] = 1;
            pq.add(new int[]{i, col - 1, heightMap[i][col - 1]});
            visit[i][col - 1] = 1;
        }
        for (int i = 1; i < col - 1; i++) {
            pq.add(new int[]{0, i, heightMap[0][i]});
            visit[0][i] = 1;
            pq.add(new int[]{row - 1, i, heightMap[row - 1][i]});
            visit[row - 1][i] = 1;
        }
        //bfs过程
        int res = 0;
        while (!pq.isEmpty()) {
            int[] poll = pq.poll();
            int high = poll[2];
            log.info("[{},{},{}]", poll[0], poll[1], poll[2]);
            for (int[] dir : DIR) {
                int nx = poll[0] + dir[0], ny = poll[1] + dir[1];
                if (nx >= 0 && nx < row && ny >= 0 && ny < col && visit[nx][ny] == 0) {
                    //比周围最低点要低
                    if (high > heightMap[nx][ny]) {
                        res += high - heightMap[nx][ny];
                        heightMap[nx][ny] = high;
                    }
                    visit[nx][ny] = 1;
                    pq.add(new int[]{nx, ny, heightMap[nx][ny]});
                }
            }
        }
        return res;
    }
}
