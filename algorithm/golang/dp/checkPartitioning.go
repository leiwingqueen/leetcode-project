package dp

// 给你一个字符串 s ，如果可以将它分割成三个 非空 回文子字符串，那么返回 true ，否则返回 false 。
//
//当一个字符串正着读和反着读是一模一样的，就称其为 回文字符串 。
//
//
//
//示例 1：
//
//输入：s = "abcbdd"
//输出：true
//解释："abcbdd" = "a" + "bcb" + "dd"，三个子字符串都是回文的。
//示例 2：
//
//输入：s = "bcbddxy"
//输出：false
//解释：s 没办法被分割成 3 个回文子字符串。
//
//
//提示：
//
//3 <= s.length <= 2000
//s​​​​​​ 只包含小写英文字母。

// 思路没错，但是超时了
func checkPartitioning(s string) bool {
	n := len(s)
	// 先预计算[i,j]之间的子串是否回文串
	dp := make([][]bool, n)
	for i := n - 1; i >= 0; i-- {
		dp[i] = make([]bool, n)
		for j := i; j < n; j++ {
			if i == j {
				dp[i][j] = true
			} else {
				if s[i] != s[j] {
					dp[i][j] = false
				} else {
					dp[i][j] = i+1 >= j-1 || dp[i+1][j-1]
				}
			}
		}
	}
	dp1 := make([][]bool, n)
	for i := n - 2; i >= 0; i-- {
		dp1[i] = make([]bool, n)
		for j := i + 1; j < n; j++ {
			if j == i+1 {
				dp1[i][j] = true
			} else {
				for l := j; l >= i+1; l-- {
					// [l,j]作为一个子串
					if dp[l][j] && dp[i][l-1] {
						dp1[i][j] = true
					}
				}
			}
		}
	}
	dp2 := make([][]bool, n)
	for i := n - 3; i >= 0; i-- {
		dp2[i] = make([]bool, n)
		for j := i + 2; j < n; j++ {
			if j == i+2 {
				dp2[i][j] = true
			} else {
				for l := j; l >= i+2; l-- {
					// [l,j]作为一个子串
					if dp[l][j] && dp1[i][l-1] {
						dp2[i][j] = true
					}
				}
			}
		}
	}
	return dp2[0][n-1]
}

func checkPartitioning2(s string) bool {
	n := len(s)
	// 先预计算[i,j]之间的子串是否回文串
	dp := make([][]bool, n)
	for i := n - 1; i >= 0; i-- {
		dp[i] = make([]bool, n)
		for j := i; j < n; j++ {
			if i == j {
				dp[i][j] = true
			} else {
				if s[i] != s[j] {
					dp[i][j] = false
				} else {
					dp[i][j] = i+1 >= j-1 || dp[i+1][j-1]
				}
			}
		}
	}
	//[0,i],[i+1,j],[j+1,n-1]
	for i := 0; i < n-2; i++ {
		if !dp[0][i] {
			continue
		}
		for j := i + 1; j < n-1; j++ {
			if dp[i+1][j] && dp[j+1][n-1] {
				return true
			}
		}
	}
	return false
}
