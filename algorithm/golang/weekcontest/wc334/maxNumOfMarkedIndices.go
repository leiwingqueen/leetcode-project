package wc334

import "sort"

func maxNumOfMarkedIndices(nums []int) int {
	n := len(nums)
	if n <= 1 {
		return 0
	}
	sort.Ints(nums)
	l := 0
	r := (n + 1) / 2
	res := 0
	for r < n {
		for r < n && nums[l]*2 > nums[r] {
			r++
		}
		if r == n {
			break
		}
		l++
		r++
		res += 2
	}
	return res
}
