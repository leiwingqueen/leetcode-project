package wc277

import "sort"

func countElements(nums []int) int {
	sort.Ints(nums)
	l := 0
	r := len(nums) - 1
	for l <= r && (l == 0 || nums[l] == nums[l-1]) {
		l++
	}
	for l <= r && (r == len(nums)-1 || nums[r] == nums[r+1]) {
		r--
	}
	if l > r {
		return 0
	} else {
		return r - l + 1
	}
}
