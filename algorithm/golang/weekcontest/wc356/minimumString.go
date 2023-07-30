package wc356

// 给你三个字符串 a ，b 和 c ， 你的任务是找到长度 最短 的字符串，且这三个字符串都是它的 子字符串 。
//如果有多个这样的字符串，请你返回 字典序最小 的一个。
//
//请你返回满足题目要求的字符串。
//
//注意：
//
//两个长度相同的字符串 a 和 b ，如果在第一个不相同的字符处，a 的字母在字母表中比 b 的字母 靠前 ，那么字符串 a 比字符串 b 字典序小 。
//子字符串 是一个字符串中一段连续的字符序列。
//
//
//示例 1：
//
//输入：a = "abc", b = "bca", c = "aaa"
//输出："aaabca"
//解释：字符串 "aaabca" 包含所有三个字符串：a = ans[2...4] ，b = ans[3..5] ，c = ans[0..2] 。结果字符串的长度至少为 6 ，且"aaabca" 是字典序最小的一个。
//示例 2：
//
//输入：a = "ab", b = "ba", c = "aba"
//输出："aba"
//解释：字符串 "aba" 包含所有三个字符串：a = ans[0..1] ，b = ans[1..2] ，c = ans[0..2] 。由于 c 的长度为 3 ，结果字符串的长度至少为 3 。"aba" 是字典序最小的一个。
//
//
//提示：
//
//1 <= a.length, b.length, c.length <= 100
//a ，b ，c 只包含小写英文字母。

func minimumString(a string, b string, c string) string {
	merge := func(s1 string, s2 string) string {
		for i := 0; i < len(s1); i++ {
			p1, p2 := i, 0
			for p1 < len(s1) && p2 < len(s2) && s1[p1] == s2[p2] {
				p1++
				p2++
			}
			if p2 == len(s2) {
				return s1
			}
			if p1 == len(s1) {
				return s1 + s2[p2:]
			}
		}
		return s1 + s2
	}
	visit := make([]bool, 3)
	res := a + b + c
	arr := []string{a, b, c}
	var dfs func(idx int, path string)
	dfs = func(idx int, path string) {
		if idx == 3 {
			if len(path) < len(res) || (len(path) == len(res) && path < res) {
				res = path
				return
			}
		}
		for i := 0; i < 3; i++ {
			if !visit[i] {
				visit[i] = true
				dfs(idx+1, merge(path, arr[i]))
				visit[i] = false
			}
		}
	}
	dfs(0, "")
	return res
}
