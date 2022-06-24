package dp

func maxProfit(k int, prices []int) int {
	if k == 0 || len(prices) == 0 {
		return 0
	}
	len := len(prices)
	dp0, dp1 := make([][]int, len), make([][]int, len)
	for i := 0; i < len; i++ {
		dp0[i] = make([]int, k+1)
		dp1[i] = make([]int, k+1)
	}
	for i := 0; i <= k; i++ {
		dp0[0][i] = 0
		dp1[0][i] = -prices[0]
	}
	for i := 1; i < len; i++ {
		for j := 1; j <= k; j++ {
			dp0[i][j] = max(dp1[i-1][j]+prices[i], dp0[i-1][j])
			dp1[i][j] = max(dp1[i-1][j], dp0[i-1][j-1]-prices[i])
		}
	}
	return dp0[len-1][k]
}

func max(a, b int) int {
	if a > b {
		return a
	} else {
		return b
	}
}
