package bwc99

import "sort"

func countWays(ranges [][]int) int {
	sort.Slice(ranges, func(i, j int) bool {
		return ranges[i][0] < ranges[j][0]
	})
	check := func(r1 []int, r2 []int) bool {
		return r1[1] >= r2[0] && r1[0] <= r2[1]
	}
	n := len(ranges)
	l := 0
	r := 0
	cnt := 0
	for r < n {
		if check(ranges[r], ranges[l]) {
			r++
		} else {
			//[l,r)有交集

		}
	}
}
