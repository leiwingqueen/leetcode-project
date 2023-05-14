package wc345

func maxMoves(grid [][]int) int {
	dirs := [][]int{
		{-1, 1},
		{0, 1},
		{1, 1},
	}
	m, n := len(grid), len(grid[0])
	cache := make([][]int, m)
	for i := 0; i < m; i++ {
		cache[i] = make([]int, n)
		for j := 0; j < n; j++ {
			cache[i][j] = -1
		}
	}
	var dfs func(x, y int) int
	dfs = func(x, y int) int {
		if cache[x][y] >= 0 {
			return cache[x][y]
		}
		res := 0
		for _, dir := range dirs {
			nx, ny := x+dir[0], y+dir[1]
			if nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] > grid[x][y] {
				sub := dfs(nx, ny) + 1
				if sub > res {
					res = sub
				}
			}
		}
		cache[x][y] = res
		return res
	}
	return dfs(0, 0)
}
