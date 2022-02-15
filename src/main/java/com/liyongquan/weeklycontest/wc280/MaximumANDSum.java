package com.liyongquan.weeklycontest.wc280;

//6007. 数组的最大与和
//给你一个长度为 n 的整数数组 nums 和一个整数 numSlots ，满足2 * numSlots >= n 。总共有 numSlots 个篮子，编号为 1 到 numSlots 。
//
//你需要把所有 n 个整数分到这些篮子中，且每个篮子 至多 有 2 个整数。一种分配方案的 与和 定义为每个数与它所在篮子编号的 按位与运算 结果之和。
//
//比方说，将数字 [1, 3] 放入篮子 1 中，[4, 6] 放入篮子 2 中，这个方案的与和为 (1 AND 1) + (3 AND 1) + (4 AND 2) + (6 AND 2) = 1 + 1 + 0 + 2 = 4 。
//请你返回将 nums 中所有数放入 numSlots 个篮子中的最大与和。
//
// 
//
//示例 1：
//
//输入：nums = [1,2,3,4,5,6], numSlots = 3
//输出：9
//解释：一个可行的方案是 [1, 4] 放入篮子 1 中，[2, 6] 放入篮子 2 中，[3, 5] 放入篮子 3 中。
//最大与和为 (1 AND 1) + (4 AND 1) + (2 AND 2) + (6 AND 2) + (3 AND 3) + (5 AND 3) = 1 + 0 + 2 + 2 + 3 + 1 = 9 。
//示例 2：
//
//输入：nums = [1,3,10,4,7,1], numSlots = 9
//输出：24
//解释：一个可行的方案是 [1, 1] 放入篮子 1 中，[3] 放入篮子 3 中，[4] 放入篮子 4 中，[7] 放入篮子 7 中，[10] 放入篮子 9 中。
//最大与和为 (1 AND 1) + (1 AND 1) + (3 AND 3) + (4 AND 4) + (7 AND 7) + (10 AND 9) = 1 + 1 + 3 + 4 + 7 + 8 = 24 。
//注意，篮子 2 ，5 ，6 和 8 是空的，这是允许的。
// 
//
//提示：
//
//n == nums.length
//1 <= numSlots <= 9
//1 <= n <= 2 * numSlots
//1 <= nums[i] <= 15
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/maximum-and-sum-of-array
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import java.util.Arrays;

public class MaximumANDSum {
    /**
     * 先来个最简单的回溯解法
     * <p>
     * 超时
     *
     * @param nums
     * @param numSlots
     * @return
     */
    public int maximumANDSum(int[] nums, int numSlots) {
        return backtrace(nums, 0, new int[numSlots]);
    }

    private int backtrace(int[] nums, int idx, int[] slots) {
        int n = nums.length, m = slots.length;
        if (idx == n) {
            return 0;
        }
        int res = 0;
        for (int i = 0; i < m; i++) {
            if (slots[i] < 2) {
                slots[i]++;
                int sub = backtrace(nums, idx + 1, slots) + ((i + 1) & nums[idx]);
                slots[i]--;
                res = Math.max(res, sub);
            }
        }
        return res;
    }

    /**
     * 贪心？
     * <p>
     * 不通过，有漏洞
     *
     * @param nums
     * @param numSlots
     * @return
     */
    public int maximumANDSum2(int[] nums, int numSlots) {
        //取numSlots的最高位
        int s = numSlots;
        int bit = 0;
        while (s > 0) {
            s >>= 1;
            bit++;
        }
        int mask = (1 << (bit + 1)) - 1;
        //范围[1,mask]
        for (int i = 0; i < nums.length; i++) {
            nums[i] = nums[i] & mask;
        }
        Arrays.sort(nums);
        //数字大的整数拥有优先选择权
        int sum = 0;
        int[] slots = new int[numSlots];
        for (int i = nums.length - 1; i >= 0; i--) {
            int max = -1;
            int maxIdx = -1;
            for (int j = 0; j < numSlots; j++) {
                if (slots[j] < 2) {
                    if ((nums[i] & (j + 1)) > max) {
                        max = nums[i] & (j + 1);
                        maxIdx = j;
                    }
                }
            }
            slots[maxIdx]++;
            sum += max;
        }
        return sum;
    }

    //TODO:状态压缩
    public int maximumANDSum3(int[] nums, int numSlots) {
        int n = nums.length;
        int m = 1 << numSlots * 2;
        //第一维是表示选择前K个数字，第二位表示slot的状态
        int[][] dp = new int[n + 1][m];
        //dp迭代
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < m; j++) {
                //过滤掉不合法的状态
                if (Integer.bitCount(j) == i - 1) {
                    //把第i个球投到第k个slot
                    for (int k = 0; k < numSlots * 2; k++) {
                        if ((j & (1 << k)) == 0) {
                            dp[i][j + (1 << k)] = Math.max(dp[i][j + (1 << k)], dp[i - 1][j] + (k / 2 + 1) & nums[i - 1]);
                        }
                    }
                }
            }
        }
        //查找最大值
        int max = 0;
        for (int i = 0; i < m; i++) {
            max = Math.max(dp[n][i], max);
        }
        return max;
    }
}
