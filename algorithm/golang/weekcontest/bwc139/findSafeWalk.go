package bwc139

func findSafeWalk(grid [][]int, health int) bool {
	m, n := len(grid), len(grid[0])
	healthGrid := make([][]int, m)
	for i := 0; i < m; i++ {
		healthGrid[i] = make([]int, n)
	}
	healthGrid[0][0] = health
	if grid[0][0] == 1 {
		healthGrid[0][0]--
	}
	if healthGrid[0][0] <= 0 {
		return false
	}
	queue := [][]int{
		{0, 0},
	}
	dirs := [][]int{
		{-1, 0},
		{1, 0},
		{0, -1},
		{0, 1},
	}
	for len(queue) > 0 {
		k := len(queue)
		for i := 0; i < k; i++ {
			x, y := queue[k][0], queue[k][1]
			for _, dir := range dirs {
				nx, ny := x+dir[0], y+dir[1]
				if nx >= 0 && nx < m && ny >= 0 && ny < n &&
					healthGrid[x][y]-grid[nx][ny] > 0 && healthGrid[x][y]-grid[nx][ny] > healthGrid[nx][ny] {
					healthGrid[nx][ny] = healthGrid[x][y] - grid[nx][ny]
					queue = append(queue, []int{nx, ny})
				}
			}
		}
		queue = queue[k:]
	}
	return healthGrid[m-1][n-1] > 0
}
