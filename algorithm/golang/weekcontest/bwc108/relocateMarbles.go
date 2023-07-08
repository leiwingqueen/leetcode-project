package bwc108

import "sort"

func relocateMarbles(nums []int, moveFrom []int, moveTo []int) []int {
	mp := make(map[int]struct{})
	for _, num := range nums {
		mp[num] = struct{}{}
	}
	for i, from := range moveFrom {
		to := moveTo[i]
		if _, exist := mp[from]; exist {
			delete(mp, from)
			mp[to] = struct{}{}
		}
	}
	var res []int
	for k := range mp {
		res = append(res, k)
	}
	sort.Ints(res)
	return res
}
