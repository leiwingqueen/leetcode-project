package wc425

import "math"

func minimumSumSubarray(nums []int, l int, r int) int {
	n := len(nums)
	pre := make([]int, n+1)
	for i, num := range nums {
		pre[i+1] = pre[i] + num
	}
	res := math.MaxInt
	found := false
	for i := 0; i < n; i++ {
		for k := l; k <= r; k++ {
			if i+k > n {
				break
			}
			// [i,i+k)
			sum := pre[i+k] - pre[i]
			if sum > 0 {
				found = true
				res = min(res, sum)
			}
		}
	}
	if found {
		return res
	} else {
		return -1
	}
}
