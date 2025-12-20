package bwc170

import "sort"

// 贪心
func lexSmallestNegatedPerm(n int, target int64) []int {
	sum := int64(1+n) * int64(n) / 2
	if target > sum || target < -sum {
		return []int{}
	}
	diff := sum - target
	if diff%2 == 1 {
		return []int{}
	}
	res := make([]int, n)
	for x := n; x >= 1; x-- {
		if int64(x)*2 <= diff {
			res[x-1] = -x
			diff -= int64(x) * 2
		} else {
			res[x-1] = x
		}
	}
	sort.Ints(res)
	return res
}
