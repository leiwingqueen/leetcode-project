package com.liyongquan.backtrack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * <p>
 * candidates 中的每个数字在每个组合中只能使用一次。
 * <p>
 * 说明：
 * <p>
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。 
 * 示例 1:
 * <p>
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 所求解集为:
 * [
 * [1, 7],
 * [1, 2, 5],
 * [2, 6],
 * [1, 1, 6]
 * ]
 * 示例 2:
 * <p>
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 所求解集为:
 * [
 *   [1,2,2],
 *   [5]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CombinationSum2 {
    /**
     * 01背包问题，但这个问题的本质是求所有解，并不是最优解，因此优先考虑回溯
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        int len = candidates.length;
        if (len == 0) {
            return Collections.emptyList();
        }
        List<List<Integer>> res = new LinkedList<>();
        backtrace(candidates, new int[len], 0, 0, target, res);
        return res;
    }

    private void backtrace(int[] candidates, int[] path, int idx, int sum, int target, List<List<Integer>> res) {
        //边界情况+提前剪枝
        int len = candidates.length;
        if (idx == len || sum >= target) {
            if (sum == target) {
                addRes(candidates, path, res);
            }
            return;
        }
        //不选择
        backtrace(candidates, path, idx + 1, sum, target, res);
        path[idx] = 1;
        backtrace(candidates, path, idx + 1, sum + candidates[idx], target, res);
        path[idx] = 0;
    }

    private void addRes(int[] candidates, int[] path, List<List<Integer>> res) {
        int len = candidates.length;
        List<Integer> com = new ArrayList<>(len);
        for (int i = 0; i < len; i++) {
            if (path[i] == 1) {
                com.add(candidates[i]);
            }
        }
        res.add(com);
    }
}
