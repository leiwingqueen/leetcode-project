package wc333

import "sort"

func mergeArrays(nums1 [][]int, nums2 [][]int) [][]int {
	mp := make(map[int]int)
	for _, num := range nums1 {
		mp[num[0]] += num[1]
	}
	for _, num := range nums2 {
		mp[num[0]] += num[1]
	}
	res := make([][]int, len(mp))
	idx := 0
	for k, v := range mp {
		res[idx] = []int{k, v}
		idx++
	}
	sort.Slice(res, func(i, j int) bool {
		return res[i][0] < res[j][0]
	})
	return res
}
