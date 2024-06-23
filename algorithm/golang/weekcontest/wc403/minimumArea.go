package wc403

func minimumArea(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	l, r := n-1, 0
	u, d := m-1, 0
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == 1 {
				l = min(l, j)
				r = max(r, j)
				u = min(u, i)
				d = max(d, i)
			}
		}
	}
	return (r - l + 1) * (d - u + 1)
}
