package wc494

import "math"

func uniformArray2(nums1 []int) bool {
	minOdd, minEven := math.MaxInt, math.MaxInt
	for _, num := range nums1 {
		if num%2 == 0 {
			minEven = min(minEven, num)
		} else {
			minOdd = min(minOdd, num)
		}
	}
	if minEven == math.MaxInt || minOdd == math.MaxInt {
		return true
	}
	checkOdd := func() bool {
		for _, num := range nums1 {
			if num%2 == 0 {
				if num-minOdd < 1 {
					return false
				}
			}
		}
		return true
	}
	checkEven := func() bool {
		for _, num := range nums1 {
			if num%2 == 1 {
				if num-minOdd < 1 {
					return false
				}
			}
		}
		return true
	}
	return checkOdd() || checkEven()
}
