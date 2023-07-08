package bwc108

func minimumBeautifulSubstrings(s string) int {
	if s[0] == '0' {
		return -1
	}
	arr := make([]int, 7)
	arr[0] = 1
	for i := 1; i < 7; i++ {
		arr[i] = 5 * arr[i-1]
	}
	n := len(s)
	dp := make([]int, n+1)
	dp[0] = 0
	for i := 1; i <= n; i++ {
		dp[i] = -1
	}
	for i := 1; i <= n; i++ {
		//[j,i]看是否能组成一个美丽字符
		p := 0
		num := 0
		for j := i - 1; j >= 0; j-- {
			if s[j] == '1' {
				num += 1 << p
				flag := false
				for _, l := range arr {
					if l == num {
						flag = true
						break
					}
				}
				if flag && dp[j] >= 0 && (dp[i] < 0 || dp[j]+1 < dp[i]) {
					dp[i] = dp[j] + 1
				}
			}
			p++
		}
	}
	return dp[n]
}
