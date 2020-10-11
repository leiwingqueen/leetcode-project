package com.liyongquan.dp;

/**
 * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * <p>
 * 注意:
 * <p>
 * 每个数组中的元素不会超过 100
 * 数组的大小不会超过 200
 * 示例 1:
 * <p>
 * 输入: [1, 5, 11, 5]
 * <p>
 * 输出: true
 * <p>
 * 解释: 数组可以分割成 [1, 5, 5] 和 [11].
 *  
 * <p>
 * 示例 2:
 * <p>
 * 输入: [1, 2, 3, 5]
 * <p>
 * 输出: false
 * <p>
 * 解释: 数组不能分割成两个元素和相等的子集.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/partition-equal-subset-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class PartitionEqualSubsetSum {
    /**
     * 不做任何剪枝,时间复杂度O(2^n)，空间复杂度O(1)
     * <p>
     * 不能通过，超时~~~
     *
     * @param nums
     * @return
     */
    public boolean canPartition(int[] nums) {
        if (nums.length <= 0) {
            return true;
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        return dfs(nums, 0, 0, sum);
    }

    private boolean dfs(int[] nums, int index, int left, int right) {
        if (index >= nums.length) {
            return left == right;
        }
        boolean l = dfs(nums, index + 1, left + nums[index], right - nums[index]);
        if (l) {
            return true;
        }
        return dfs(nums, index + 1, left, right);
    }
}
