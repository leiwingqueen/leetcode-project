package com.liyongquan.bfs;

import java.util.List;
import java.util.concurrent.TransferQueue;

/**
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 * <p>
 * 相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
 * <p>
 *  
 * <p>
 * 例如，给定三角形：
 * <p>
 * [
 * [2],
 * [3,4],
 * [6,5,7],
 * [4,1,8,3]
 * ]
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 * <p>
 *  
 * <p>
 * 说明：
 * <p>
 * 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/triangle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Triangle {
    /**
     * dfs，这种计算方式会超时，由于父结点会被重复计算
     *
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < triangle.size(); i++) {
            int dfs = dfs(triangle, triangle.size() - 1, i);
            if (dfs < min) {
                min = dfs;
            }
        }
        return min;
    }

    private int dfs(List<List<Integer>> triangle, int i, int j) {
        int p = triangle.get(i).get(j);
        if (i == 0) {
            return p;
        }
        if (j == i) {
            return dfs(triangle, i - 1, j-1) + p;
        } else if (j == 0) {
            return dfs(triangle, i - 1, j) + p;
        } else {
            int p1 = dfs(triangle, i - 1, j);
            int p2 = dfs(triangle, i - 1, j - 1);
            return Math.min(p1, p2) + p;
        }
    }
}
