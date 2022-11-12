package bwc91

import "sort"

func distinctAverages(nums []int) int {
	sort.Ints(nums)
	mp := make(map[int]struct{})
	l := 0
	r := len(nums) - 1
	for l < r {
		mp[nums[l]+nums[r]] = struct{}{}
		l++
		r--
	}
	return len(mp)
}
