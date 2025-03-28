package wc439

// 给你一个字符串 s 和一个整数 k。
//
//在一次操作中，你可以将任意位置的字符替换为字母表中相邻的字符（字母表是循环的，因此 'z' 的下一个字母是 'a'）。例如，将 'a' 替换为下一个字母结果是 'b'，将 'a' 替换为上一个字母结果是 'z'；同样，将 'z' 替换为下一个字母结果是 'a'，替换为上一个字母结果是 'y'。
//
//返回在进行 最多 k 次操作后，s 的 最长回文子序列 的长度。
//
//子序列 是一个 非空 字符串，可以通过删除原字符串中的某些字符（或不删除任何字符）并保持剩余字符的相对顺序得到。
//
//回文 是正着读和反着读都相同的字符串。
//
//
//
//示例 1：
//
//输入: s = "abced", k = 2
//
//输出: 3
//
//解释:
//
//将 s[1] 替换为下一个字母，得到 "acced"。
//将 s[4] 替换为上一个字母，得到 "accec"。
//子序列 "ccc" 形成一个长度为 3 的回文，这是最长的回文子序列。
//
//示例 2：
//
//输入: s = "aaazzz", k = 4
//
//输出: 6
//
//解释:
//
//将 s[0] 替换为上一个字母，得到 "zaazzz"。
//将 s[4] 替换为下一个字母，得到 "zaazaz"。
//将 s[3] 替换为下一个字母，得到 "zaaaaz"。
//整个字符串形成一个长度为 6 的回文。
//
//
//
//提示:
//
//1 <= s.length <= 200
//1 <= k <= 200
//s 仅由小写英文字母组成。

// 还是挺难的
func longestPalindromicSubsequence(s string, k int) int {
	n := len(s)
	dis := func(a, b byte) int {
		if a > b {
			a, b = b, a
		}
		return min(int(b-a), int(a+26-b))
	}
	dp := make([][][]int, k+1)
	for i := 0; i <= k; i++ {
		dp[i] = make([][]int, n)
		for j := 0; j < n; j++ {
			dp[i][j] = make([]int, n+1)
		}
	}
	res := 0
	for p := 0; p <= k; p++ {
		for i := n - 1; i >= 0; i-- {
			for j := i + 1; j <= n; j++ {
				if j == i+1 {
					dp[p][i][j] = 1
				} else {
					dp[p][i][j] = max(dp[p][i+1][j], dp[p][i][j-1])
					if s[i] == s[j-1] {
						dp[p][i][j] = max(dp[p][i][j], dp[p][i+1][j-1]+2)
					}
					// 改变的情况
					d := dis(s[i], s[j-1])
					if p > 0 && d <= p {
						dp[p][i][j] = max(dp[p][i][j], dp[p-d][i+1][j-1]+2)
					}
				}
			}
		}
		res = max(res, dp[p][0][n])
	}
	return res
}
