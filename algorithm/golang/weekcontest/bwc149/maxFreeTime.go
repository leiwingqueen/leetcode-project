package bwc149

import "sort"

// 暂时没有思路
func maxFreeTime(eventTime int, k int, startTime []int, endTime []int) int {
	n := len(startTime)
	interval := make([]int, n)
	for i := 0; i < n; i++ {
		interval[i] = endTime[i] - startTime[i]
	}
	sort.Ints(interval)
	sum := 0
	for _, t := range interval[:k] {
		sum += t
	}
	return eventTime - sum
}
