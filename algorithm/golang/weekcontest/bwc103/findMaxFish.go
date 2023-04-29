package bwc103

func findMaxFish(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	dirs := [][]int{
		{-1, 0},
		{1, 0},
		{0, -1},
		{0, 1},
	}
	var dfs func(x, y int) int
	dfs = func(x, y int) int {
		c := grid[x][y]
		grid[x][y] = 0
		for _, dir := range dirs {
			nx, ny := x+dir[0], y+dir[1]
			if nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] > 0 {
				c += dfs(nx, ny)
			}
		}
		return c
	}
	res := 0
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] > 0 {
				sub := dfs(i, j)
				if sub > res {
					res = sub
				}
			}
		}
	}
	return res
}
