package wc400

import "math"

func minimumDifference(nums []int, k int) int {
	abs := func(num int) int {
		if num > 0 {
			return num
		} else {
			return -num
		}
	}
	n := len(nums)
	res := math.MaxInt32
	for i := 0; i < n; i++ {
		for j := i; j < n; j++ {
			and := math.MaxInt32
			for l := i; l <= j; l++ {
				and ^= nums[l]
			}
			res = min(res, abs(and-k))
		}
	}
	return res
}
