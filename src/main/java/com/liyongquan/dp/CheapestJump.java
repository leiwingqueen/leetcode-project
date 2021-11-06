package com.liyongquan.dp;

//656. 金币路径
//给定一个数组 A（下标从 1 开始）包含 N 个整数：A1，A2，……，AN 和一个整数 B。你可以从数组 A 中的任何一个位置（下标为 i）跳到下标 i+1，i+2，……，i+B 的任意一个可以跳到的位置上。如果你在下标为 i 的位置上，你需要支付 Ai 个金币。如果 Ai 是 -1，意味着下标为 i 的位置是不可以跳到的。
//
//现在，你希望花费最少的金币从数组 A 的 1 位置跳到 N 位置，你需要输出花费最少的路径，依次输出所有经过的下标（从 1 到 N）。
//
//如果有多种花费最少的方案，输出字典顺序最小的路径。
//
//如果无法到达 N 位置，请返回一个空数组。
//
// 
//
//样例 1 :
//
//输入: [1,2,4,-1,2], 2
//输出: [1,3,5]
// 
//
//样例 2 :
//
//输入: [1,2,4,-1,2], 1
//输出: []
// 
//
//注释 :
//
//路径 Pa1，Pa2，……，Pan 是字典序小于 Pb1，Pb2，……，Pbm 的，当且仅当第一个 Pai 和 Pbi 不同的 i 满足 Pai < Pbi，如果不存在这样的 i 那么满足 n < m。
//A1 >= 0。 A2, ..., AN （如果存在） 的范围是 [-1, 100]。
//A 数组的长度范围 [1, 1000].
//B 的范围 [1, 100].
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/coin-path
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author liyongquan
 * @date 2021/11/6
 */
public class CheapestJump {
    /**
     * dp表达式
     * <p>
     * f(n)=min{f(n-B),f(n-B+1),...,f(n-1)}+A[n-1]。需要字典排序，
     * f(1)=A[0]
     *
     * @param coins
     * @param maxJump
     * @return
     */
    public List<Integer> cheapestJump(int[] coins, int maxJump) {
        int len = coins.length;
        int[] dp = new int[len];
        int[] path = new int[len];
        path[0] = -1;
        dp[0] = coins[0];
        for (int i = 1; i < len; i++) {
            int min = -1;
            int preIdx = -1;
            if (coins[i] >= 0) {
                for (int j = maxJump; j >= 1; j--) {
                    if (i - j >= 0 && (min == -1 || (dp[i - j] >= 0 && dp[i - j] < min))) {
                        min = dp[i - j];
                        preIdx = i - j;
                    }
                }
            }
            if (min >= 0) {
                dp[i] = min + coins[i];
            } else {
                dp[i] = -1;
            }
            path[i] = preIdx;
        }
        if (dp[len - 1] < 0) {
            return Collections.emptyList();
        }
        //输出路径
        LinkedList<Integer> list = new LinkedList<>();
        int idx = len - 1;
        list.offerFirst(idx + 1);
        while (path[idx] >= 0) {
            list.offerFirst(path[idx] + 1);
            idx = path[idx];
        }
        return list;
    }
}
