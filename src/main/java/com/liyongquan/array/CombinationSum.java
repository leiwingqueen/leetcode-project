package com.liyongquan.array;

import sun.java2d.pipe.AAShapePipe;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 39. 组合总和
 * <p>
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * <p>
 * candidates 中的数字可以无限制重复被选取。
 * <p>
 * 说明：
 * <p>
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。 
 * 示例 1：
 * <p>
 * 输入：candidates = [2,3,6,7], target = 7,
 * 所求解集为：
 * [
 * [7],
 * [2,2,3]
 * ]
 * 示例 2：
 * <p>
 * 输入：candidates = [2,3,5], target = 8,
 * 所求解集为：
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= candidates.length <= 30
 * 1 <= candidates[i] <= 200
 * candidate 中的每个元素都是独一无二的。
 * 1 <= target <= 500
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        int len = candidates.length;
        List<List<Integer>> res = new LinkedList<>();
        //定义每个数字出现的可能性，不再是0,1的问题，而是[0,n]的问题
        int[] cnt = new int[len];
        for (int i = 0; i < len; i++) {
            cnt[i] = target / candidates[i];
        }
        backtrace(candidates, cnt, 0, 0, new int[len], target, res);
        return res;
    }

    private void backtrace(int[] candidates, int[] cnt, int idx, int sum,
                           int[] path, int target, List<List<Integer>> res) {
        int len = candidates.length;
        //到达结果尾部
        if (idx >= len) {
            if (sum == target) {
                List<Integer> list = new ArrayList<>(len);
                for (int i = 0; i < len; i++) {
                    if (path[i] > 0) {
                        for (int j = 0; j < path[i]; j++) {
                            list.add(candidates[i]);
                        }
                    }
                }
                res.add(list);
            }
            return;
        }
        //适当剪枝
        if (sum > target) {
            return;
        }
        //选择多个 or 不选择
        for (int i = 0; i <= cnt[idx]; i++) {
            path[idx] = i;
            backtrace(candidates, cnt, idx + 1, sum + i * candidates[idx], path, target, res);
            //回溯
            path[idx] = 0;
        }
    }
}
