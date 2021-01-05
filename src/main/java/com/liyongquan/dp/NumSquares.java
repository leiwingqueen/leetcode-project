package com.liyongquan.dp;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 * <p>
 * 示例 1:
 * <p>
 * 输入: n = 12
 * 输出: 3
 * 解释: 12 = 4 + 4 + 4.
 * 示例 2:
 * <p>
 * 输入: n = 13
 * 输出: 2
 * 解释: 13 = 4 + 9.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/perfect-squares
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NumSquares {
    /**
     * 暴力解法dfs
     * <p>
     * 指数级别的时间复杂度
     * <p>
     * 超时
     *
     * @param n
     * @return
     */
    public int numSquares(int n) {
        if (n == 0) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= Math.sqrt(n); i++) {
            int ns = numSquares(n - i * i);
            min = Math.min(min, ns + 1);
        }
        return min;
    }

    /**
     * dfs会超时，对于一颗可达接的树而言，最优解就是叶子节点的最小的高度。我们可以考虑使用bfs来进行遍历
     *
     * 哈哈，还是超时，只是相当于适当剪枝。没有去重复解
     *
     * @param n
     * @return
     */
    public int numSquares2(int n) {
        Queue<Integer> queue = new LinkedList<>();
        int depth = 0;
        queue.offer(n);
        while (queue.size() != 0) {
            int size = queue.size();
            //遍历每一层的节点
            for (int i = 0; i < size; i++) {
                Integer poll = queue.poll();
                if (poll == 0) {
                    return depth;
                }
                for (int j = 1; j <= Math.sqrt(poll); j++) {
                    queue.add(poll - j * j);
                }
            }
            depth++;
        }
        return depth;
    }

}
