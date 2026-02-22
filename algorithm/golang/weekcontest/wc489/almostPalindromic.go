package wc489

// dp0[i][j]=dp0[i+1][j-1]&&s[i]==s[j]
// dp1[i][j]=s[i]==s[j]&&dp1[i+1][j-1] || dp0[i+1][j] || dp0[i][j-1]
func almostPalindromic(s string) int {
	n := len(s)
	dp0, dp1 := make([]bool, n), make([]bool, n)
	tmp0, tmp1 := make([]bool, n), make([]bool, n)
	dp0[n-1] = true
	dp1[n-1] = true
	res := 1
	for i := n - 1; i >= 0; i-- {
		for j := i; j < n; j++ {
			if i == j {
				tmp0[i] = true
				tmp1[i] = true
			} else {
				tmp0[j] = s[i] == s[j] && (i+1 >= j-1 || dp0[j-1])
				tmp1[j] = (s[i] == s[j] && dp1[j-1]) || dp0[j] || tmp0[j-1]
				if tmp1[j] {
					res = max(res, j-i+1)
				}
			}
		}
		copy(dp0, tmp0)
		copy(dp1, tmp1)
	}
	return res
}
