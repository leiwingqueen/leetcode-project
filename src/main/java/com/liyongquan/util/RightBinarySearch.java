package com.liyongquan.util;

/**
 * 二分查找变体，查找右边界
 */
public class RightBinarySearch implements IBinarySearch {
    @Override
    public int search(int[] nums, int target) {
        if (nums.length <= 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int middle = (left + right) / 2;
            if (nums[middle] <= target) {
                left = middle;
            } else {
                right = middle - 1;
            }
        }
        return nums[left] == target ? left : -1;
    }
}
