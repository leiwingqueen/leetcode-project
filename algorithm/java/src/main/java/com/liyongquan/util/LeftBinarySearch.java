package com.liyongquan.util;

/**
 * 二分查找的变体，找左边界。
 * eg.nums=[1,2,2,7,8],target=2,返回1
 */
public class LeftBinarySearch implements IBinarySearch {
    @Override
    public int search(int[] nums, int target) {
        if (nums.length <= 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int middle = (left + right) / 2;
            if (nums[middle] >= target) {
                right = middle;
            } else {
                left = middle + 1;
            }
        }
        return nums[left] == target ? left : -1;
    }
}
