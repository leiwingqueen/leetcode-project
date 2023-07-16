package wc354

import "sort"

func maximumBeauty(nums []int, k int) int {
	sort.Ints(nums)
	n := len(nums)
	res := 0
	l, r := 0, 0
	for r < n {
		if nums[l]+k >= nums[r]-k {
			if r-l+1 > res {
				res = r - l + 1
			}
			r++
		} else {
			l++
		}
	}
	if r-l > res {
		res = r - l
	}
	return res
}
