package dp

// 定义 str = [s, n] 表示 str 由 n 个字符串 s 连接构成。
//
//例如，str == ["abc", 3] =="abcabcabc" 。
//如果可以从 s2 中删除某些字符使其变为 s1，则称字符串 s1 可以从字符串 s2 获得。
//
//例如，根据定义，s1 = "abc" 可以从 s2 = "abdbec" 获得，仅需要删除加粗且用斜体标识的字符。
//现在给你两个字符串 s1 和 s2 和两个整数 n1 和 n2 。由此构造得到两个字符串，其中 str1 = [s1, n1]、str2 = [s2, n2] 。
//
//请你找出一个最大整数 m ，以满足 str = [str2, m] 可以从 str1 获得。
//
//
//
//示例 1：
//
//输入：s1 = "acb", n1 = 4, s2 = "ab", n2 = 2
//输出：2
//示例 2：
//
//输入：s1 = "acb", n1 = 1, s2 = "acb", n2 = 1
//输出：1
//
//
//提示：
//
//1 <= s1.length, s2.length <= 100
//s1 和 s2 由小写英文字母组成
//1 <= n1, n2 <= 106

// s1是s2的子序列，则称为s1可以从s2获得
// str1=acbacbacbacb
// str2=abab
// 先来一个暴力解法
func getMaxRepetitions(s1 string, n1 int, s2 string, n2 int) int {
	var str1 []byte
	var str2 []byte
	for i := 0; i < n1; i++ {
		str1 = append(str1, []byte(s1)...)
	}
	for i := 0; i < n2; i++ {
		str2 = append(str2, []byte(s2)...)
	}
	check := func(m int) bool {
		if len(str1) < len(s2)*m {
			return false
		}
		var s []byte
		for i := 0; i < m; i++ {
			s = append(s, str2...)
		}
		// 匹配
		p1, p2 := 0, 0
		for p1 < len(s) {
			for p2 < len(str1) && str1[p2] != s[p1] {
				p2++
			}
			if p2 == len(str1) {
				return false
			}
			p1++
			p2++
		}
		return true
	}
	mx := len(str1) / len(str2)
	l, r := 0, mx
	for l < r {
		mid := l + (r-l+1)/2
		if check(mid) {
			l = mid
		} else {
			r = mid - 1
		}
	}
	return l
}
