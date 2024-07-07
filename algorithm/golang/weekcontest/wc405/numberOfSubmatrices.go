package wc405

func numberOfSubmatrices(grid [][]byte) int {
	m, n := len(grid), len(grid[0])
	prefixSum := make([][][]int, m+1)
	for i := 0; i <= m; i++ {
		prefixSum[i] = make([][]int, n+1)
		for j := 0; j <= n; j++ {
			prefixSum[i][j] = make([]int, 2)
		}
	}
	for i := 1; i <= m; i++ {
		for j := 1; j <= n; j++ {
			for k := 0; k < 2; k++ {
				prefixSum[i][j][k] = prefixSum[i-1][j][k] + prefixSum[i][j-1][k] - prefixSum[i-1][j-1][k]
			}
			if grid[i-1][j-1] == 'X' {
				prefixSum[i][j][0]++
			} else if grid[i-1][j-1] == 'Y' {
				prefixSum[i][j][1]++
			}
		}
	}
	res := 0
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if prefixSum[i+1][j+1][0] > 0 && prefixSum[i+1][j+1][0] == prefixSum[i+1][j+1][1] {
				res++
			}
		}
	}
	return res
}
