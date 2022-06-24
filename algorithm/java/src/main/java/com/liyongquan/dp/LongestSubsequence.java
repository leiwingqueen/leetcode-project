package com.liyongquan.dp;

//1218. 最长定差子序列
//给你一个整数数组 arr 和一个整数 difference，请你找出并返回 arr 中最长等差子序列的长度，该子序列中相邻元素之间的差等于 difference 。
//
//子序列 是指在不改变其余元素顺序的情况下，通过删除一些元素或不删除任何元素而从 arr 派生出来的序列。
//
// 
//
//示例 1：
//
//输入：arr = [1,2,3,4], difference = 1
//输出：4
//解释：最长的等差子序列是 [1,2,3,4]。
//示例 2：
//
//输入：arr = [1,3,5,7], difference = 1
//输出：1
//解释：最长的等差子序列是任意单个元素。
//示例 3：
//
//输入：arr = [1,5,7,8,5,3,4,2,1], difference = -2
//输出：4
//解释：最长的等差子序列是 [7,5,3,1]。
// 
//
//提示：
//
//1 <= arr.length <= 105
//-104 <= arr[i], difference <= 104
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/longest-arithmetic-subsequence-of-given-difference
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import java.util.HashMap;
import java.util.Map;

/**
 * @author liyongquan
 * @date 2021/11/5
 */
public class LongestSubsequence {
    /**
     * 时间复杂度O(n^2)，会超时
     *
     * @param arr
     * @param difference
     * @return
     */
    public int longestSubsequence(int[] arr, int difference) {
        int[] dp = new int[arr.length];
        dp[0] = 1;
        int res = 1;
        for (int i = 1; i < arr.length; i++) {
            dp[i] = 1;
            //核心问题在这里，能不能快速找到arr[j]=arr[i]-difference的下标
            for (int j = 0; j < i; j++) {
                if (arr[j] + difference == arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    /**
     * 上面的优化方案
     * <p>
     * 时间复杂度O(n)
     * 空间复杂度O(n)
     *
     * @param arr
     * @param difference
     * @return
     */
    public int longestSubsequence2(int[] arr, int difference) {
        int[] dp = new int[arr.length];
        dp[0] = 1;
        int res = 1;
        //key-最后一个元素的值,value-等差数列最大长度
        Map<Integer, Integer> map = new HashMap<>();
        map.put(arr[0], 1);
        for (int i = 1; i < arr.length; i++) {
            dp[i] = 1;
            if (map.containsKey(arr[i] - difference)) {
                dp[i] = map.get(arr[i] - difference) + 1;
            }
            map.put(arr[i], Math.max(map.getOrDefault(arr[i], 1), dp[i]));
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    /**
     * 由于历史的dp值已经不需要了，我们可以再做一次简化
     *
     * @param arr
     * @param difference
     * @return
     */
    public int longestSubsequence3(int[] arr, int difference) {
        int res = 1;
        //key-最后一个元素的值,value-等差数列最大长度
        Map<Integer, Integer> map = new HashMap<>();
        map.put(arr[0], 1);
        for (int i = 1; i < arr.length; i++) {
            int dp = 1;
            if (map.containsKey(arr[i] - difference)) {
                dp = map.get(arr[i] - difference) + 1;
            }
            map.put(arr[i], Math.max(map.getOrDefault(arr[i], 1), dp));
            res = Math.max(res, dp);
        }
        return res;
    }
}
