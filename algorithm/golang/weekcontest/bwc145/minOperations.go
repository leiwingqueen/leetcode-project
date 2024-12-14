package bwc145

import "sort"

func minOperations(nums []int, k int) int {
	sort.Ints(nums)
	if k > nums[0] {
		return -1
	}
	mp := make(map[int]int)
	for _, num := range nums {
		mp[num]++
	}
	if k == nums[0] {
		return len(mp) - 1
	} else {
		return len(mp)
	}
}
