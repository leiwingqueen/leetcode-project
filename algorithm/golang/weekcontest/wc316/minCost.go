package wc316

import (
	"math"
	"sort"
)

func minCost(nums []int, cost []int) int64 {
	n := len(nums)
	arr := make([][]int, n)
	for i := 0; i < n; i++ {
		arr[i] = []int{nums[i], cost[i]}
	}
	sort.Slice(arr, func(i, j int) bool {
		return arr[i][0] < arr[j][0]
	})
	sum1 := make([]int64, n+1)
	sum2 := make([]int64, n+1)
	for i := 0; i < n; i++ {
		sum1[i+1] = sum1[i] + int64(arr[i][1])
		sum2[i+1] = sum2[i] + int64(arr[i][0])*int64(arr[i][1])
	}
	var search func(k int) int
	search = func(k int) int {
		l := 0
		r := n - 1
		for l < r {
			mid := l + (r-l)/2
			if arr[mid][0] >= k {
				r = mid
			} else {
				l = mid + 1
			}
		}
		return l
	}
	var res int64
	res = math.MaxInt64
	for k := arr[0][0]; k <= arr[n-1][0]; k++ {
		idx := search(k)
		//[0,idx),[idx,n)
		s1 := sum1[idx]*int64(k) - sum2[idx]
		s2 := (sum2[n] - sum2[idx]) - int64(k)*(sum1[n]-sum1[idx])
		sub := s1 + s2
		if sub < res {
			res = sub
		}
	}
	return res
}
