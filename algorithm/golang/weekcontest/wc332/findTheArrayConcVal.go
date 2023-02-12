package wc332

import "strconv"

func findTheArrayConcVal(nums []int) int64 {
	var res int64
	l := 0
	r := len(nums) - 1
	for l <= r {
		if l == r {
			res += int64(nums[l])
			break
		}
		num, _ := strconv.Atoi(strconv.Itoa(nums[l]) + strconv.Itoa(nums[r]))
		res += int64(num)
		l++
		r--
	}
	return res
}
