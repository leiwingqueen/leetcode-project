package bwc128

import "sort"

func minRectanglesToCoverPoints(points [][]int, w int) int {
	n := len(points)
	arr := make([]int, n)
	for i, point := range points {
		arr[i] = point[0]
	}
	sort.Ints(arr)
	l := -w - 1
	res := 0
	for _, num := range arr {
		if num > l+w {
			res++
			l = num
		}
	}
	return res
}
