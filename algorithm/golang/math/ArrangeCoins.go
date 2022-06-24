package math

import "math"

/**
二分查找
*/
func arrangeCoins(n int) int {
	l := 1
	r := int(math.Sqrt(float64(n) * 2))
	for l < r {
		//查找右边界，注意这里要+1
		mid := l + (r-l+1)/2
		sum := int64(1+mid) * int64(mid) / 2
		p := int64(n)
		if sum == p {
			return mid
		} else if sum < p {
			l = mid
		} else {
			r = mid - 1
		}
	}
	return l
}
