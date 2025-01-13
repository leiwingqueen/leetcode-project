package wc432

import "math"

// 典型的DP
// f(i,j,k)=max{f(i,j-1,k)+coin[i][j],f(i-1,j,k)+coin[i][j],f(i,j-1,k+1),f(i-1,j,k+1)}
func maximumAmount(coins [][]int) int {
	m, n := len(coins), len(coins[0])
	dp := make([][][]int, 3)
	for i := 0; i < 3; i++ {
		dp[i] = make([][]int, m)
		for j := 0; j < m; j++ {
			dp[i][j] = make([]int, n)
		}
	}
	for k := 0; k < 3; k++ {
		dp[k][0][0] = coins[0][0]
		if k > 0 {
			dp[k][0][0] = 0
		}
		for i := 1; i < n; i++ {
			dp[k][0][i] = dp[k][0][i-1] + coins[0][i]
			if k > 0 {
				dp[k][0][i] = max(dp[k][0][i], dp[k-1][0][i-1])
			}
		}
		for i := 1; i < m; i++ {
			dp[k][i][0] = dp[k][i-1][0] + coins[i][0]
			if k > 0 {
				dp[k][i][0] = max(dp[k][i][0], dp[k-1][i-1][0])
			}
		}
		for i := 1; i < m; i++ {
			for j := 1; j < n; j++ {
				dp[k][i][j] = max(dp[k][i][j-1]+coins[i][j], dp[k][i-1][j]+coins[i][j])
				if k > 0 {
					dp[k][i][j] = max(dp[k][i][j], dp[k-1][i-1][j], dp[k-1][i][j-1])
				}
			}
		}
	}
	res := math.MinInt
	for i := 0; i < 3; i++ {
		res = max(res, dp[i][m-1][n-1])
	}
	return res
}
