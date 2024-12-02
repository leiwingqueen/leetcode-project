package wc426

import "math"

func getLargestOutlier(nums []int) int {
	sum := 0
	mp := make(map[int]int)
	for _, num := range nums {
		sum += num
		mp[num]++
	}
	res := math.MinInt
	for _, num := range nums {
		d := sum - num
		mp[num]--
		if d%2 == 0 && mp[d/2] > 0 {
			res = max(res, num)
		}
		mp[num]++
	}
	return res
}
