package bwc105

import "sort"

func maxStrength(nums []int) int64 {
	if len(nums) == 1 {
		return int64(nums[0])
	}
	var negative []int
	zero := 0
	var res int64
	res = 1
	positive := false
	for _, num := range nums {
		if num > 0 {
			positive = true
			res *= int64(num)
		} else if num < 0 {
			negative = append(negative, num)
		} else {
			zero++
		}
	}
	if positive {
		if len(negative) > 0 {
			sort.Ints(negative)
			i := 0
			for i+1 < len(negative) {
				res *= int64(negative[i]) * int64(negative[i+1])
				i += 2
			}
		}
	} else {
		// 全是0和负数
		var r int64
		r = 1
		if len(negative) > 1 {
			sort.Ints(negative)
			i := 0
			for i+1 < len(negative) {
				r *= int64(negative[i]) * int64(negative[i+1])
				i += 2
			}
			return r
		} else {
			return 0
		}
	}
	return res
}
