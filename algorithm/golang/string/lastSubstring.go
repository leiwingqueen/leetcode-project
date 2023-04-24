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

func lastSubstring2(s string) string {
	n := len(s)
	var dfs func(mp map[int]struct{}) int
	dfs = func(mp map[int]struct{}) int {
		var mx uint8
		mp2 := make(map[int]struct{})
		for i := range mp {
			var next uint8
			if i+1 < n {
				next = s[i+1]
			}
			if next > mx {
				mp2 = make(map[int]struct{})
				mp2[i+1] = struct{}{}
				mx = next
			} else if next == mx {
				mp2[i+1] = struct{}{}
			}
		}
		if mx == 0 {
		}
	}
}
