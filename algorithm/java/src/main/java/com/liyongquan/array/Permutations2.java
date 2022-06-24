package com.liyongquan.array;

import java.util.*;

/**
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,1,2]
 * 输出：
 * [[1,1,2],
 * [1,2,1],
 * [2,1,1]]
 * 示例 2：
 * <p>
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 8
 * -10 <= nums[i] <= 10
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutations-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Permutations2 {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> list = new LinkedList<>();
        dfs(nums, new ArrayDeque<>(nums.length), new int[nums.length], 0, nums.length, list);
        return list;
    }

    private void dfs(int[] nums, Deque<Integer> path, int[] used,
                     int depth, int len, List<List<Integer>> list) {
        if (depth == len) {
            //深度复制，避免回溯的时候把这个list清空
            list.add(new ArrayList<>(path));
            return;
        }
        //增加一个Set保证同一个数字不会重复被选择
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < len; i++) {
            if (used[i] == 1 || set.contains(nums[i])) {
                continue;
            }
            set.add(nums[i]);
            path.addLast(nums[i]);
            used[i] = 1;
            dfs(nums, path, used, depth + 1, len, list);
            used[i] = 0;
            path.removeLast();
        }
    }
}
