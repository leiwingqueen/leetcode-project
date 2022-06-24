package com.liyongquan.slidewindow;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个数组 nums 和一个目标值 k，找到和等于 k 的最长子数组长度。如果不存在任意一个符合要求的子数组，则返回 0。
 * <p>
 * 注意:
 *  nums 数组的总和是一定在 32 位有符号整数范围之内的。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1, -1, 5, -2, 3], k = 3
 * 输出: 4
 * 解释: 子数组 [1, -1, 5, -2] 和等于 3，且长度最长。
 * 示例 2:
 * <p>
 * 输入: nums = [-2, -1, 2, 1], k = 1
 * 输出: 2
 * 解释: 子数组 [-1, 2] 和等于 1，且长度最长。
 * 进阶:
 * 你能使时间复杂度在 O(n) 内完成此题吗?
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-size-subarray-sum-equals-k
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaxSubArrayLen {
    /**
     * 先尝试O(n^2)的解法
     * <p>
     * 前缀和
     * <p>
     * 勉强通过
     *
     * @param nums
     * @param k
     * @return
     */
    public int maxSubArrayLen(int[] nums, int k) {
        //计算前缀和
        int[] preSum = new int[nums.length + 1];
        for (int i = 1; i <= nums.length; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }
        int max = 0;
        //适当剪枝，继续往后找也不能找到更大的了
        for (int i = 0; i < nums.length && i <= nums.length - max; i++) {
            //适当剪枝 i + max - 1 < j
            for (int j = nums.length - 1; j >= i && i + max - 1 < j; j--) {

                int sum = preSum[j + 1] - preSum[i];
                if (sum == k) {
                    max = Math.max(max, j - i + 1);
                    //继续往左移动也不会找到更优解，直接退出
                    break;
                }
            }
        }
        return max;
    }

    /**
     * 计算出每个索引的前缀和，利用一个数组sum保存，利用一个max储存最长子数组长度；
     * 利用HashMap储存每个前缀和和对应的索引，如果出现前缀和相同的情况，则储存较小的索引（因为要求最长子数组）；
     * 利用一个指针i作为子数组的结尾从后向前遍历，寻找map中是否储存有key为sum[i] - k的索引，如果有则更新max；
     * 当指针的值小于等于max的值后，则无需再继续遍历；
     * 返回max则为最终结果。
     * <p>
     * 作者：iame
     * 链接：https://leetcode-cn.com/problems/maximum-size-subarray-sum-equals-k/solution/325-he-deng-yu-k-de-zui-chang-zi-shu-zu-chang-du-j/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public int maxSubArrayLen2(int[] nums, int k) {
        //计算前缀和
        int[] preSum = new int[nums.length + 1];
        for (int i = 1; i <= nums.length; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }
        //把前缀和对应的索引位置存储起来
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < preSum.length; i++) {
            if (!map.containsKey(preSum[i])) {
                map.put(preSum[i], i);
            }
        }
        int max = 0;
        for (int i = nums.length; i > 0 && i > max; i--) {
            int left = preSum[i] + k;
            if (map.containsKey(left) && map.get(left) <= i) {
                max = Math.max(max, i - map.get(left));
            }
        }
        return max;
    }
}
