package com.liyongquan.weeklycontest.wc299;

public class MaximumsSplicedArray {
    public int maximumsSplicedArray(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[] arr = new int[n];
        int s1 = 0, s2 = 0;
        for (int i = 0; i < n; i++) {
            s1 += nums1[i];
            s2 += nums2[i];
        }
        for (int i = 0; i < n; i++) {
            arr[i] = nums1[i] - nums2[i];
        }
        int sum = 0;
        int min1 = 0;
        int max1 = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
            max1 = Math.max(sum - min1, max1);
            min1 = Math.min(min1, sum);
        }
        sum = 0;
        int min2 = 0;
        int max2 = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
            min2 = Math.min(sum - max2, min2);
            max2 = Math.max(max2, sum);
        }
        return Math.max(s2 + max1, s1 - min2);
    }
}
