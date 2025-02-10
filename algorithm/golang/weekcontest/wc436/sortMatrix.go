package wc436

import "sort"

func sortMatrix(grid [][]int) [][]int {
	n := len(grid)
	for i := 0; i < n; i++ {
		x, y := i, 0
		// 倒序排序
		var arr []int
		for x < n && y < n {
			arr = append(arr, grid[x][y])
			x++
			y++
		}
		sort.Slice(arr, func(i, j int) bool {
			return arr[i] > arr[j]
		})
		x, y = i, 0
		for _, num := range arr {
			grid[x][y] = num
			x++
			y++
		}
	}
	// 上半区
	for i := 1; i < n; i++ {
		x, y := 0, i
		var arr []int
		for x < n && y < n {
			arr = append(arr, grid[x][y])
			x++
			y++
		}
		sort.Ints(arr)
		x, y = 0, i
		for _, num := range arr {
			grid[x][y] = num
			x++
			y++
		}
	}
	return grid
}
