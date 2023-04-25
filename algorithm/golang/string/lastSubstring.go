package string

// 给你一个字符串 s ，找出它的所有子串并按字典序排列，返回排在最后的那个子串。
//
//
//
//示例 1：
//
//输入：s = "abab"
//输出："bab"
//解释：我们可以找出 7 个子串 ["a", "ab", "aba", "abab", "b", "ba", "bab"]。按字典序排在最后的子串是 "bab"。
//示例 2：
//
//输入：s = "leetcode"
//输出："tcode"
//
//
//提示：
//
//1 <= s.length <= 4 * 105
//s 仅含有小写英文字符。
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/last-substring-in-lexicographical-order
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// 错误，果然不是那么简单
func lastSubstring(s string) string {
	idx := 0
	mx := s[0]
	for i := 1; i < len(s); i++ {
		if s[i] > mx {
			mx = s[i]
			idx = i
		}
	}
	return s[idx:]
}

// 简单处理，但是超时
func lastSubstring2(s string) string {
	mx := s[0]
	for i := 1; i < len(s); i++ {
		if s[i] > mx {
			mx = s[i]
		}
	}
	res := ""
	for i := 0; i < len(s); i++ {
		if s[i] == mx {
			if s[i:] > res {
				res = s[i:]
			}
		}
	}
	return res
}

// 还是超时
func lastSubstring3(s string) string {
	n := len(s)
	mx := s[0]
	for i := 1; i < n; i++ {
		if s[i] > mx {
			mx = s[i]
		}
	}
	p1 := 0
	for p1 < n && s[p1] != mx {
		p1++
	}
	p2 := p1 + 1
	for p2 < n && s[p2] != mx {
		p2++
	}
	if p2 == n {
		return s[p1:]
	}
	for p2 < n {
		k := 0
		for p2+k < n && s[p1+k] == s[p2+k] {
			k++
		}
		if p2+k == n {
			// 找下一个符合条件的下标
			p2++
			for p2 < n && s[p2] != mx {
				p2++
			}
			if p2 == n {
				return s[p1:]
			}
			continue
		}
		if s[p1+k] < s[p2+k] {
			// p2较大的场景
			p1 = p2 + 1
			for p1 < n && s[p1] != mx {
				p1++
			}
			p1, p2 = p2, p1
		} else {
			// p1较大
			p2 = p2 + 1
			for p2 < n && s[p2] != mx {
				p2++
			}
		}
	}
	return s[p1:]
}
