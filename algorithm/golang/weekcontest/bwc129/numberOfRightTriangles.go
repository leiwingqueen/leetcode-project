package bwc129

// 不需要相邻
func numberOfRightTriangles(grid [][]int) int64 {
	m, n := len(grid), len(grid[0])
	dirs := [][]int{
		{-1, 0, 0, 1},
		{-1, 0, 0, -1},
		{0, -1, 1, 0},
		{0, 1, 1, 0},
	}
	var res int64
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] != 1 {
				continue
			}
			for _, dir := range dirs {
				x1, y1 := i+dir[0], j+dir[1]
				x2, y2 := i+dir[2], j+dir[3]
				if x1 >= 0 && x1 < m && y1 >= 0 && y1 < n &&
					x2 >= 0 && x2 < m && y2 >= 0 && y2 < n &&
					grid[x1][y1] == 1 && grid[x2][y2] == 1 {
					res++
				}
			}
		}
	}
	return res
}

func numberOfRightTriangles2(grid [][]int) int64 {
	m, n := len(grid), len(grid[0])
	rows := make([]int, m)
	cols := make([]int, n)
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == 1 {
				rows[i]++
				cols[j]++
			}
		}
	}
	var res int64
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == 1 {
				res += int64(rows[i]-1) * int64(cols[j]-1)
			}
		}
	}
	return res
}
