package com.liyongquan.array;

//786. 第 K 个最小的素数分数
//给你一个按递增顺序排序的数组 arr 和一个整数 k 。数组 arr 由 1 和若干 素数  组成，且其中所有整数互不相同。
//
//对于每对满足 0 < i < j < arr.length 的 i 和 j ，可以得到分数 arr[i] / arr[j] 。
//
//那么第 k 个最小的分数是多少呢?  以长度为 2 的整数数组返回你的答案, 这里 answer[0] == arr[i] 且 answer[1] == arr[j] 。
//
// 
//示例 1：
//
//输入：arr = [1,2,3,5], k = 3
//输出：[2,5]
//解释：已构造好的分数,排序后如下所示:
//1/5, 1/3, 2/5, 1/2, 3/5, 2/3
//很明显第三个最小的分数是 2/5
//示例 2：
//
//输入：arr = [1,7], k = 1
//输出：[1,7]
// 
//
//提示：
//
//2 <= arr.length <= 1000
//1 <= arr[i] <= 3 * 104
//arr[0] == 1
//arr[i] 是一个 素数 ，i > 0
//arr 中的所有数字 互不相同 ，且按 严格递增 排序
//1 <= k <= arr.length * (arr.length - 1) / 2
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/k-th-smallest-prime-fraction
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author liyongquan
 * @date 2021/11/29
 */
public class KthSmallestPrimeFraction {
    /**
     * 先来一个暴力解法
     * <p>
     * 时间复杂度O(n^2)
     *
     * @param arr
     * @param k
     * @return
     */
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        int len = arr.length;
        List<int[]> list = new ArrayList<>(len * (len - 1) / 2);
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                list.add(new int[]{arr[i], arr[j]});
            }
        }
        list.sort((o1, o2) -> {
            double d1 = (double) o1[0] / o1[1];
            double d2 = (double) o2[0] / o2[1];
            if (d1 == d2) {
                return 0;
            } else if (d1 < d2) {
                return -1;
            } else {
                return 1;
            }
        });
        return list.get(k - 1);
    }
}
