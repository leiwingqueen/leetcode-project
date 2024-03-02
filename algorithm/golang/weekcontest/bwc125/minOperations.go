package bwc125

import "sort"

func minOperations(nums []int, k int) int {
	sort.Ints(nums)
	idx := sort.Search(len(nums), func(i int) bool {
		return nums[i] >= k
	})
	return idx
}
