package bwc104

import "sort"

func matrixSum(nums [][]int) int {
	res := 0
	m, n := len(nums), len(nums[0])
	for i := 0; i < m; i++ {
		sort.Slice(nums[i], func(j, k int) bool {
			return nums[i][j] > nums[i][k]
		})
	}
	for i := 0; i < n; i++ {
		mx := 0
		for j := 0; j < m; j++ {
			if nums[j][i] > mx {
				mx = nums[j][i]
			}
		}
		res += mx
	}
	return res
}
