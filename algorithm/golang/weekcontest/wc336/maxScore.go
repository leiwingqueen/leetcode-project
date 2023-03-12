package wc336

import "sort"

func maxScore(nums []int) int {
	sort.Slice(nums, func(i, j int) bool {
		return nums[i] > nums[j]
	})
	cnt := 0
	sum := 0
	for _, num := range nums {
		sum += num
		if sum > 0 {
			cnt++
		} else {
			break
		}
	}
	return cnt
}
