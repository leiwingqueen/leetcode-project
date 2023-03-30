package bwc100

import "sort"

func findScore(nums []int) int64 {
	n := len(nums)
	arr := make([][]int, n)
	mark := make([]bool, n)
	for i, num := range nums {
		// index,num
		arr[i] = []int{i, num}
	}
	sort.Slice(arr, func(i, j int) bool {
		if arr[i][1] != arr[j][1] {
			return arr[i][1] < arr[j][1]
		} else {
			return arr[i][0] < arr[j][0]
		}
	})
	var res int64
	for i := 0; i < n; i++ {
		idx, num := arr[i][0], arr[i][1]
		if mark[idx] {
			continue
		}
		res += int64(num)
		mark[idx] = true
		if idx > 0 {
			mark[idx-1] = true
		}
		if idx < n-1 {
			mark[idx+1] = true
		}
	}
	return res
}
