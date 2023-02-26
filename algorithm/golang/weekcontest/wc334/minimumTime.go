package wc334

func minimumTime(grid [][]int) int {
	dirs := [][]int{
		{-1, 0},
		{1, 0},
		{0, -1},
		{0, 1},
	}
	m := len(grid)
	n := len(grid[0])
	var dfs func(x int, y int, t int) int
	dfs = func(x int, y int, t int) int {
		if x == m-1 && y == n-1 {
			return t
		}
		res := -1
		for _, dir := range dirs {
			nx := x + dir[0]
			ny := y + dir[1]
			if nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] <= t+1 {
				sub := dfs(nx, ny, t+1)
				if res < 0 || sub < res {
					res = sub
				}
			}
		}
		return res
	}
	return dfs(0, 0, 0)
}

func minimumTime2(grid [][]int) int {
	dirs := [][]int{
		{-1, 0},
		{1, 0},
		{0, -1},
		{0, 1},
	}
	m := len(grid)
	n := len(grid[0])
	mem := make([][]int, m)
	for i := 0; i < m; i++ {
		mem[i] = make([]int, n)
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			mem[i][j] = -1
		}
	}
	mem[0][0] = 0
	var dfs func(x int, y int, t int) int
	dfs = func(x int, y int, t int) int {
		if x == m-1 && y == n-1 {
			return t
		}
		mem[x][y] = t
		res := -1
		for _, dir := range dirs {
			nx := x + dir[0]
			ny := y + dir[1]
			if nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] <= t+1 {
				if mem[nx][ny] < 0 || mem[nx][ny] > t+1 {
					sub := dfs(nx, ny, t+1)
					if res < 0 || sub < res {
						res = sub
					}
				}
			}
		}
		return res
	}
	return dfs(0, 0, 0)
}

func minimumTime3(grid [][]int) int {
	dirs := [][]int{
		{-1, 0},
		{1, 0},
		{0, -1},
		{0, 1},
	}
	m := len(grid)
	n := len(grid[0])
	mem := make([][]int, m)
	for i := 0; i < m; i++ {
		mem[i] = make([]int, n)
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			mem[i][j] = -1
		}
	}
	mem[0][0] = 0
	var dfs func(x int, y int, t int) int
	dfs = func(x int, y int, t int) int {
		if x == m-1 && y == n-1 {
			mem[x][y] = t
			return t
		}
		mem[x][y] = t
		res := -1
		for _, dir := range dirs {
			nx := x + dir[0]
			ny := y + dir[1]
			if nx >= 0 && nx < m && ny >= 0 && ny < n {
				if grid[nx][ny] <= t+1 {
					if mem[nx][ny] < 0 || mem[nx][ny] > t+1 {
						sub := dfs(nx, ny, t+1)
						if res < 0 || sub < res {
							res = sub
						}
					}
				} else {
					if t > 0 {
						// 存在回头路
						diff := grid[nx][ny] - t - 1
						inc := (diff + 1) / 2
						if mem[nx][ny] < 0 || mem[nx][ny] > t+inc*2+1 {
							sub := dfs(nx, ny, t+inc*2+1)
							if res < 0 || sub < res {
								res = sub
							}
						}
					}
				}
			}
		}
		return res
	}
	dfs(0, 0, 0)
	return mem[m-1][n-1]
}

func minimumTime4(grid [][]int) int {
	dirs := [][]int{
		{-1, 0},
		{1, 0},
		{0, -1},
		{0, 1},
	}
	m := len(grid)
	n := len(grid[0])
	mem := make([][]int, m)
	for i := 0; i < m; i++ {
		mem[i] = make([]int, n)
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			mem[i][j] = -1
		}
	}
	mem[0][0] = 0
	var dfs func(x int, y int, t int) int
	dfs = func(x int, y int, t int) int {
		if x == m-1 && y == n-1 {
			mem[x][y] = t
			return t
		}
		mem[x][y] = t
		res := -1
		for _, dir := range dirs {
			nx := x + dir[0]
			ny := y + dir[1]
			if nx >= 0 && nx < m && ny >= 0 && ny < n {
				if grid[nx][ny] <= t+1 {
					if mem[nx][ny] < 0 || mem[nx][ny] > t+1 {
						mem[nx][ny] = t + 1
						sub := dfs(nx, ny, t+1)
						if res < 0 || sub < res {
							res = sub
						}
					}
				} else {
					if t > 0 {
						// 存在回头路
						diff := grid[nx][ny] - t - 1
						inc := (diff + 1) / 2
						if mem[nx][ny] < 0 || mem[nx][ny] > t+inc*2+1 {
							mem[nx][ny] = t + inc*2 + 1
							sub := dfs(nx, ny, t+inc*2+1)
							if res < 0 || sub < res {
								res = sub
							}
						}
					}
				}
			}
		}
		return res
	}
	dfs(0, 0, 0)
	return mem[m-1][n-1]
}
