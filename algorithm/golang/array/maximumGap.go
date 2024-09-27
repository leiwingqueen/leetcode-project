package array

import "sort"

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
