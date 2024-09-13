package dfs

//
// 给你一个输入字符串 (
// s) 和一个字符模式 (
// p) ，请你实现一个支持
// '?' 和
// '*' 匹配规则的通配符匹配：
//
//
//
// '?' 可以匹配任何单个字符。
// '*' 可以匹配任意字符序列（包括空字符序列）。
//
//
//
//
// 判定匹配成功的充要条件是：字符模式必须能够 完全匹配 输入字符串（而不是部分匹配）。
//
//
//
// 示例 1：
//
//
//输入：s = "aa", p = "a"
//输出：false
//解释："a" 无法匹配 "aa" 整个字符串。
//
//
// 示例 2：
//
//
//输入：s = "aa", p = "*"
//输出：true
//解释：'*' 可以匹配任意字符串。
//
//
// 示例 3：
//
//
//输入：s = "cb", p = "?a"
//输出：false
//解释：'?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
//
//
//
//
// 提示：
//
//
// 0 <= s.length, p.length <= 2000
// s 仅由小写英文字母组成
// p 仅由小写英文字母、'?' 或 '*' 组成
//
//
// Related Topics 贪心 递归 字符串 动态规划 👍 1163 👎 0

// 超时
func isMatch(s string, p string) bool {
	n1, n2 := len(s), len(p)
	var dfs func(i, j int) bool
	dfs = func(i, j int) bool {
		if i == n1 && j == n2 {
			return true
		}
		if i == n1 {
			if p[j] != '*' {
				return false
			} else {
				return dfs(i, j+1)
			}
		}
		if j == n2 {
			return false
		}
		if p[j] == '?' {
			return dfs(i+1, j+1)
		} else if p[j] == '*' {
			return dfs(i, j+1) || dfs(i+1, j)
		} else {
			if s[i] != p[j] {
				return false
			}
			return dfs(i+1, j+1)
		}
	}
	return dfs(0, 0)
}

// 增加记忆，勉强通过
func isMatch2(s string, p string) bool {
	n1, n2 := len(s), len(p)
	mem := make(map[int]map[int]bool)
	var dfs func(i, j int) bool
	dfs = func(i, j int) bool {
		if i == n1 && j == n2 {
			return true
		}
		if _, ok := mem[i]; !ok {
			mem[i] = make(map[int]bool)
		}
		if v, ok := mem[i][j]; ok {
			return v
		}
		res := false
		defer func() {
			mem[i][j] = res
		}()
		if i == n1 {
			if p[j] != '*' {
				res = false
				return res
			} else {
				res = dfs(i, j+1)
				return res
			}
		}
		if j == n2 {
			res = false
			return false
		}
		if p[j] == '?' {
			res = dfs(i+1, j+1)
			return res
		} else if p[j] == '*' {
			res = dfs(i, j+1) || dfs(i+1, j)
			return res
		} else {
			if s[i] != p[j] {
				res = false
				return res
			}
			res = dfs(i+1, j+1)
			return res
		}
	}
	return dfs(0, 0)
}
