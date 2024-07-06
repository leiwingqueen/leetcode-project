package dfs

// 给定一个布尔表达式和一个期望的布尔结果 result，布尔表达式由 0 (false)、1 (true)、& (AND)、 | (OR) 和 ^ (XOR) 符号组成。实现一个函数，算出有几种可使该表达式得出 result 值的括号方法。
//
//示例 1:
//
//输入: s = "1^0|0|1", result = 0
//
//输出: 2
//解释: 两种可能的括号方法是
//1^(0|(0|1))
//1^((0|0)|1)
//示例 2:
//
//输入: s = "0&0&0&1^1|0", result = 1
//
//输出: 10
//提示：
//
//运算符的数量不超过 19 个

func countEval(s string, result int) int {
	n := len(s)
	var dfs func(start, end int, expect int) int
	dfs = func(start, end int, expect int) int {
		if start == end {
			if s[start] == byte('0'+result) {
				return 1
			} else {
				return 0
			}
		}
		res := 0
		for i := start + 1; i < end; i += 2 {
			if expect == 1 {
				if s[i] == '&' {
					res += dfs(start, i-1, 1) * dfs(i+1, end, 1)
				} else if s[i] == '|' {
					res += dfs(start, i-1, 1) * dfs(i+1, end, 1)
					res += dfs(start, i-1, 1) * dfs(i+1, end, 0)
					res += dfs(start, i-1, 0) * dfs(i+1, end, 1)
				} else {
					res += dfs(start, i-1, 1) * dfs(i+1, end, 0)
					res += dfs(start, i-1, 0) * dfs(i+1, end, 1)
				}
			} else {
				if s[i] == '&' {
					res += dfs(start, i-1, 0) * dfs(i+1, end, 0)
					res += dfs(start, i-1, 0) * dfs(i+1, end, 1)
					res += dfs(start, i-1, 1) * dfs(i+1, end, 0)
				} else if s[i] == '|' {
					res += dfs(start, i-1, 0) * dfs(i+1, end, 0)
				} else {
					res += dfs(start, i-1, 0) * dfs(i+1, end, 0)
					res += dfs(start, i-1, 1) * dfs(i+1, end, 1)
				}
			}
		}
		return res
	}
	return dfs(0, n-1, result)
}

// 时间复杂度O(n^3)
func countEval2(s string, result int) int {
	n := len(s)
	dp := make([][][]int, n)
	for i := 0; i < n; i++ {
		dp[i] = make([][]int, n)
		for j := 0; j < n; j++ {
			dp[i][j] = make([]int, 2)
		}
	}
	for i := 0; i < n; i += 2 {
		dp[i][i][s[i]-'0'] = 1
	}
	for i := n - 3; i >= 0; i -= 2 {
		for j := i + 2; j < n; j += 2 {
			for k := 0; k <= 1; k++ {
				// 以l为分割点
				for l := i + 1; l < j; l += 2 {
					if k == 0 {
						if s[l] == '&' {
							dp[i][j][k] += dp[i][l-1][0] * dp[l+1][j][0]
							dp[i][j][k] += dp[i][l-1][0] * dp[l+1][j][1]
							dp[i][j][k] += dp[i][l-1][1] * dp[l+1][j][0]
						} else if s[l] == '|' {
							dp[i][j][k] += dp[i][l-1][0] * dp[l+1][j][0]
						} else {
							dp[i][j][k] += dp[i][l-1][0] * dp[l+1][j][0]
							dp[i][j][k] += dp[i][l-1][1] * dp[l+1][j][1]
						}
					} else {
						if s[l] == '&' {
							dp[i][j][k] += dp[i][l-1][1] * dp[l+1][j][1]
						} else if s[l] == '|' {
							dp[i][j][k] += dp[i][l-1][1] * dp[l+1][j][0]
							dp[i][j][k] += dp[i][l-1][0] * dp[l+1][j][1]
							dp[i][j][k] += dp[i][l-1][1] * dp[l+1][j][1]
						} else {
							dp[i][j][k] += dp[i][l-1][0] * dp[l+1][j][1]
							dp[i][j][k] += dp[i][l-1][1] * dp[l+1][j][0]
						}
					}
				}
			}
		}
	}
	return dp[0][n-1][result]
}
