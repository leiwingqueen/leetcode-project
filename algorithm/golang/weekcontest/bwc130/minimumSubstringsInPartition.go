package bwc130

// 给你一个字符串 s ，你需要将它分割成一个或者更多的 平衡 子字符串。比方说，s == "ababcc" 那么 ("abab", "c", "c") ，("ab", "abc", "c") 和 ("ababcc") 都是合法分割，但是 ("a", "bab", "cc") ，("aba", "bc", "c") 和 ("ab", "abcc") 不是，不平衡的子字符串用粗体表示。
//
//请你返回 s 最少 能分割成多少个平衡子字符串。
//
//注意：一个 平衡 字符串指的是字符串中所有字符出现的次数都相同。
//
//
//
//示例 1：
//
//输入：s = "fabccddg"
//
//输出：3
//
//解释：
//
//我们可以将 s 分割成 3 个子字符串：("fab, "ccdd", "g") 或者 ("fabc", "cd", "dg") 。
//
//示例 2：
//
//输入：s = "abababaccddb"
//
//输出：2
//
//解释：
//
//我们可以将 s 分割成 2 个子字符串：("abab", "abaccddb") 。
//
//
//
//提示：
//
//1 <= s.length <= 1000
//s 只包含小写英文字母。

// dp解法
func minimumSubstringsInPartition(s string) int {
	n := len(s)
	prefix := make([][]int, n+1)
	for i := 0; i <= n; i++ {
		prefix[i] = make([]int, 26)
	}
	for i := 0; i < n; i++ {
		for j := 0; j < 26; j++ {
			prefix[i+1][j] = prefix[i][j]
		}
		prefix[i+1][s[i]-'a']++
	}
	check := func(l, r int) bool {
		cnt := 0
		for i := 0; i < 26; i++ {
			c := prefix[r+1][i] - prefix[l][i]
			if c > 0 {
				if cnt == 0 {
					cnt = c
				} else {
					if cnt != c {
						return false
					}
				}
			}
		}
		return true
	}
	dp := make([]int, n+1)
	for i := 1; i <= n; i++ {
		// [j-i,i)
		dp[i] = i
		for j := 1; j <= i; j++ {
			if check(i-j, i-1) && dp[i-j]+1 < dp[i] {
				dp[i] = dp[i-j] + 1
			}
		}
	}
	return dp[n]
}
