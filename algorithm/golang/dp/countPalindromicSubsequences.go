package dp

//给定一个字符串 s，返回 s 中不同的非空「回文子序列」个数 。
//
//通过从 s 中删除 0 个或多个字符来获得子序列。
//
//如果一个字符序列与它反转后的字符序列一致，那么它是「回文字符序列」。
//
//如果有某个 i , 满足 ai != bi ，则两个序列 a1, a2, ... 和 b1, b2, ... 不同。
//
//注意：
//
//结果可能很大，你需要对 109 + 7 取模 。
//
//
//示例 1：
//
//输入：s = 'bccb'
//输出：6
//解释：6 个不同的非空回文子字符序列分别为：'b', 'c', 'bb', 'cc', 'bcb', 'bccb'。
//注意：'bcb' 虽然出现两次但仅计数一次。
//示例 2：
//
//输入：s = 'abcdabcdabcdabcdabcdabcdabcdabcddcbadcbadcbadcbadcbadcbadcbadcba'
//输出：104860361
//解释：共有 3104860382 个不同的非空回文子序列，104860361 对 109 + 7 取模后的值。
//
//
//提示：
//
//1 <= s.length <= 1000
//s[i] 仅包含 'a', 'b', 'c' 或 'd'
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/count-different-palindromic-subsequences
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

//太难了，最终还是看答案
func countPalindromicSubsequences(s string) int {
	mod := 1_000_000_007
	dp := make([][][]int, 4)
	for i := 0; i < 4; i++ {
		dp[i] = make([][]int, len(s))
		for j := 0; j < len(s); j++ {
			dp[i][j] = make([]int, len(s))
		}
	}
	for i := 0; i < 4; i++ {
		for j := 0; j < len(s); j++ {
			//dp[i,j,j]
			if i == int(s[j]-'a') {
				dp[i][j][j] = 1
			} else {
				dp[i][j][j] = 0
			}
		}
	}
	for j := len(s) - 2; j >= 0; j-- {
		for k := j + 1; k < len(s); k++ {
			for i := 0; i < 4; i++ {
				ch := byte(i + 'a')
				if ch == s[j] && ch == s[k] {
					dp[i][j][k] = 2
					for l := 0; l < 4; l++ {
						dp[i][j][k] = (dp[i][j][k] + dp[l][j+1][k-1]) % mod
					}
				} else if ch == s[j] {
					dp[i][j][k] = dp[i][j][k-1]
				} else if ch == s[k] {
					dp[i][j][k] = dp[i][j+1][k]
				} else {
					dp[i][j][k] = dp[i][j+1][k-1]
				}
			}
		}
	}
	res := 0
	for i := 0; i < 4; i++ {
		res = (res + dp[i][0][len(s)-1]) % mod
	}
	return res
}
