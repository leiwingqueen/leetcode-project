package wc526

import "math"

func minMergedInteger(nums []int) int {
	merge := func(num1, num2 int) int {
		size := 0
		tmp := num2
		for tmp > 0 {
			size++
			tmp /= 10
		}
		return num1*int(math.Pow10(size)) + num2
	}
	res := merge(nums[0], nums[1])
	for i := 2; i < len(nums); i += 2 {
		res = min(res, merge(nums[i], nums[i+1]))
	}
	return res
}
