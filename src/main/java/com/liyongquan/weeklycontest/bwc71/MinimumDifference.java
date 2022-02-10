package com.liyongquan.weeklycontest.bwc71;

//2163. 删除元素后和的最小差值
//给你一个下标从 0 开始的整数数组 nums ，它包含 3 * n 个元素。
//
//你可以从 nums 中删除 恰好 n 个元素，剩下的 2 * n 个元素将会被分成两个 相同大小 的部分。
//
//前面 n 个元素属于第一部分，它们的和记为 sumfirst 。
//后面 n 个元素属于第二部分，它们的和记为 sumsecond 。
//两部分和的 差值 记为 sumfirst - sumsecond 。
//
//比方说，sumfirst = 3 且 sumsecond = 2 ，它们的差值为 1 。
//再比方，sumfirst = 2 且 sumsecond = 3 ，它们的差值为 -1 。
//请你返回删除 n 个元素之后，剩下两部分和的 差值的最小值 是多少。
//
// 
//
//示例 1：
//
//输入：nums = [3,1,2]
//输出：-1
//解释：nums 有 3 个元素，所以 n = 1 。
//所以我们需要从 nums 中删除 1 个元素，并将剩下的元素分成两部分。
//- 如果我们删除 nums[0] = 3 ，数组变为 [1,2] 。两部分和的差值为 1 - 2 = -1 。
//- 如果我们删除 nums[1] = 1 ，数组变为 [3,2] 。两部分和的差值为 3 - 2 = 1 。
//- 如果我们删除 nums[2] = 2 ，数组变为 [3,1] 。两部分和的差值为 3 - 1 = 2 。
//两部分和的最小差值为 min(-1,1,2) = -1 。
//示例 2：
//
//输入：nums = [7,9,5,8,1,3]
//输出：1
//解释：n = 2 。所以我们需要删除 2 个元素，并将剩下元素分为 2 部分。
//如果我们删除元素 nums[2] = 5 和 nums[3] = 8 ，剩下元素为 [7,9,1,3] 。和的差值为 (7+9) - (1+3) = 12 。
//为了得到最小差值，我们应该删除 nums[1] = 9 和 nums[4] = 1 ，剩下的元素为 [7,5,8,3] 。和的差值为 (7+5) - (8+3) = 1 。
//观察可知，最优答案为 1 。
// 
//
//提示：
//
//nums.length == 3 * n
//1 <= n <= 105
//1 <= nums[i] <= 105
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/minimum-difference-in-sums-after-removal-of-elements
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import java.util.PriorityQueue;

public class MinimumDifference {
    /**
     * 问题我们可以稍微做下转换，我们需要找到一个分界点k把数组分成两部分，然后我们分别从左右两部分选择最小和最大的n个数字
     * <p>
     * 左右两份部分为
     * [0,k),[k,3n)
     * 我们需要保证左右两边都至少有n个数字，那么k的取值范围[n,2n)
     * <p>
     * 分别求左右两边的最小和最大的n个数字和我们可以考虑用空间换时间，预计算来得到
     *
     * @param nums
     * @return
     */
    public long minimumDifference(int[] nums) {
        int n = nums.length / 3;
        int[] left = new int[n], right = new int[n];
        //计算左边区域
        PriorityQueue<Integer> pqMax = new PriorityQueue<>((o1, o2) -> o2 - o1);
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            pqMax.add(nums[i]);
        }
        left[0] = sum;
        for (int i = 1; i < n; i++) {
            int num = nums[n + i - 1];
            pqMax.add(num);
            Integer poll = pqMax.poll();
            sum += num - poll;
            left[i] = sum;
        }
        //计算右边区域
        PriorityQueue<Integer> pqMin = new PriorityQueue<>();
        sum = 0;
        for (int i = 3 * n - 1; i >= 2 * n; i--) {
            sum += nums[i];
            pqMin.add(nums[i]);
        }
        right[n - 1] = sum;
        for (int i = 1; i < n; i--) {
            int num = nums[2 * n - i];
            pqMin.add(num);
            Integer poll = pqMin.poll();
            sum += num - poll;
            right[n - i - 1] = sum;
        }
        //遍历一次，求最小值
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            res = Math.min(left[i] - right[i], res);
        }
        return res;
    }
}
