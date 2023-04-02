package bwc101

import "math"

func minNumber(nums1 []int, nums2 []int) int {
	res := math.MaxInt32
	for _, i := range nums1 {
		for _, j := range nums2 {
			if i == j {
				if i < res {
					res = i
				}
			} else {
				num := i*10 + j
				if i > j {
					num = j*10 + i
				}
				if num < res {
					res = num
				}
			}
		}
	}
	return res
}
