package com.liyongquan.array;

import java.util.*;

/**
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 * <p>
 * 示例:
 * <p>
 * 输入: n = 4, k = 2
 * 输出:
 * [
 * [2,4],
 * [3,4],
 * [2,3],
 * [1,2],
 * [1,3],
 * [1,4],
 * ]
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combinations
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Combinations {
    /**
     * 还是回溯解法，但是这题跟之前的题目有点不调一样，求的是组合，并不是排列
     * <p>
     * 为了去掉重复解，在每一次取的时候我们只能选择上一次选择的最后一个数后面的数字，从而保证我们的序列是一个升序。
     *
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new LinkedList<>();
        backtrack(n, new ArrayDeque<>(n), 0, k, result);
        return result;
    }

    private void backtrack(int n, Deque<Integer> path, int depth, int k,
                           List<List<Integer>> result) {
        //最后添加的数字
        int last = path.size() > 0 ? path.getLast() : 0;
        //剩余数组不满足条件，直接返回失败
        if (n - last < k - depth) {
            return;
        }
        //剩余数字只能全部选择
        if (n - last == k - depth) {
            List<Integer> r = new ArrayList<>(path);
            for (int i = 1; i <= k - depth; i++) {
                r.add(last + i);
            }
            result.add(r);
            return;
        }
        //已经找到合适的数字
        if (depth == k) {
            result.add(new ArrayList<>(path));
            return;
        }
        //只能选择后面的数字
        for (int i = last + 1; i <= n; i++) {
            path.addLast(i);
            backtrack(n, path, depth + 1, k, result);
            //回溯
            path.removeLast();
        }
    }
}
