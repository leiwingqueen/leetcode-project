package wc400

import "sort"

func countDays(days int, meetings [][]int) int {
	res := 0
	pre := 0
	sort.Slice(meetings, func(i, j int) bool {
		return meetings[i][0] < meetings[j][0]
	})
	for _, query := range meetings {
		start, end := query[0], query[1]
		res += max(start-pre-1, 0)
		pre = max(pre, end)
	}
	res += days - pre
	return res
}
