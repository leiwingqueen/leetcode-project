package slidewindow

//给定一个字符串 s ，找出 至多 包含两个不同字符的最长子串 t ，并返回该子串的长度。
//
//示例 1:
//
//输入: "eceba"
//输出: 3
//解释: t 是 "ece"，长度为3。
//示例 2:
//
//输入: "ccaabbb"
//输出: 5
//解释: t 是 "aabbb"，长度为5。
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/longest-substring-with-at-most-two-distinct-characters
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
func lengthOfLongestSubstringTwoDistinct(s string) int {
	bitmap := make(map[byte]int)
	cnt := 0
	l := 0
	r := 0
	res := 0
	for r < len(s) {
		//窗口右移
		if bitmap[s[r]] == 0 {
			cnt++
		}
		bitmap[s[r]]++
		if cnt > 2 {
			if r-l > res {
				res = r - l
			}
			//窗口左移
			for cnt > 2 && l < r {
				bitmap[s[l]]--
				if bitmap[s[l]] == 0 {
					cnt--
				}
				l++
			}
		}
		r++
	}
	if r-l > res {
		res = r - l
	}
	return res
}
