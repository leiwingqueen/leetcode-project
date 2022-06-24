package com.liyongquan.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//给你一个由 无重复 正整数组成的集合 nums ，请你找出并返回其中最大的整除子集 answer ，子集中每一元素对 (answer[i], answer[
//j]) 都应当满足：
//
// answer[i] % answer[j] == 0 ，或
// answer[j] % answer[i] == 0
//
//
// 如果存在多个有效解子集，返回其中任何一个均可。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,2,3]
//输出：[1,2]
//解释：[1,3] 也会被视为正确答案。
//
//
// 示例 2：
//
//
//输入：nums = [1,2,4,8]
//输出：[1,2,4,8]
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 1000
// 1 <= nums[i] <= 2 * 109
// nums 中的所有整数 互不相同
//
// Related Topics 数学 动态规划
// 👍 255 👎 0


public class LargestDivisibleSubset {
    /**
     * 为了简化整个dp的过程
     * <p>
     * 假设数组nums是升序的，i<j，满足整除条件，则必然有nums[j]%nums[i]=0
     * <p>
     * 我们假设要在一个已有的结果集中加入一个数字，一般要和集合中的所有数字进行比较。
     * 但是假设我们新加入的数字>集合中的任意一个数字，那我们只需要检查和集合中的最大数比较即可
     * <p>
     * 假设c>b>a，有b%a=0,c%b=0，则有c%a=0
     * <p>
     * 假设f(n)是前n个数字的最大整除子集，其中第N个数字必须选择(为什么？因为这里可能存在多个解，但是一旦确定了最后一个数字必须选择，后面能否加入新数字也就同时确定了)
     * f(n)=max{f(k)}+1,其中1<=k<n，且nums[n-1]%nums[k-1]==0
     *
     * @param nums
     * @return
     */
    public List<Integer> largestDivisibleSubset(int[] nums) {
        int len = nums.length;
        Arrays.sort(nums);
        //存储最大结果
        int[] dp = new int[len];
        //存储上一次的节点下标，这样就减少list拷贝的逻辑
        int[] res = new int[len];
        Arrays.fill(res, -1);
        dp[0] = 1;
        int maxIdx = 0, max = dp[0];
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0) {
                    if (dp[j] > dp[i]) {
                        dp[i] = dp[j];
                        res[i] = j;
                    }
                }
            }
            dp[i] += 1;
            if (dp[i] > max) {
                max = dp[i];
                maxIdx = i;
            }
        }
        //组装结果
        List<Integer> list = new ArrayList<>();
        list.add(nums[maxIdx]);
        int idx = maxIdx;
        while (res[idx] >= 0) {
            list.add(nums[res[idx]]);
            idx = res[idx];
        }
        return list;
    }
}
