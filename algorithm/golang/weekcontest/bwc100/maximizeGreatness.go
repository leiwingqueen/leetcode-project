package bwc100

import "sort"

func maximizeGreatness(nums []int) int {
	n := len(nums)
	sort.Ints(nums)
	p1, p2 := 0, 0
	res := 0
	for p2 < n {
		for p2 < n && nums[p2] <= nums[p1] {
			p2++
		}
		if p2 == n {
			return res
		}
		res++
		p1++
		p2++
	}
	return res
}
