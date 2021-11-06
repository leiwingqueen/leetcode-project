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
import java.util.Iterator;
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
     * <p>
     * 字典序需要额外进行遍历和比较
     *
     * @param coins
     * @param maxJump
     * @return
     */
    public List<Integer> cheapestJump(int[] coins, int maxJump) {
        int len = coins.length;
        int[] dp = new int[len];
        //这里会有一个字典序的问题
        int[] path = new int[len];
        path[0] = -1;
        dp[0] = coins[0];
        for (int i = 1; i < len; i++) {
            int min = -1;
            int preIdx = -1;
            if (coins[i] >= 0) {
                for (int j = maxJump; j >= 1; j--) {
                    if (i - j < 0 || dp[i - j] < 0) {
                        continue;
                    }
                    if (min == -1 || dp[i - j] < min) {
                        min = dp[i - j];
                        preIdx = i - j;
                    } else if (dp[i - j] == min) {
                        //这种场景需要比较字典序
                        if (compare(path, i - j, preIdx, i + 1) < 0) {
                            preIdx = i - j;
                        }
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
        return build(path, len - 1);
    }

    private int compare(int[] path, int p1, int p2, int last) {
        List<Integer> l1 = build(path, p1);
        List<Integer> l2 = build(path, p2);
        l1.add(last);
        l2.add(last);
        Iterator<Integer> it1 = l1.iterator();
        Iterator<Integer> it2 = l2.iterator();
        while (it1.hasNext() && it2.hasNext()) {
            Integer n1 = it1.next();
            Integer n2 = it2.next();
            if (n1 != n2) {
                return n1 - n2;
            }
        }
        return l1.size() - l2.size();
    }

    private List<Integer> build(int[] path, int p) {
        LinkedList<Integer> list = new LinkedList<>();
        list.offerFirst(p + 1);
        while (path[p] >= 0) {
            list.offerFirst(path[p] + 1);
            p = path[p];
        }
        return list;
    }
}
