package wc443

// 给你两个字符串 s 和 t。
//
//你可以从 s 中选择一个子串（可以为空）以及从 t 中选择一个子串（可以为空），然后将它们 按顺序 连接，得到一个新的字符串。
//
//返回可以由上述方法构造出的 最长 回文串的长度。
//
//回文串 是指正着读和反着读都相同的字符串。
//
//子字符串 是指字符串中的一个连续字符序列。
//
//
//
//示例 1：
//
//输入： s = "a", t = "a"
//
//输出： 2
//
//解释：
//
//从 s 中选择 "a"，从 t 中选择 "a"，拼接得到 "aa"，这是一个长度为 2 的回文串。
//
//示例 2：
//
//输入： s = "abc", t = "def"
//
//输出： 1
//
//解释：
//
//由于两个字符串的所有字符都不同，最长的回文串只能是任意一个单独的字符，因此答案是 1。
//
//示例 3：
//
//输入： s = "b", t = "aaaa"
//
//输出： 4
//
//解释：
//
//可以选择 "aaaa" 作为回文串，其长度为 4。
//
//示例 4：
//
//输入： s = "abcde", t = "ecdba"
//
//输出： 5
//
//解释：
//
//从 s 中选择 "abc"，从 t 中选择 "ba"，拼接得到 "abcba"，这是一个长度为 5 的回文串。
//
//
//
//提示：
//
//1 <= s.length, t.length <= 30
//s 和 t 仅由小写英文字母组成。

// 超时，就差最后的2个用例
func longestPalindrome(s string, t string) int {
	m, n := len(s), len(t)
	// 预计算回文串
	dp1 := make([][]bool, m)
	for i := 0; i < m; i++ {
		dp1[i] = make([]bool, m)
		dp1[i][i] = true
	}
	for i := m - 1; i >= 0; i-- {
		for j := i + 1; j < m; j++ {
			dp1[i][j] = s[i] == s[j] && (i+1 > j-1 || dp1[i+1][j-1])
		}
	}
	dp2 := make([][]bool, n)
	for i := 0; i < n; i++ {
		dp2[i] = make([]bool, n)
		dp2[i][i] = true
	}
	for i := n - 1; i >= 0; i-- {
		for j := i + 1; j < n; j++ {
			dp2[i][j] = t[i] == t[j] && (i+1 >= j-1 || dp2[i+1][j-1])
		}
	}
	res := 0
	t1 := make([]int, m)
	for i := 0; i < m; i++ {
		for j := m - 1; j >= i; j-- {
			if dp1[i][j] {
				t1[i] = j - i + 1
				// 只选择s的回文串
				res = max(res, t1[i])
				break
			}
		}
	}
	t2 := make([]int, n)
	for i := n - 1; i >= 0; i-- {
		for j := 0; j <= i; j++ {
			if dp2[j][i] {
				t2[i] = i - j + 1
				// 只选择t的回文串
				res = max(res, t2[i])
				break
			}
		}
	}
	// l,r分别作为s和t的左右边界
	for l := 0; l < m; l++ {
		for r := 0; r < n; r++ {
			if s[l] != t[r] {
				continue
			}
			p1, p2 := l, r
			size := 0
			for {
				// 这里再选择s[p1:]的最大回文串，或者t[:p2]的最大回文串
				k := 0
				if p1 < m {
					k = max(k, t1[p1])
				}
				if p2 >= 0 {
					k = max(k, t2[p2])
				}
				res = max(res, size+k)
				if p1 >= m || p2 < 0 || s[p1] != t[p2] {
					break
				}
				p1++
				p2--
				size += 2
			}
		}
	}
	return res
}

func longestPalindrome2(s string, t string) int {
	m, n := len(s), len(t)
	// 预计算回文串
	dp1 := make([][]bool, m)
	for i := 0; i < m; i++ {
		dp1[i] = make([]bool, m)
		dp1[i][i] = true
	}
	for i := m - 1; i >= 0; i-- {
		for j := i + 1; j < m; j++ {
			dp1[i][j] = s[i] == s[j] && (i+1 > j-1 || dp1[i+1][j-1])
		}
	}
	dp2 := make([][]bool, n)
	for i := 0; i < n; i++ {
		dp2[i] = make([]bool, n)
		dp2[i][i] = true
	}
	for i := n - 1; i >= 0; i-- {
		for j := i + 1; j < n; j++ {
			dp2[i][j] = t[i] == t[j] && (i+1 >= j-1 || dp2[i+1][j-1])
		}
	}
	res := 0
	t1 := make([]int, m)
	for i := 0; i < m; i++ {
		for j := m - 1; j >= i; j-- {
			if dp1[i][j] {
				t1[i] = j - i + 1
				// 只选择s的回文串
				res = max(res, t1[i])
				break
			}
		}
	}
	t2 := make([]int, n)
	for i := n - 1; i >= 0; i-- {
		for j := 0; j <= i; j++ {
			if dp2[j][i] {
				t2[i] = i - j + 1
				// 只选择t的回文串
				res = max(res, t2[i])
				break
			}
		}
	}
	// l,r分别作为s和t的左右边界
	dp3 := make([][]int, m)
	for i := 0; i < m; i++ {
		dp3[i] = make([]int, n)
	}
	if s[m-1] == t[0] {
		dp3[m-1][0] = 2
		res = max(res, 2)
	}
	// 初始化
	for i := 1; i < n; i++ {
		if s[m-1] == t[i] {
			dp3[m-1][i] = t2[i-1] + 2
			res = max(res, dp3[m-1][i])
		}
	}
	for i := 0; i < m-1; i++ {
		if s[i] == t[0] {
			dp3[i][0] = t1[i+1] + 2
			res = max(res, dp3[i][0])
		}
	}
	for i := m - 2; i >= 0; i-- {
		for j := 1; j < n; j++ {
			if s[i] != t[j] {
				continue
			}
			dp3[i][j] = max(dp3[i+1][j-1]+2, t1[i+1]+2, t2[j-1]+2)
			res = max(res, dp3[i][j])
		}
	}
	return res
}
