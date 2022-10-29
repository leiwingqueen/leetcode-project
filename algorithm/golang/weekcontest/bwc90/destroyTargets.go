package bwc90

import "sort"

func destroyTargets(nums []int, space int) int {
	mp := make(map[int]int)
	for _, num := range nums {
		mp[num%space]++
	}
	list := make([]int, 0)
	mx := 0
	for k, v := range mp {
		if v > mx {
			mx = v
			list = []int{k}
		} else if v == mx {
			list = append(list, k)
		}
	}
	sort.Ints(nums)
	for _, num := range nums {
		for _, k := range list {
			if num%space == k {
				return num
			}
		}
	}
	return -1
}
