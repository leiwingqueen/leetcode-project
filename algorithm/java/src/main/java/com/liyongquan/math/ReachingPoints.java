package com.liyongquan.math;

//给定四个整数 sx , sy ，tx 和 ty，如果通过一系列的转换可以从起点 (sx, sy) 到达终点 (tx, ty)，则返回 true，否则返回 false。
//
//从点 (x, y) 可以转换到 (x, x+y)  或者 (x+y, y)。
//
// 
//
//示例 1:
//
//输入: sx = 1, sy = 1, tx = 3, ty = 5
//输出: true
//解释:
//可以通过以下一系列转换从起点转换到终点：
//(1, 1) -> (1, 2)
//(1, 2) -> (3, 2)
//(3, 2) -> (3, 5)
//示例 2:
//
//输入: sx = 1, sy = 1, tx = 2, ty = 2
//输出: false
//示例 3:
//
//输入: sx = 1, sy = 1, tx = 1, ty = 1
//输出: true
// 
//
//提示:
//
//1 <= sx, sy, tx, ty <= 109
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/reaching-points
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import java.util.*;

public class ReachingPoints {
    /**
     * 先尝试用bfs求解
     * <p>
     * 超时
     *
     * @param sx
     * @param sy
     * @param tx
     * @param ty
     * @return
     */
    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        Set<Integer> visit = new HashSet<>();
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{sx, sy});
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            if (poll[0] == tx && poll[1] == ty) {
                return true;
            }
            int x = poll[0] + poll[1];
            int y = poll[1];
            int[] pos = new int[]{x, y};
            if (!visit.contains(Arrays.hashCode(pos)) && x <= tx && y <= ty) {
                queue.add(pos);
                visit.add(Arrays.hashCode(poll));
            }
            x = poll[0];
            y = poll[0] + poll[1];
            pos = new int[]{x, y};
            if (!visit.contains(Arrays.hashCode(pos)) && x <= tx && y <= ty) {
                queue.add(pos);
                visit.add(Arrays.hashCode(poll));
            }
        }
        return false;
    }

    /**
     * 优化解法2，线性复杂度
     * <p>
     * 从终点逆向思维
     *
     * @param sx
     * @param sy
     * @param tx
     * @param ty
     * @return
     */
    public boolean reachingPoints2(int sx, int sy, int tx, int ty) {
        while (tx >= sx && ty >= sy) {
            if (sx == tx && sy == ty) {
                return true;
            }
            if (tx >= ty) {
                //这里其实可以加速运算
                tx -= ty;
            } else {
                ty -= tx;
            }
        }
        return false;
    }

    /**
     * 在上面基础上进行加速
     *
     * @param sx
     * @param sy
     * @param tx
     * @param ty
     * @return
     */
    public boolean reachingPoints3(int sx, int sy, int tx, int ty) {
        while (tx >= sx && ty >= sy) {
            if (sx == tx && sy == ty) {
                return true;
            }
            if (tx >= ty) {
                int mx = Math.max(ty, sx);
                int cnt = (tx - mx) / ty;
                if (cnt == 0) {
                    cnt = 1;
                }
                tx -= cnt * ty;
            } else {
                int mx = Math.max(tx, sy);
                int cnt = (ty - mx) / tx;
                if (cnt == 0) {
                    cnt = 1;
                }
                ty -= cnt * tx;
            }
        }
        return false;
    }
}
