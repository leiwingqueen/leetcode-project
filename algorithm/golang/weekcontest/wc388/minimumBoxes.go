package wc388

import "sort"

func minimumBoxes(apple []int, capacity []int) int {
	sum := 0
	for _, num := range apple {
		sum += num
	}
	sort.Slice(capacity, func(i, j int) bool {
		return capacity[i] > capacity[j]
	})
	cnt := 0
	for _, num := range capacity {
		if sum <= 0 {
			return cnt
		}
		c := sum
		if num < sum {
			c = num
		}
		sum -= c
		cnt++
	}
	return cnt
}
