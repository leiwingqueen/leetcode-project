package wc312

import "sort"

type people struct {
	name   string
	height int
}

func sortPeople(names []string, heights []int) []string {
	arr := make([]people, len(names))
	for i, name := range names {
		arr[i] = people{name, heights[i]}
	}
	sort.Slice(arr, func(i, j int) bool {
		return arr[i].height >= arr[j].height
	})
	res := make([]string, len(names))
	for i, k := range arr {
		res[i] = k.name
	}
	return res
}
