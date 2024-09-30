package bwc140

import "math"

func minElement(nums []int) int {
	replace := func(num int) int {
		sum := 0
		for num > 0 {
			sum += num % 10
			num /= 10
		}
		return sum
	}
	res := math.MaxInt
	for _, num := range nums {
		res = min(res, replace(num))
	}
	return res
}
