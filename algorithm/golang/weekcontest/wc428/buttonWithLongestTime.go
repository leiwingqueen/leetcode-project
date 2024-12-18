package wc428

import "math"

func buttonWithLongestTime(events [][]int) int {
	n := len(events)
	res := 0
	idx := math.MaxInt
	last := 0
	for i := 0; i < n; i++ {
		t := events[i][1] - last
		if t == res {
			idx = min(events[i][0], idx)
		} else if t > res {
			res = t
			idx = events[i][0]
		}
		last = events[i][1]
	}
	return idx
}
