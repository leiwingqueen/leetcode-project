package array

import "sort"

func deleteGreatestValue(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	for i := 0; i < m; i++ {
		sort.Ints(grid[i])
	}
	res := 0
	for i := 0; i < n; i++ {
		mx := 0
		for j := 0; j < m; j++ {
			if grid[j][i] > mx {
				mx = grid[j][i]
			}
		}
		res += mx
	}
	return res
}
