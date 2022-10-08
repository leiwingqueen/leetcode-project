package com.liyongquan.hash;

// 给定两个大小相等的数组 nums1 和 nums2，nums1 相对于 nums 的优势可以用满足 nums1[i] > nums2[i] 的索引 i 的数目来描述。
//
//返回 nums1 的任意排列，使其相对于 nums2 的优势最大化。
//
// 
//
//示例 1：
//
//输入：nums1 = [2,7,11,15], nums2 = [1,10,4,11]
//输出：[2,11,7,15]
//示例 2：
//
//输入：nums1 = [12,24,8,32], nums2 = [13,25,32,11]
//输出：[24,32,8,12]
// 
//
//提示：
//
//1 <= nums1.length <= 105
//nums2.length == nums1.length
//0 <= nums1[i], nums2[i] <= 109
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/advantage-shuffle
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

// 给定两个大小相等的数组 nums1 和 nums2，nums1 相对于 nums 的优势可以用满足 nums1[i] > nums2[i] 的索引 i 的数目来描述。
//
//返回 nums1 的任意排列，使其相对于 nums2 的优势最大化。
//
// 
//
//示例 1：
//
//输入：nums1 = [2,7,11,15], nums2 = [1,10,4,11]
//输出：[2,11,7,15]
//示例 2：
//
//输入：nums1 = [12,24,8,32], nums2 = [13,25,32,11]
//输出：[24,32,8,12]
// 
//
//提示：
//
//1 <= nums1.length <= 105
//nums2.length == nums1.length
//0 <= nums1[i], nums2[i] <= 109
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/advantage-shuffle
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

public class AdvantageCount {
    public int[] advantageCount(int[] nums1, int[] nums2) {
        int n = nums1.length;
        Arrays.sort(nums1);
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i] = new int[]{i, nums2[i]};
        }
        Arrays.sort(arr, Comparator.comparingInt(o -> o[1]));
        int p1 = 0, p2 = 0;
        int[] res = new int[n];
        Queue<Integer> skip = new LinkedList<>();
        while (p1 < n) {
            while (p1 < n && nums1[p1] <= arr[p2][1]) {
                skip.add(nums1[p1]);
                p1++;
            }
            if (p1 == n) {
                break;
            }
            res[arr[p2][0]] = nums1[p1];
            p1++;
            p2++;
        }
        while (p2 < n) {
            res[arr[p2][0]] = skip.poll();
            p2++;
        }
        return res;
    }
}
