package wc332

import "sort"

func countFairPairs(nums []int, lower int, upper int) int64 {
	n := len(nums)
	sort.Ints(nums)
	cal := func(mx int) int64 {
		l := 0
		r := 1
		var res int64
		for r < n {
			if nums[r]+nums[l] <= mx {
				r++
			} else {
				res += int64(r - l - 1)
				l++
				r = l + 1
			}
		}
		res += int64(r - l - 1)
		return res
	}
	return cal(upper) - cal(lower-1)
}
