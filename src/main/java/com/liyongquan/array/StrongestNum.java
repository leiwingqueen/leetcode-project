package com.liyongquan.array;

import java.util.Arrays;
import java.util.Collections;

/**
 * https://leetcode-cn.com/contest/weekly-contest-192/problems/the-k-strongest-values-in-an-array/
 * 给你一个整数数组 arr 和一个整数 k 。
 * <p>
 * 设 m 为数组的中位数，只要满足下述两个前提之一，就可以判定 arr[i] 的值比 arr[j] 的值更强：
 * <p>
 * |arr[i] - m| > |arr[j] - m|
 * |arr[i] - m| == |arr[j] - m|，且 arr[i] > arr[j]
 * 请返回由数组中最强的 k 个值组成的列表。答案可以以 任意顺序 返回。
 * <p>
 * 中位数 是一个有序整数列表中处于中间位置的值。形式上，如果列表的长度为 n ，那么中位数就是该有序列表（下标从 0 开始）中位于 ((n - 1) / 2) 的元素。
 * <p>
 * 例如 arr = [6, -3, 7, 2, 11]，n = 5：数组排序后得到 arr = [-3, 2, 6, 7, 11] ，数组的中间位置为 m = ((5 - 1) / 2) = 2 ，中位数 arr[m] 的值为 6 。
 * 例如 arr = [-7, 22, 17, 3]，n = 4：数组排序后得到 arr = [-7, 3, 17, 22] ，数组的中间位置为 m = ((4 - 1) / 2) = 1 ，中位数 arr[m] 的值为 3 。
 */
public class StrongestNum {
    /**
     * 双指针
     * @param arr
     * @param k
     * @return
     */
    public int[] getStrongest(int[] arr, int k) {
        Arrays.sort(arr);
        int middle = (arr.length - 1) / 2;
        int[] result = new int[k];
        int left = 0, right = arr.length - 1;
        int p = 0;
        while (p < k) {
            if (arr[right] - arr[middle] >= arr[middle] - arr[left]) {
                result[p] = arr[right];
                right--;
            } else {
                result[p] = arr[left];
                left++;
            }
            p++;
        }
        return result;
    }
}
