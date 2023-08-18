package dp

func maxSizeSlices(slices []int) int {
	n := len(slices)
	k := n / 3
	cal := func(arr []int, k int) int {
		dp := make([][]int, len(arr))
		for i := 0; i < len(arr); i++ {
			dp[i] = make([]int, k+1)
		}
		for i := 1; i < len(arr); i++ {
			for j := 1; j < k; j++ {
				dp[i][j] = dp[i-1][j]
				if i > 1 && dp[i-2][j-1]+arr[i] > dp[i][j] {
					dp[i][j] = dp[i-2][j-1] + arr[i]
				}
			}
		}
		return dp[len(arr)-1][k]
	}
	p1 := cal(slices[:n-1], k)
	p2 := cal(slices[1:], k)
	if p1 > p2 {
		return p1
	} else {
		return p2
	}
}
