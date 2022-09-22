package bwc84

import "sort"

func mergeSimilarItems(items1 [][]int, items2 [][]int) [][]int {
	mp := make(map[int]int)
	for _, item := range items1 {
		mp[item[0]] += item[1]
	}
	for _, item := range items2 {
		mp[item[0]] += item[1]
	}
	var res [][]int
	for k, v := range mp {
		res = append(res, []int{k, v})
	}
	sort.Slice(res, func(i, j int) bool {
		return res[i][0] < res[j][0]
	})
	return res
}
