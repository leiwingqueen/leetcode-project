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
     * <p>
     * 剪枝还是过不了
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
        //适当剪枝
        if (left < right) {
            boolean l = dfs(nums, index + 1, left + nums[index], right - nums[index]);
            if (l) {
                return true;
            }
            return dfs(nums, index + 1, left, right);
        } else {
            return false;
        }
    }

    /**
     * 既然暴力+剪枝没法过，我们换个思路。
     * <p>
     * 分成等分的两部分，相当于能够在数组中找到一组数组=sum/2，sum为所有数字的总和(显然如果sum为奇数的话是可以直接返回false)
     * <p>
     * 我们假设f(n,s)表示前n个数字中，是否能够组成合为s的方程。这里我们就可以发现，这变成一个典型的背包问题了。
     * <p>
     * dp方程:
     * f(n,s)=f(n-1,s) || f(n-1,s-nums[n-1])
     * <p>
     * 初始化：
     * 直接把n=1的场景初始化。
     * <p>
     * 时间复杂度O(n*s)，空间复杂度O(n*s)，n为节点个数，s为nums的总和/2
     *
     * @param nums
     * @return
     */
    public boolean canPartition2(int[] nums) {
        if (nums.length == 0) {
            return true;
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        //奇偶判断
        if ((sum & 0b1) != 0) {
            return false;
        }
        sum >>= 1;
        boolean[][] dp = new boolean[nums.length][sum + 1];
        //初始化
        dp[0][0] = true;
        for (int i = 1; i <= sum; i++) {
            dp[0][i] = nums[0] == i;
        }
        for (int i = 0; i < nums.length; i++) {
            dp[i][0] = true;
        }
        //dp迭代
        for (int i = 1; i < nums.length; i++) {
            for (int j = 1; j <= sum; j++) {
                if (nums[i] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]];
                }
            }
        }
        return dp[nums.length - 1][sum];
    }
}
