package wc524

func lastVisitedCell(grid [][]byte) []int {
	m, n := len(grid), len(grid[0])
	visit := make([][]bool, m)
	for i := 0; i < m; i++ {
		visit[i] = make([]bool, n)
	}
	visit[0][0] = true
	x, y := 0, 0
	for {
		action := grid[x][y]
		nx, ny := x, y
		if action == 'U' {
			nx--
		} else if action == 'D' {
			nx++
		} else if action == 'L' {
			ny--
		} else {
			ny++
		}
		if nx >= 0 && nx < m && ny >= 0 && ny < n && !visit[nx][ny] {
			visit[nx][ny] = true
			x, y = nx, ny
		} else {
			break
		}
	}
	return []int{x, y}
}
