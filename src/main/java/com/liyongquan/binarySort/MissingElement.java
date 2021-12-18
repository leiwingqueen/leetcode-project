package com.liyongquan.binarySort;

//1060. 有序数组中的缺失元素
//现有一个按 升序 排列的整数数组 nums ，其中每个数字都 互不相同 。
//
//给你一个整数 k ，请你找出并返回从数组最左边开始的第 k 个缺失数字。
//
// 
//
//示例 1：
//
//输入：nums = [4,7,9,10], k = 1
//输出：5
//解释：第一个缺失数字为 5 。
//示例 2：
//
//输入：nums = [4,7,9,10], k = 3
//输出：8
//解释：缺失数字有 [5,6,8,...]，因此第三个缺失数字为 8 。
//示例 3：
//
//输入：nums = [1,2,4], k = 3
//输出：6
//解释：缺失数字有 [3,5,6,7,...]，因此第三个缺失数字为 6 。
// 
//
//提示：
//
//1 <= nums.length <= 5 * 104
//1 <= nums[i] <= 107
//nums 按 升序 排列，其中所有元素 互不相同 。
//1 <= k <= 108
// 
//
//进阶：你可以设计一个对数时间复杂度（即，O(log(n))）的解决方案吗？
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/missing-element-in-sorted-array
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class MissingElement {
    public int missingElement(int[] nums, int k) {
        int len = nums.length;
        int l = nums[0], r = nums[len - 1];
        //计算缺失的总数字
        int cnt = r - l + 1 - len;
        if (cnt < k) {
            return nums[len - 1] + k - cnt;
        }
        //二分查找，找到第一个缺失数字为k
        while (l < r) {
            int mid = l + (r - l) / 2;
            int missing = missing(nums, mid);
            if (missing <= k) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }


    //找到<=n的右边界，缺失的数字数量
    public int missing(int[] nums, int n) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = l + (r - l + 1) / 2;
            if (nums[mid] <= n) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        //[l,r]中间缺失的数字
        int cnt = nums[l] - nums[0] - l;
        //nums[r]~n缺失的数字
        cnt += n - nums[l];
        return cnt;
    }
}
