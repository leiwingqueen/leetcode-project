package bwc100

func distMoney(money int, children int) int {
	for k := 0; k <= children; k++ {
		if k*8 > money {
			return k - 1
		}
		c := money - k*8
		l := children - k
		if l == 0 {
			if c == 0 {
				return k
			} else {
				return k - 1
			}
		}
		if c < l {
			return k - 1
		}
		if l == 1 && c == 4 {
			return k - 1
		}
	}
	return children
}

func distMoney2(money int, children int) int {
	n := children
	k := money
	dp := make([][]int, n)
	for i := 0; i < n; i++ {
		dp[i] = make([]int, k+1)
	}
	dp[0][4] = -1
	dp[0][8] = 1
	for i := 0; i < n; i++ {
		dp[i][0] = -1
	}
	for i := 1; i < n; i++ {
		for j := 1; j <= k; j++ {
			dp[i][j] = -1
			for l := 1; l < j; l++ {
				if dp[i-1][j-l] >= 0 && l != 4 {
					c := 0
					if l == 8 {
						c = 1
					}
					if dp[i][j] < 0 || dp[i-1][j-l]+c > dp[i][j] {
						dp[i][j] = -1
					}
				}
			}
		}
	}
	return dp[n-1][k]
}
