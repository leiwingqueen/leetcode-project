package com.liyongquan.bfs;


import java.util.LinkedList;
import java.util.Queue;

//为了给刷题的同学一些奖励，力扣团队引入了一个弹簧游戏机。游戏机由 N 个特殊弹簧排成一排，编号为 0 到 N-1。初始有一个小球在编号 0 的弹簧处。若小球
//在编号为 i 的弹簧处，通过按动弹簧，可以选择把小球向右弹射 jump[i] 的距离，或者向左弹射到任意左侧弹簧的位置。也就是说，在编号为 i 弹簧处按动弹簧，
//小球可以弹向 0 到 i-1 中任意弹簧或者 i+jump[i] 的弹簧（若 i+jump[i]>=N ，则表示小球弹出了机器）。小球位于编号 0 处的弹簧时不
//能再向左弹。
//
// 为了获得奖励，你需要将小球弹出机器。请求出最少需要按动多少次弹簧，可以将小球从编号 0 弹簧弹出整个机器，即向右越过编号 N-1 的弹簧。
//
// 示例 1：
//
//
// 输入：jump = [2, 5, 1, 1, 1, 1]
//
// 输出：3
//
// 解释：小 Z 最少需要按动 3 次弹簧，小球依次到达的顺序为 0 -> 2 -> 1 -> 6，最终小球弹出了机器。
//
//
// 限制：
//
//
// 1 <= jump.length <= 10^6
// 1 <= jump[i] <= 10000
//
// Related Topics 广度优先搜索 线段树 数组 动态规划
// 👍 52 👎 0
public class MinJump {

    private static class Pair {
        int idx;
        int depth;
        Pair(int idx, int depth) {
            this.idx = idx;
            this.depth = depth;
        }
    }

    public int minJump(int[] jump) {
        int len = jump.length;
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(0, 0));
        int[] visit = new int[len];
        visit[0] = 1;
        int far = 1;
        while (!queue.isEmpty()) {
            Pair poll = queue.poll();
            int idx = poll.idx;
            int depth = poll.depth;
            //向右移动
            if (jump[idx] + idx >= len) {
                return depth + 1;
            }
            if (visit[jump[idx] + idx] == 0) {
                queue.add(new Pair(jump[idx] + idx, depth + 1));
                visit[jump[idx] + idx] = 1;
            }
            //向左移动
            // 小优化，从后往前遍历遍历，如果某个节点已经被访问过，那么他前面的节点必然也已经加入队列
            /*for (int i = idx - 1; i >= 0 && visit[i] == 0; i--) {
                queue.add(new Pair(i, depth + 1));
                visit[i] = 1;
            }*/
            for (int i = far; i < idx; i++) {
                if (visit[i] == 0) {
                    queue.add(new Pair(i, depth + 1));
                    visit[i] = 1;
                }
            }
            far = Math.max(far, idx + 1);
        }
        return -1;
    }
}
