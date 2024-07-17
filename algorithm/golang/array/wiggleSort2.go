package array

import "sort"

// 在一个整数数组中，“峰”是大于或等于相邻整数的元素，相应地，“谷”是小于或等于相邻整数的元素。例如，在数组{5, 8, 4, 2, 3, 4, 6}中，{8, 6}是峰， {5, 2}是谷。现在给定一个整数数组，将该数组按峰与谷的交替顺序排序。
//
//示例:
//
//输入: [5, 3, 1, 2, 3]
//输出: [5, 1, 3, 2, 3]
//提示：
//
//nums.length <= 10000

func wiggleSort2(nums []int) {
	n := len(nums)
	if n == 0 {
		return
	}
	sort.Ints(nums)
	arr1 := nums[n/2:]
	arr2 := nums[:n/2]
	p1, p2 := 0, 0
	var res []int
	for p1 < len(arr1) || p2 < len(arr2) {
		if p1 < len(arr1) {
			res = append(res, arr1[p1])
			p1++
		}
		if p2 < len(arr2) {
			res = append(res, arr2[p2])
			p2++
		}
	}
	copy(nums, res)
}
