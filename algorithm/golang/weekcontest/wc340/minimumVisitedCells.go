package wc340

// 超时
func minimumVisitedCells(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	dp := make([][]int, m)
	for i := 0; i < m; i++ {
		dp[i] = make([]int, n)
	}
	for i := m - 1; i >= 0; i-- {
		for j := n - 1; j >= 0; j-- {
			if i == m-1 && j == n-1 {
				dp[i][j] = 1
			} else {
				dp[i][j] = -1
				if grid[i][j] > 0 {
					// 向右移动
					for k := 1; k <= grid[i][j] && j+k < n; k++ {
						if dp[i][j+k] > 0 && (dp[i][j] < 0 || dp[i][j+k]+1 < dp[i][j]) {
							dp[i][j] = dp[i][j+k] + 1
						}
					}
					// 向下移动
					for k := 1; k <= grid[i][j] && i+k < m; k++ {
						if dp[i+k][j] > 0 && (dp[i][j] < 0 || dp[i+k][j]+1 < dp[i][j]) {
							dp[i][j] = dp[i+k][j] + 1
						}
					}
				}
			}
		}
	}
	return dp[0][0]
}
