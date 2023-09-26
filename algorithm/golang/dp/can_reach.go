package dp

// f(n)=f(n-1)|| {f(n-maxJump-1)...f(n-minJump-1)}
func canReach(arr []int, minJump int, maxJump int) bool {
	max := func(a, b int) int {
		if a > b {
			return a
		} else {
			return b
		}
	}
	min := func(a, b int) int {
		if a < b {
			return a
		} else {
			return b
		}
	}
	if arr[0] == 1 {
		return false
	}
	n := len(arr)
	dp := make([]bool, n)
	dp[0] = true
	for i := 1; i < n; i++ {
		if arr[i] == 1 {
			dp[i] = false
		} else {
			if dp[i-1] {
				dp[i] = true
			} else {
				for j := max(0, i-maxJump-1); j <= min(n-1, i-minJump-1); j++ {
					if dp[j] {
						dp[i] = true
						break
					}
				}
			}
		}
	}
	return dp[n-1]
}
