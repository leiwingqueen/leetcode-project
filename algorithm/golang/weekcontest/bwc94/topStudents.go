package bwc94

import (
	"sort"
	"strings"
)

func topStudents(positive_feedback []string, negative_feedback []string, report []string, student_id []int, k int) []int {
	positive := make(map[string]bool)
	negative := make(map[string]bool)
	for _, word := range positive_feedback {
		positive[word] = true
	}
	for _, word := range negative_feedback {
		negative[word] = true
	}
	cal := func(s string) int {
		res := 0
		for _, word := range strings.Split(s, " ") {
			if positive[word] {
				res += 3
			} else if negative[word] {
				res -= 1
			}
		}
		return res
	}
	n := len(report)
	point := make([][]int, n)
	for i, s := range report {
		point[i] = []int{student_id[i], cal(s)}
	}
	sort.Slice(point, func(i, j int) bool {
		if point[i][1] != point[j][1] {
			return point[i][1] > point[j][1]
		}
		return point[i][0] < point[j][0]
	})
	res := make([]int, k)
	for i := 0; i < k; i++ {
		res[i] = point[i][0]
	}
	return res
}
