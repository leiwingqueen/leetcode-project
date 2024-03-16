package array

// 对于字符串 s 和 t，只有在 s = t + t + t + ... + t + t（t 自身连接 1 次或多次）时，我们才认定 “t 能除尽 s”。
//
//给定两个字符串 str1 和 str2 。返回 最长字符串 x，要求满足 x 能除尽 str1 且 x 能除尽 str2 。
//
//
//
//示例 1：
//
//输入：str1 = "ABCABC", str2 = "ABC"
//输出："ABC"
//示例 2：
//
//输入：str1 = "ABABAB", str2 = "ABAB"
//输出："AB"
//示例 3：
//
//输入：str1 = "LEET", str2 = "CODE"
//输出：""
//
//
//提示：
//
//1 <= str1.length, str2.length <= 1000
//str1 和 str2 由大写英文字母组成

func gcdOfStrings(str1 string, str2 string) string {
	check := func(s string, d string) bool {
		n := len(s)
		size := len(d)
		if size > n || n%size != 0 {
			return false
		}
		p1 := 0
		for p1 < n {
			for i := 0; i < size; i++ {
				if d[i] != s[p1] {
					return false
				}
				p1++
			}
		}
		return true
	}
	mx := len(str1)
	if len(str2) < mx {
		mx = len(str2)
	}
	for i := mx; i >= 1; i-- {
		if check(str1, str1[:i]) && check(str2, str1[:i]) {
			return str1[:i]
		}
	}
	return ""
}
