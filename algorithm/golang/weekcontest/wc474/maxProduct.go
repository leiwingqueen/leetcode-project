package wc474

import "sort"

func maxProduct(nums []int) int64 {
	n := len(nums)
	for i := 0; i < n; i++ {
		if nums[i] < 0 {
			nums[i] = -nums[i]
		}
	}
	sort.Ints(nums)
	return int64(nums[n-1]) * int64(nums[n-2]) * 100_000
}
