package bwc120

import "sort"

func largestPerimeter(nums []int) int64 {
	n := len(nums)
	sort.Ints(nums)
	var sum int64
	sum += int64(nums[0] + nums[1])
	res := int64(-1)
	for i := 2; i < n; i++ {
		if sum > int64(nums[i]) {
			res = sum + int64(nums[i])
		}
		sum += int64(nums[i])
	}
	return res
}
