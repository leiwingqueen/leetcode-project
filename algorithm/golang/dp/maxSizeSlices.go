package dp

func maxSizeSlices(slices []int) int {
	n := len(slices)
	k := n / 3
	cal := func(arr []int, k int) int {
		m := len(arr)
		dp := make([][]int, m+1)
		for i := 0; i <= m; i++ {
			dp[i] = make([]int, k+1)
		}
		dp[1][1] = arr[0]
		for i := 2; i <= m; i++ {
			for j := 1; j <= k; j++ {
				dp[i][j] = dp[i-1][j]
				if dp[i-2][j-1]+arr[i-1] > dp[i][j] {
					dp[i][j] = dp[i-2][j-1] + arr[i-1]
				}
			}
		}
		return dp[m][k]
	}
	p1 := cal(slices[:n-1], k)
	p2 := cal(slices[1:], k)
	if p1 > p2 {
		return p1
	} else {
		return p2
	}
}
