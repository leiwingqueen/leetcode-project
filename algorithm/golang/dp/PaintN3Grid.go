package dp

func numOfWays(n int) int {
	mod := 1000000007
	dp := make([][3]int, 3*n)
	dp[0][0] = 1
	dp[0][1] = 1
	dp[0][2] = 1
	for i := 1; i < 3*n; i++ {
		for j := 0; j < 3; j++ {
			for k := 0; k < 3; k++ {
				if j != k {
					dp[i][j] += dp[i-1][k]
					dp[i][j] %= mod
				}
			}
		}
	}
	return dp[3*n-1][0] + dp[3*n-1][1] + dp[3*n-1][2]
}
