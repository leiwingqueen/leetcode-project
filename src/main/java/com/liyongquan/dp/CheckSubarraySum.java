package com.liyongquan.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个包含 非负数 的数组和一个目标 整数 k，编写一个函数来判断该数组是否含有连续的子数组，其大小至少为 2，且总和为 k 的倍数，即总和为 n*k，其中 n 也是一个整数。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：[23,2,4,6,7], k = 6
 * 输出：True
 * 解释：[2,4] 是一个大小为 2 的子数组，并且和为 6。
 * 示例 2：
 * <p>
 * 输入：[23,2,6,4,7], k = 6
 * 输出：True
 * 解释：[23,2,6,4,7]是大小为 5 的子数组，并且和为 42。
 *  
 * <p>
 * 说明：
 * <p>
 * 数组的长度不会超过 10,000 。
 * 你可以认为所有数字总和在 32 位有符号整数范围内。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/continuous-subarray-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CheckSubarraySum {
    /**
     * 暴力+前缀和
     *
     * @param nums
     * @param k
     * @return
     */
    public boolean checkSubarraySum(int[] nums, int k) {
        int len = nums.length;
        //计算前缀和
        int[] prefixSum = new int[len + 1];
        for (int i = 1; i <= len; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }
        for (int i = 0; i < len; i++) {
            for (int j = i + 2; j <= len; j++) {
                int sum = prefixSum[j] - prefixSum[i];
                if (k == 0 && sum == 0 || k != 0 && sum % k == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 前缀和-优化
     * <p>
     * f(i,j)为[i,j)的和，f(i,j)=f(j)-f(i)=n*k
     * <p>
     * 当k==0:
     * f(j)-f(i)=0
     * =>
     * f(j)=f(i)
     * <p>
     * 当k!=0:
     * (f(j)-f(i))%k=0
     * ==>
     * f(j)%k-f(i)%k=0
     * =>
     * f(j)%k=f(i)%k
     * <p>
     * 利用一个map存放前缀和扫描即可
     * <p>
     *
     * @param nums
     * @param k
     * @return
     */
    public boolean checkSubarraySum2(int[] nums, int k) {
        int len = nums.length;
        //key-前缀和，value--最早出现的位置(用来排除只有一个数的场景)
        Map<Integer, Integer> map = new HashMap<>(len);
        int prefixSum = 0;
        for (int i = 1; i <= len; i++) {
            prefixSum += nums[i - 1];
            if (k != 0) {
                prefixSum %= k;
            }
            if (map.containsKey(prefixSum)) {
                int idx = map.get(prefixSum);
                //排除长度为1的场景
                if (i - idx > 1) {
                    return true;
                }
            } else {
                map.put(prefixSum, i - 1);
            }
        }
        return false;
    }
}
