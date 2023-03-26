package wc338

import "leetcode-go/util"

func kItemsWithMaximumSum(numOnes int, numZeros int, numNegOnes int, k int) int {
	res := 0
	if k > 0 {
		sub := util.Min(numOnes, k)
		res += sub
		k -= sub
	}
	if k > 0 {
		sub := util.Min(numZeros, k)
		k -= sub
	}
	if k > 0 {
		sub := util.Min(numNegOnes, k)
		res -= sub
		k -= sub
	}
	return res
}
