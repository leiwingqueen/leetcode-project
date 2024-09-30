package bwc140

import "sort"

func maximumTotalSum(maximumHeight []int) int64 {
	sort.Ints(maximumHeight)
	n := len(maximumHeight)
	var res int64
	pre := maximumHeight[n-1] + 1
	for i := n - 1; i >= 0; i-- {
		h := min(maximumHeight[i], pre-1)
		if h <= 0 {
			return -1
		}
		res += int64(h)
		pre = h
	}
	return res
}
