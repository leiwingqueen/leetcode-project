package wc389

// 给你一个字符串 s 和一个字符 c 。返回在字符串 s 中并且以 c 字符开头和结尾的非空子字符串的总数。
//
//
//
//示例 1：
//
//输入：s = "abada", c = "a"
//
//输出：6
//
//解释：以 "a" 开头和结尾的子字符串有： "abada"、"abada"、"abada"、"abada"、"abada"、"abada"。
//
//示例 2：
//
//输入：s = "zzz", c = "z"
//
//输出：6
//
//解释：字符串 s 中总共有 6 个子字符串，并且它们都以 "z" 开头和结尾。
//
//
//
//提示：
//
//1 <= s.length <= 105
//s 和 c 均由小写英文字母组成。

func countSubstrings(s string, c byte) int64 {
	cnt := 0
	n := len(s)
	var res int64
	for i := 0; i < n; i++ {
		if s[i] == c {
			res += int64(cnt) + 1
			cnt++
		}
	}
	return res
}
