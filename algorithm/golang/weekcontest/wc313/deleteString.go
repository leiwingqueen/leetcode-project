package wc313

func deleteString(s string) int {
	n := len(s)
	dp := make([]int, n)
	dp[n-1] = 1
	for i := n - 2; i >= 0; i-- {
		mid := i + (n-1-i)/2
		if n-i%2 == 1 {
			mid--
		}
		dp[i] = 1
		//分成两部分[i,j],[j+1,n-1]
		for j := i; j <= mid; j++ {
			check := func(i int, j int) bool {
				for k := 0; k <= j-i; k++ {
					if s[i+k] != s[j+1+k] {
						return false
					}
				}
				return true
			}
			if check(i, j) && dp[j+1]+1 > dp[i] {
				dp[i] = dp[j+1] + 1
			}
		}
	}
	return dp[0]
}
