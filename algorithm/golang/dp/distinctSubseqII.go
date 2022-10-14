package dp

// 给定一个字符串 s，计算 s 的 不同非空子序列 的个数。因为结果可能很大，所以返回答案需要对 10^9 + 7 取余 。
//
//字符串的 子序列 是经由原字符串删除一些（也可能不删除）字符但不改变剩余字符相对位置的一个新字符串。
//
//例如，"ace" 是 "abcde" 的一个子序列，但 "aec" 不是。
//
//
//示例 1：
//
//输入：s = "abc"
//输出：7
//解释：7 个不同的子序列分别是 "a", "b", "c", "ab", "ac", "bc", 以及 "abc"。
//示例 2：
//
//输入：s = "aba"
//输出：6
//解释：6 个不同的子序列分别是 "a", "b", "ab", "ba", "aa" 以及 "aba"。
//示例 3：
//
//输入：s = "aaa"
//输出：3
//解释：3 个不同的子序列分别是 "a", "aa" 以及 "aaa"。
//
//
//提示：
//
//1 <= s.length <= 2000
//s 仅由小写英文字母组成
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/distinct-subsequences-ii
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

func distinctSubseqII(s string) int {
	mod := 1_000_000_007
	n := len(s)
	dp := make([][][]int, n)
	for i := 0; i < n; i++ {
		dp[i] = make([][]int, n)
		for j := 0; j < n; j++ {
			dp[i][j] = make([]int, 26)
		}
	}
	dp2 := make([][]int, n)
	for i := 0; i < n; i++ {
		dp2[i] = make([]int, n)
	}
	for i := 0; i < n; i++ {
		dp[i][i][s[i]-'a'] = 1
		dp2[i][i] = 1
	}
	// 首次出现对应字母的下标
	first := make([]int, 26)
	for i := 0; i < 26; i++ {
		first[i] = -1
	}
	for i := 0; i < n; i++ {
		idx := s[i] - 'a'
		if first[idx] < 0 {
			first[idx] = i
		}
	}
	for i := 0; i < n; i++ {
		for j := 0; j < 26; j++ {
			if first[j] >= 0 && first[j] <= i {
				dp[i][0][j] = 1
				dp2[i][0]++
			}
		}
	}
	// 最后出现的位置
	last := make([][]int, n)
	for i := 0; i < n; i++ {
		last[i] = make([]int, 26)
		for j := 0; j < 26; j++ {
			last[i][j] = -1
		}
	}
	for i := 0; i < n; i++ {
		for j := 0; j <= i; j++ {
			last[i][s[j]-'a'] = j
		}
	}
	for i := 1; i < n; i++ {
		for j := 1; j < i; j++ {
			for k := 0; k < 26; k++ {
				// 找到前i+1个字符串中，最近一个字符为'a'+k的下标
				l := last[i][k]
				if l >= j {
					dp[i][j][k] = (dp[i][j][k] + dp2[l-1][j-1]) % mod
					/*for m := 0; m < 26; m++ {
						dp[i][j][k] = (dp[i][j][k] + dp[l-1][j-1][m]) % mod
					}*/
				}
				dp2[i][j] = (dp2[i][j] + dp[i][j][k]) % mod
			}
		}
	}
	res := 0
	for i := 0; i < n; i++ {
		res = (res + dp2[n-1][i]) % mod
	}
	return res
}
