package greedy

// 如果一个字符串满足以下条件，则称其为 美丽字符串 ：
//
//它由英语小写字母表的前 k 个字母组成。
//它不包含任何长度为 2 或更长的回文子字符串。
//给你一个长度为 n 的美丽字符串 s 和一个正整数 k 。
//
//请你找出并返回一个长度为 n 的美丽字符串，该字符串还满足：在字典序大于 s 的所有美丽字符串中字典序最小。如果不存在这样的字符串，则返回一个空字符串。
//
//对于长度相同的两个字符串 a 和 b ，如果字符串 a 在与字符串 b 不同的第一个位置上的字符字典序更大，则字符串 a 的字典序大于字符串 b 。
//
//例如，"abcd" 的字典序比 "abcc" 更大，因为在不同的第一个位置（第四个字符）上 d 的字典序大于 c 。
//
//
//示例 1：
//
//输入：s = "abcz", k = 26
//输出："abda"
//解释：字符串 "abda" 既是美丽字符串，又满足字典序大于 "abcz" 。
//可以证明不存在字符串同时满足字典序大于 "abcz"、美丽字符串、字典序小于 "abda" 这三个条件。
//示例 2：
//
//输入：s = "dc", k = 4
//输出：""
//解释：可以证明，不存在既是美丽字符串，又字典序大于 "dc" 的字符串。
//
//
//提示：
//
//1 <= n == s.length <= 105
//4 <= k <= 26
//s 是一个美丽字符串

func smallestBeautifulString(s string, k int) string {
	n := len(s)
	res := []byte(s)
	check := func(idx int) bool {
		if idx > 0 {
			if res[idx] == res[idx-1] {
				return false
			}
		}
		if idx > 1 {
			if res[idx] == res[idx-2] {
				return false
			}
		}
		return true
	}
	i := n - 1
	for ; i >= 0; i-- {
		res[i]++
		found := false
		for res[i] <= byte('a'+k-1) {
			if check(i) {
				found = true
				break
			}
			res[i]++
		}
		if found {
			break
		}
		res[i] = 'a'
	}
	if i < 0 {
		return ""
	}
	for j := i + 1; j < n; j++ {
		for !check(j) {
			res[j]++
			if res[j] > byte('a'+k-1) {
				return ""
			}
		}
	}
	return string(res)
}
