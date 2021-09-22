package com.liyongquan.dp;

//673. 最长递增子序列的个数
//给定一个未排序的整数数组，找到最长递增子序列的个数。
//
//示例 1:
//
//输入: [1,3,5,4,7]
//输出: 2
//解释: 有两个最长递增子序列，分别是 [1, 3, 4, 7] 和[1, 3, 5, 7]。
//示例 2:
//
//输入: [2,2,2,2,2]
//输出: 5
//解释: 最长递增子序列的长度是1，并且存在5个子序列的长度为1，因此输出5。
//注意: 给定的数组长度不超过 2000 并且结果一定是32位有符号整数。
//
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/number-of-longest-increasing-subsequence
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import java.util.*;

/**
 * @author liyongquan
 * @date 2021/9/20
 */
public class FindNumberOfLIS {
    /**
     * 超时
     *
     * @param nums
     * @return
     */
    public int findNumberOfLIS(int[] nums) {
        int len = nums.length;
        if (len == 1) {
            return 1;
        }
        //二分查找，找到第一个不符合的结果
        int l = 1, r = len;
        long res = 0;
        while (l < r) {
            int mid = l + (r - l) / 2;
            long f = find(nums, len, mid);
            if (f <= 0) {
                r = mid;
            } else {
                res = f;
                l = mid + 1;
            }
        }
        long f = find(nums, len, l);
        if (f > 0) {
            res = f;
        }
        return (int) res;
    }

    /**
     * 假设f(n,k)是前n个数字，并且最后一个数字是选中的情况，自增序列长度为k的个数
     * <p>
     * f(n,k)=sum{f(i,k-1)},这里i>=k-1,且A[i]<A[n]
     * <p>
     * 那么前n个数字能够构成长度为k的自增序列数量为
     * sum{f(i,k)}，其中i>=k且i<=n
     *
     * @param nums
     * @param len
     * @param k
     * @return
     */
    private long find(int[] nums, int len, int k) {
        if (k > len) {
            return 0;
        }
        if (k == 1) {
            return len;
        }
        long[][] dp = new long[len][k];
        //初始化
        for (int i = 0; i < len; i++) {
            dp[i][0] = 1;
        }
        //dp迭代
        for (int i = 1; i < len; i++) {
            for (int j = 1; j <= i & j < k; j++) {
                long sum = 0;
                for (int l = j - 1; l < i; l++) {
                    if (nums[l] < nums[i]) {
                        sum += dp[l][j - 1];
                    }
                }
                dp[i][j] = sum;
            }
        }
        //求和
        long res = 0;
        for (int i = k - 1; i < len; i++) {
            res += dp[i][k - 1];
        }
        return res;
    }

    /**
     * f(n)表示以第n个数字结尾的最大长度
     * g(n)表示以n个数字结尾的最长的子序列的数量
     *
     * @param nums
     * @return
     */
    public int findNumberOfLIS2(int[] nums) {
        int len = nums.length;
        if (len <= 1) {
            return len;
        }
        //求最大长度
        int[] fn = new int[len];
        fn[0] = 1;
        for (int i = 1; i < len; i++) {
            fn[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j] && nums[j] + 1 > fn[i]) {
                    fn[i] = nums[j] + 1;
                }
            }
        }
        int maxLen = 0;
        for (int i = 0; i < len; i++) {
            maxLen = Math.max(maxLen, fn[i]);
        }
        //gn的dp过程
        int[] gn = new int[len];
        gn[0] = 1;
        for (int i = 1; i < len; i++) {
            gn[i] = 0;
            if (fn[i] == 1) {
                //只有一个数字的场景
                gn[i] = 1;
            } else {
                for (int j = 0; j < i; j++) {
                    if (nums[j] < nums[i] && gn[j] == fn[i] - 1) {
                        gn[i] += gn[j];
                    }
                }
            }
        }
        //统一最大长度为maxLen的下标
        int res = 0;
        for (int i = 0; i < len; i++) {
            if (fn[i] == maxLen) {
                res += gn[i];
            }
        }
        return res;
    }
}
