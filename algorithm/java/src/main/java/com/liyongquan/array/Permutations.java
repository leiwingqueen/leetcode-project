package com.liyongquan.array;

import java.util.*;

/**
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,2,3]
 * 输出:
 * [
 * [1,2,3],
 * [1,3,2],
 * [2,1,3],
 * [2,3,1],
 * [3,1,2],
 * [3,2,1]
 * ]
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutations
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Permutations {
    /**
     * 回溯解法
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        List<List<Integer>> list = new LinkedList<>();
        dfs(new LinkedList<>(), set, list);
        return list;
    }

    private void dfs(List<Integer> prefix, Set<Integer> set, List<List<Integer>> list) {
        if (set.size() == 0) {
            //深度复制，避免回溯的时候把这个list清空
            List<Integer> r = new LinkedList<>();
            r.addAll(prefix);
            list.add(r);
            return;
        }
        //拷贝一份，避免对set进行修改，会导致迭代异常
        Set<Integer> ns = new HashSet<>();
        ns.addAll(set);
        for (Integer num : ns) {
            prefix.add(num);
            set.remove(num);
            dfs(prefix, set, list);
            //回溯
            set.add(num);
            prefix.remove(num);
        }
    }

    public List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> list = new LinkedList<>();
        dfs2(nums, new ArrayDeque<>(nums.length), new int[nums.length], 0, nums.length, list);
        return list;
    }

    private void dfs2(int[] nums, Deque<Integer> path, int[] used, int depth, int len, List<List<Integer>> list) {
        if (depth == len) {
            //深度复制，避免回溯的时候把这个list清空
            list.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < len; i++) {
            if (used[i] == 1) {
                continue;
            }
            path.addLast(nums[i]);
            used[i] = 1;
            dfs2(nums, path, used, depth + 1, len, list);
            used[i] = 0;
            path.removeLast();
        }
    }
}
