package com.liyongquan.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * 560. 和为K的子数组
 * <p>
 * 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
 * <p>
 * 示例 1 :
 * <p>
 * 输入:nums = [1,1,1], k = 2
 * 输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
 * 说明 :
 * <p>
 * 数组的长度为 [1, 20,000]。
 * 数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]。
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subarray-sum-equals-k
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SubarraySum {
    /**
     * 首先遍历所有的子串，对于每个子串的和我们可以使用dp来简化复杂度
     * <p>
     * 假设f(i,j)为下标范围为[i,j]的子串，我们有f(i,j)=f(i-1,j)-A[i-1]，或者f(i,j)=f(i+1,j)+A[i]
     * <p>
     * 两者的区别的从第一行开始扫描还是从最后一行开始扫描
     * <p>
     * 时间复杂度O(n^2)
     * 空间复杂度O(n^2)
     * <p>
     * 不通过，由于数组的长度是万级别的，因为O(n^2)的时间复杂度是不能满足要求的
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {
        int len = nums.length;
        int[][] dp = new int[len][len];
        int res = 0;
        //dp初始化,i==j的场景
        for (int i = 0; i < len; i++) {
            dp[i][i] = nums[i];
            if (nums[i] == k) {
                res++;
            }
        }
        //遍历所有场景
        for (int i = len - 2; i >= 0; i--) {
            for (int j = i + 1; j < len; j++) {
                dp[i][j] = dp[i + 1][j] + nums[i];
                if (dp[i][j] == k) {
                    res++;
                }
            }
        }
        return res;
    }

    /**
     * 上面的解法做下优化，由于dp只是依赖上一行的结果，因此我们可以只保留一行的数据
     * <p>
     * 哈，这么改又超出时间限制了
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum2(int[] nums, int k) {
        int len = nums.length;
        int[] dp0 = new int[len], dp1 = new int[len];
        int res = 0;
        //遍历所有场景
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i; j < len; j++) {
                if (i == j) {
                    dp1[j] = nums[i];
                } else {
                    dp1[j] = dp0[j] + nums[i];
                }
                if (dp1[j] == k) {
                    res++;
                }
                //更新dp1到dp0
                for (int l = 0; l < len; l++) {
                    dp0[l] = dp1[l];
                }
            }
        }
        return res;
    }

    /**
     * 前缀和的解法
     * f(i,j)表示[i,j)的区间
     * f(i,j)=f(0,j)-f(0,i)
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum3(int[] nums, int k) {
        int len = nums.length;
        //计算前缀和
        int[] prefixSum = new int[len + 1];
        for (int i = 1; i <= len; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }
        int res = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j <= len; j++) {
                int sum = prefixSum[j] - prefixSum[i];
                if (sum == k) {
                    res++;
                }
            }
        }
        return res;
    }

    /**
     * 前缀和优化
     * <p>
     * f(i,j)=f(j)-f(i)
     * <p>
     * f(i,j)=k=f(j)-f(i)，其中i<j。那么我们使用一个map存储起来是否能够把时间复杂度简化成O(n)
     * <p>
     * f(j)-k=f(i)
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum4(int[] nums, int k) {
        int len = nums.length;
        //计算前缀和
        Map<Integer, Integer> map = new HashMap<>(len);
        map.put(0, 1);
        int prefixSum = 0;
        int res = 0;
        for (int i = 0; i < len; i++) {
            prefixSum += nums[i - 1];
            res += map.getOrDefault(prefixSum - k, 0);
            map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
        }
        return res;
    }
}
