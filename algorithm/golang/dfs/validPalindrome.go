package dfs

// 给你一个字符串 s，最多 可以从中删除一个字符。
//
//请你判断 s 是否能成为回文字符串：如果能，返回 true ；否则，返回 false 。
//
//
//
//示例 1：
//
//输入：s = "aba"
//输出：true
//示例 2：
//
//输入：s = "abca"
//输出：true
//解释：你可以删除字符 'c' 。
//示例 3：
//
//输入：s = "abc"
//输出：false
//
//
//提示：
//
//1 <= s.length <= 105
//s 由小写英文字母组成

// 朴素的dfs
func validPalindrome(s string) bool {
	var dfs func(i, j int, del bool) bool
	dfs = func(i, j int, del bool) bool {
		if i >= j {
			return true
		}
		if del {
			if s[i] != s[j] {
				return false
			}
			return dfs(i+1, j-1, del)
		} else {
			if s[i] == s[j] && dfs(i+1, j-1, del) {
				return true
			}
			// 删除左边的
			return dfs(i+1, j, true) || dfs(i, j-1, true)
		}
	}
	return dfs(0, len(s)-1, false)
}

func validPalindrome2(s string) bool {
	check := func(i, j int) bool {
		for i < j {
			if s[i] != s[j] {
				return false
			}
			i++
			j--
		}
		return true
	}
	var dfs func(i, j int) bool
	dfs = func(i, j int) bool {
		if i >= j {
			return true
		}
		if check(i, j) {
			return true
		}
		if s[i] == s[j] && dfs(i+1, j-1) {
			return true
		}
		return check(i+1, j) || check(i, j-1)
	}
	return dfs(0, len(s)-1)
}
