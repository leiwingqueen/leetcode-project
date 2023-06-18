package wc350

import "sort"

func findValueOfPartition(nums []int) int {
	n := len(nums)
	sort.Ints(nums)
	res := nums[n-1] - nums[0]
	for i := 0; i < n-1; i++ {
		if nums[i+1]-nums[i] < res {
			res = nums[i+1] - nums[i]
		}
	}
	return res
}
