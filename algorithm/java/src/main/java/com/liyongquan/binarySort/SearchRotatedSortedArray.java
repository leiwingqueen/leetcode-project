package com.liyongquan.binarySort;

/**
 * 33. 搜索旋转排序数组
 * <p>
 * 整数数组 nums 按升序排列，数组中的值 互不相同 。
 * <p>
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
 * <p>
 * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的索引，否则返回 -1 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [4,5,6,7,0,1,2], target = 0
 * 输出：4
 * 示例 2：
 * <p>
 * 输入：nums = [4,5,6,7,0,1,2], target = 3
 * 输出：-1
 * 示例 3：
 * <p>
 * 输入：nums = [1], target = 0
 * 输出：-1
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 5000
 * -10^4 <= nums[i] <= 10^4
 * nums 中的每个值都 独一无二
 * nums 肯定会在某个点上旋转
 * -10^4 <= target <= 10^4
 *  
 * <p>
 * 进阶：你可以设计一个时间复杂度为 O(log n) 的解决方案吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SearchRotatedSortedArray {
    /**
     * 暴力无视
     * <p>
     * 居然性能还有100%
     * <p>
     * 时间复杂度O(n)
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 先找到翻转点
     * 然后再做二分查找
     * <p>
     * 时间复杂度O(log(n))
     *
     * @param nums
     * @param target
     * @return
     */
    public int search2(int[] nums, int target) {
        if (nums.length == 1) {
            return target == nums[0] ? 0 : -1;
        }
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int middle = (l + r) / 2;
            if (nums[middle] >= nums[0]) {
                l = middle + 1;
            } else {
                r = middle;
            }
        }
        int k = nums[l] > nums[l - 1] ? l + 1 : l;
        if (target >= nums[0]) {
            return binarySearch(nums, 0, k - 1, target);
        } else {
            return binarySearch(nums, k, nums.length - 1, target);
        }
    }

    private int binarySearch(int[] nums, int l, int r, int target) {
        while (l <= r) {
            int middle = (l + r) / 2;
            if (target == nums[middle]) {
                return middle;
            } else if (target < nums[middle]) {
                r = middle - 1;
            } else {
                l = middle + 1;
            }
        }
        return -1;
    }
}
