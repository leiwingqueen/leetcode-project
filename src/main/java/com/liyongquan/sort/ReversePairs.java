package com.liyongquan.sort;

//在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
//
// 
//
//示例 1:
//
//输入: [7,5,6,4]
//输出: 5
// 
//
//限制：
//
//0 <= 数组长度 <= 50000
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

public class ReversePairs {
    /**
     * 题目很短，但很难。
     * <p>
     * 归并排序
     *
     * @param nums
     * @return
     */
    public int reversePairs(int[] nums) {
        if (nums.length <= 1) {
            return 0;
        }
        return mergeSort(nums, new int[nums.length], 0, nums.length - 1);
    }

    private int mergeSort(int[] nums, int[] tmp, int left, int right) {
        if (left >= right) {
            return 0;
        }
        int mid = left + (right - left) / 2;
        int r1 = mergeSort(nums, tmp, left, mid);
        int r2 = mergeSort(nums, tmp, mid + 1, right);
        //已经排序好
        if (nums[mid] <= nums[mid + 1]) {
            return r1 + r2;
        }
        //归并过程统计数量
        for (int i = left; i <= right; i++) {
            tmp[i] = nums[i];
        }
        int p1 = left, p2 = mid + 1;
        int res = r1 + r2;
        for (int i = left; i <= right; i++) {
            //边界情况处理
            if (p1 > mid) {
                nums[i] = tmp[p2++];
            } else if (p2 > right) {
                nums[i] = tmp[p1++];
            } else if (tmp[p1] <= tmp[p2]) {
                nums[i] = tmp[p1++];
            } else {
                nums[i] = tmp[p2++];
                res += mid - p1 + 1;
            }
        }
        return res;
    }
}
