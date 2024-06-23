package wc403

import (
	"math"
	"sort"
)

func minimumAverage(nums []int) float64 {
	n := len(nums)
	sort.Ints(nums)
	res := math.MaxFloat64
	l, r := 0, n-1
	for l < r {
		f := float64(nums[l]+nums[r]) / float64(2)
		res = min(res, f)
		l++
		r--
	}
	return res
}
