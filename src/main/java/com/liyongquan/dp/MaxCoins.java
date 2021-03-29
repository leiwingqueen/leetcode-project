package com.liyongquan.dp;

/**
 * //有 n 个气球，编号为0 到 n - 1，每个气球上都标有一个数字，这些数字存在数组 nums 中。
 * //
 * // 现在要求你戳破所有的气球。戳破第 i 个气球，你可以获得 nums[i - 1] * nums[i] * nums[i + 1] 枚硬币。 这里的 i -
 * // 1 和 i + 1 代表和 i 相邻的两个气球的序号。如果 i - 1或 i + 1 超出了数组的边界，那么就当它是一个数字为 1 的气球。
 * //
 * // 求所能获得硬币的最大数量。
 * //
 * //
 * //示例 1：
 * //
 * //
 * //输入：nums = [3,1,5,8]
 * //输出：167
 * //解释：
 * //nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
 * //coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167
 * //
 * // 示例 2：
 * //
 * //
 * //输入：nums = [1,5]
 * //输出：10
 * //
 * //
 * //
 * //
 * // 提示：
 * //
 * //
 * // n == nums.length
 * // 1 <= n <= 500
 * // 0 <= nums[i] <= 100
 * //
 * // Related Topics 分治算法 动态规划
 * // 👍 679 👎 0
 */
public class MaxCoins {
    /**
     * 回溯解法，必然超时
     *
     * @param nums
     * @return
     */
    public int maxCoins(int[] nums) {
        return backtrace(nums, new int[nums.length], 0, nums.length);
    }

    private int backtrace(int[] nums, int[] pop, int idx, int len) {
        if (idx == len) {
            return 0;
        }
        int max = 0;
        for (int i = 0; i < len; i++) {
            if (pop[i] == 1) {
                continue;
            }
            pop[i] = 1;
            //查找两边相邻的数组
            int l = i - 1, r = i + 1;
            while (l >= 0 && pop[l] == 1) {
                l--;
            }
            while (r < len && pop[r] == 1) {
                r++;
            }
            int tmp = (l >= 0 ? nums[l] : 1) * nums[i] * (r < len ? nums[r] : 1);
            int sub = backtrace(nums, pop, idx + 1, len);
            max = Math.max(max, sub + tmp);
            //回溯
            pop[i] = 0;
        }
        return max;
    }
}
