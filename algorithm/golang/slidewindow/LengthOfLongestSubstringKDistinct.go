package slidewindow

//给定一个字符串 s ，找出 至多 包含 k 个不同字符的最长子串 T。
//
// 
//
//示例 1:
//
//输入: s = "eceba", k = 2
//输出: 3
//解释: 则 T 为 "ece"，所以长度为 3。
//示例 2:
//
//输入: s = "aa", k = 1
//输出: 2
//解释: 则 T 为 "aa"，所以长度为 2。
// 
//
//提示：
//
//1 <= s.length <= 5 * 104
//0 <= k <= 50
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/longest-substring-with-at-most-k-distinct-characters
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

func lengthOfLongestSubstringKDistinct(s string, k int) int {
	mp := make(map[byte]int)
	cnt := 0
	l := 0
	r := 0
	res := 0
	for r < len(s) {
		//窗口右移
		if mp[s[r]] == 0 {
			cnt++
		}
		mp[s[r]]++
		r++
		if cnt <= k {
			if r-l > res {
				res = r - l
			}
		} else {
			//窗口左移
			for l < r && cnt > k {
				mp[s[l]]--
				if mp[s[l]] == 0 {
					cnt--
				}
				l++
			}
		}
	}
	return res
}
