package wc376

import "sort"

func divideArray(nums []int, k int) [][]int {
	n := len(nums)
	sort.Ints(nums)
	l := 0
	var res [][]int
	for l < n {
		if nums[l+2]-nums[l] <= k {
			res = append(res, nums[l:l+3])
		} else {
			return [][]int{}
		}
		l += 3
	}
	return res
}
