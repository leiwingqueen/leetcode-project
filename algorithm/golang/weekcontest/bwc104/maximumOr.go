package bwc104

import "sort"

func maximumOr(nums []int, k int) int64 {
	sort.Ints(nums)
	res := int64(nums[len(nums)-1]) << k
	for i := 0; i < len(nums)-1; i++ {
		res |= int64(nums[i])
	}
	return res
}

func maximumOr2(nums []int, k int) int64 {
	n := len(nums)
	pre := make([]int, n)
	post := make([]int, n)
	for i := 1; i < n; i++ {
		pre[i] = pre[i-1] | nums[i-1]
	}
	for i := n - 2; i >= 0; i-- {
		post[i] = post[i+1] | nums[i+1]
	}
	var res int64
	for i := 0; i < n; i++ {
		s := int64(pre[i]) | int64(post[i]) | int64(nums[i])<<k
		if s > res {
			res = s
		}
	}
	return res
}
