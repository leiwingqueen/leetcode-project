package array

// 给你两个字符串 s 和 t ，请你找出 s 中的非空子串的数目，这些子串满足替换 一个不同字符 以后，是 t 串的子串。换言之，请你找到 s 和 t 串中 恰好 只有一个字符不同的子字符串对的数目。
//
//比方说， "computer" and "computation" 只有一个字符不同： 'e'/'a' ，所以这一对子字符串会给答案加 1 。
//
//请你返回满足上述条件的不同子字符串对数目。
//
//一个 子字符串 是一个字符串中连续的字符。
//
//
//
//示例 1：
//
//输入：s = "aba", t = "baba"
//输出：6
//解释：以下为只相差 1 个字符的 s 和 t 串的子字符串对：
//("aba", "baba")
//("aba", "baba")
//("aba", "baba")
//("aba", "baba")
//("aba", "baba")
//("aba", "baba")
//加粗部分分别表示 s 和 t 串选出来的子字符串。
//示例 2：
//输入：s = "ab", t = "bb"
//输出：3
//解释：以下为只相差 1 个字符的 s 和 t 串的子字符串对：
//("ab", "bb")
//("ab", "bb")
//("ab", "bb")
//加粗部分分别表示 s 和 t 串选出来的子字符串。
//示例 3：
//输入：s = "a", t = "a"
//输出：0
//示例 4：
//
//输入：s = "abe", t = "bbc"
//输出：10
//
//
//提示：
//
//1 <= s.length, t.length <= 100
//s 和 t 都只包含小写英文字母。
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/count-substrings-that-differ-by-one-character
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// 暴力解法。时间复杂度O(n^4)，勉强通过
func countSubstrings(s string, t string) int {
	l1, l2 := len(s), len(t)
	mxLen := l1
	if l2 < l1 {
		mxLen = l2
	}
	check := func(i, j, k int) bool {
		cnt := 0
		for l := 0; l < k; l++ {
			if s[i+l] != t[j+l] {
				cnt++
			}
			if cnt > 2 {
				return false
			}
		}
		return cnt == 1
	}
	res := 0
	for k := 1; k <= mxLen; k++ {
		for i := 0; i <= l1-k; i++ {
			for j := 0; j <= l2-k; j++ {
				if check(i, j, k) {
					res++
				}
			}
		}
	}
	return res
}

// 优化解法
func countSubstrings2(s string, t string) int {
	res := 0
	for i := 0; i < len(s); i++ {
		for j := 0; j < len(t); j++ {
			diff := 0
			for k := 0; i+k < len(s) && j+k < len(t); k++ {
				if s[i+k] != s[j+k] {
					diff++
				}
				if diff == 1 {
					res++
				} else if diff > 1 {
					break
				}
			}
		}
	}
	return res
}
