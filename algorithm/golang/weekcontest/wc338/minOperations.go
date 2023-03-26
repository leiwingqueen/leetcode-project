package wc338

import (
	"leetcode-go/util"
	"sort"
)

// 超时
func minOperations(nums []int, queries []int) []int64 {
	m := len(queries)
	res := make([]int64, m)
	for i, query := range queries {
		var r int64
		for _, num := range nums {
			r += int64(util.Abs(query - num))
		}
		res[i] = r
	}
	return res
}

func minOperations2(nums []int, queries []int) []int64 {
	sort.Ints(nums)
	n := len(nums)
	prefixSum := make([]int64, n+1)
	for i := 0; i < n; i++ {
		prefixSum[i+1] = prefixSum[i] + int64(nums[i])
	}
	m := len(queries)
	res := make([]int64, m)
	for i, query := range queries {
		idx := sort.SearchInts(nums, query)
		//[0,idx)小于query,[idx,n)>=query
		var r int64
		r += int64(idx)*int64(query) - prefixSum[idx]
		r += prefixSum[n] - prefixSum[idx] - int64(n-idx)*int64(query)
		res[i] = r
	}
	return res
}
