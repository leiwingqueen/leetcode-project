package wc401

// f(i,j)为第i秒下标为j的值
// f(i,j)=f(i-1,0)+....f(i-1,j)
// 等价于 f(i,j)=f(i,j-1)+f(i-1,j)
func valueAfterKSeconds(n int, k int) int {
	mod := 1_000_000_007
	dp := make([][]int, k+1)
	for i := 0; i <= k; i++ {
		dp[i] = make([]int, n)
	}
	for i := 0; i < n; i++ {
		dp[0][i] = 1
	}
	for i := 1; i <= k; i++ {
		dp[i][0] = 1
		for j := 1; j < n; j++ {
			dp[i][j] = (dp[i][j-1] + dp[i-1][j]) % mod
		}
	}
	return dp[k][n-1]
}
