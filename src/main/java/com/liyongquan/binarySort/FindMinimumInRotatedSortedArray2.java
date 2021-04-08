package com.liyongquan.binarySort;

//假设按照升序排序的数组在预先未知的某个点上进行了旋转。
//
// ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
//
// 请找出其中最小的元素。
//
// 注意数组中可能存在重复的元素。
//
// 示例 1：
//
// 输入: [1,3,5]
//输出: 1
//
// 示例 2：
//
// 输入: [2,2,2,0,1]
//输出: 0
//
// 说明：
//
//
// 这道题是 寻找旋转排序数组中的最小值 的延伸题目。
// 允许重复会影响算法的时间复杂度吗？会如何影响，为什么？
//
// Related Topics 数组 二分查找
// 👍 263 👎 0


public class FindMinimumInRotatedSortedArray2 {
    /**
     * 二分查找
     *
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {
        int len = nums.length;
        int l = 0, r = len - 1;
        while (l < r) {
            if (r - l == 1) {
                return Math.min(nums[l], nums[r]);
            }
            int mid = l + (r - l) / 2;
            //消除左右两边和中间相等的数字(有可能退化成线性查找)
            while (l < mid && nums[l] == nums[mid]) {
                l++;
            }
            while (r > mid && nums[r] == nums[mid]) {
                r--;
            }
            if (l >= r) {
                return nums[mid];
            }
            if (l == mid || r == mid) {
                continue;
            }
            //左边是严格升序
            if (nums[mid] > nums[l]) {
                //右侧小于左侧,则最小值在右边
                if (nums[r] < nums[mid]) {
                    l = mid + 1;
                } else {
                    //严格升序
                    return nums[l];
                }
            } else {
                //右侧严格升序
                //这里是由于下一次的mid一定在r的左侧，区间一定会继续缩小，这里不会导致死循环
                r = mid;
            }
        }
        return nums[l];
    }

    //TODO:比较最右边端点其实会更简单
}
