package array

import "sort"

func heightChecker(heights []int) int {
	expect := make([]int, len(heights))
	for i, v := range heights {
		expect[i] = v
	}
	sort.Ints(expect)
	cnt := 0
	for i := 0; i < len(heights); i++ {
		if heights[i] != expect[i] {
			cnt++
		}
	}
	return cnt
}
