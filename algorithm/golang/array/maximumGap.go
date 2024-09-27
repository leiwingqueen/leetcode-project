package array

import "sort"

// 给定一个无序的数组 nums，返回 数组在排序之后，相邻元素之间最大的差值 。如果数组元素个数小于 2，则返回 0 。
//
//您必须编写一个在「线性时间」内运行并使用「线性额外空间」的算法。
//
//
//
//示例 1:
//
//输入: nums = [3,6,9,1]
//输出: 3
//解释: 排序后的数组是 [1,3,6,9], 其中相邻元素 (3,6) 和 (6,9) 之间都存在最大差值 3。
//示例 2:
//
//输入: nums = [10]
//输出: 0
//解释: 数组元素个数小于 2，因此返回 0。
//
//
//提示:
//
//1 <= nums.length <= 105
//0 <= nums[i] <= 109

// 作弊写法
func maximumGap(nums []int) int {
	sort.Ints(nums)
	n := len(nums)
	res := 0
	for i := 1; i < n; i++ {
		res = max(res, nums[i]-nums[i-1])
	}
	return res
}
