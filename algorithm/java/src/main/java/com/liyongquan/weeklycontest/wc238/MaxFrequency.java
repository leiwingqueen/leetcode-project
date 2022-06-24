package com.liyongquan.weeklycontest.wc238;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * 元素的 频数 是该元素在一个数组中出现的次数。
 * <p>
 * 给你一个整数数组 nums 和一个整数 k 。在一步操作中，你可以选择 nums 的一个下标，并将该下标对应元素的值增加 1 。
 * <p>
 * 执行最多 k 次操作后，返回数组中最高频元素的 最大可能频数 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,4], k = 5
 * 输出：3
 * 解释：对第一个元素执行 3 次递增操作，对第二个元素执 2 次递增操作，此时 nums = [4,4,4] 。
 * 4 是数组中最高频元素，频数是 3 。
 * 示例 2：
 * <p>
 * 输入：nums = [1,4,8,13], k = 5
 * 输出：2
 * 解释：存在多种最优解决方案：
 * - 对第一个元素执行 3 次递增操作，此时 nums = [4,4,8,13] 。4 是数组中最高频元素，频数是 2 。
 * - 对第二个元素执行 4 次递增操作，此时 nums = [1,8,8,13] 。8 是数组中最高频元素，频数是 2 。
 * - 对第三个元素执行 5 次递增操作，此时 nums = [1,4,13,13] 。13 是数组中最高频元素，频数是 2 。
 * 示例 3：
 * <p>
 * 输入：nums = [3,9,6], k = 2
 * 输出：1
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^5
 * 1 <= k <= 105
 */
@Slf4j
public class MaxFrequency {
    /**
     * 贪心+暴力暴力貌似就可以了
     * <p>
     * 不通过，应该用滑动窗口
     *
     * @param nums
     * @param k
     * @return
     */
    public int maxFrequency(int[] nums, int k) {
        int len = nums.length;
        Arrays.sort(nums);
        long diff = 0;
        int i = 1;
        while (i < len) {
            diff += (long) i * (nums[i] - nums[i - 1]);
            if (diff > k) {
                break;
            }
            i++;
        }
        return i;
    }

    /**
     * 滑动窗口+前缀和
     *
     * @param nums
     * @param k
     * @return
     */
    public int maxFrequency2(int[] nums, int k) {
        int len = nums.length;
        Arrays.sort(nums);
        int l = 0, r = 1;
        int diff = 0;
        int max = 1;
        while (r < len) {
            diff += (nums[r] - nums[r - 1]) * (r - l);
            r++;
            if (diff <= k) {
                max = Math.max(max, r - l);
            } else {
                //左边窗口移动
                while (l < r && diff > k) {
                    diff -= nums[r - 1] - nums[l];
                    l++;
                }
            }
        }
        return max;
    }
}
