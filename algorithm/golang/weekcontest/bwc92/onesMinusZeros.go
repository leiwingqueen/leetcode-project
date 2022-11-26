package bwc92

func onesMinusZeros(grid [][]int) [][]int {
	m := len(grid)
	n := len(grid[0])
	onesRows := make([]int, m)
	onesCols := make([]int, n)
	for i := 0; i < m; i++ {
		cnt := 0
		for j := 0; j < n; j++ {
			if grid[i][j] == 1 {
				cnt++
			}
		}
		onesRows[i] = cnt
	}
	for i := 0; i < n; i++ {
		cnt := 0
		for j := 0; j < m; j++ {
			if grid[j][i] == 1 {
				cnt++
			}
		}
		onesCols[i] = cnt
	}
	res := make([][]int, m)
	for i := 0; i < m; i++ {
		res[i] = make([]int, n)
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			res[i][j] = onesRows[i] + onesCols[j] - (n - onesRows[i]) - (m - onesCols[j])
		}
	}
	return res
}
