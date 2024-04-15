package wc393

import "math"

// 这个写法有个问题，需要去重
func findKthSmallest(coins []int, k int) int64 {
	// 小于或等于num的数量有多少
	getCount := func(num int64) int64 {
		var cnt int64
		for _, coin := range coins {
			cnt += num / int64(coin)
		}
		return cnt
	}
	var l, r int64
	l = 0
	r = math.MaxInt64
	for l < r {
		mid := l + (r-l)/2
		if getCount(mid) < int64(k) {
			l = mid + 1
		} else {
			r = mid
		}
	}
	return l
}
