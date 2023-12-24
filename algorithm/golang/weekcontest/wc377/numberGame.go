package wc377

import "sort"

func numberGame(nums []int) []int {
	n := len(nums)
	sort.Ints(nums)
	res := make([]int, n)
	j := 0
	for i := 0; i < n; i += 2 {
		res[j] = nums[i+1]
		res[j+1] = nums[i]
		j += 2
	}
	return res
}
