package bwc157

// 给你一个字符串 word。
//
//返回以 首尾字母相同 且 长度至少为 4 的 不相交子字符串 的最大数量。
//
//子字符串 是字符串中连续的 非空 字符序列。
//
//
//
//示例 1：
//
//输入： word = "abcdeafdef"
//
//输出： 2
//
//解释：
//
//两个子字符串是 "abcdea" 和 "fdef"。
//
//示例 2：
//
//输入： word = "bcdaaaab"
//
//输出： 1
//
//解释：
//
//唯一的子字符串是 "aaaa"。注意我们 不能 同时选择 "bcdaaaab"，因为它和另一个子字符串有重叠。
//
//
//
//提示：
//
//1 <= word.length <= 2 * 105
//word 仅由小写英文字母组成。

func maxSubstrings(word string) int {
	n := len(word)
	pos := make([][]int, 26)
	dp := make([]int, n+1)
	dp[0] = 0
	for i := 1; i <= n; i++ {
		dp[i] = dp[i-1]
		// 选择word[i],查找上一个合适的字符
		x := word[i-1] - 'a'
		found := false
		if len(pos[x]) > 0 {
			j := len(pos[x]) - 1
			for ; j >= 0; j-- {
				if i-pos[x][j] >= 4 {
					found = true
					break
				}
			}
			if found {
				dp[i] = max(dp[i], dp[pos[x][j]]+1)
			}
		}
		pos[x] = append(pos[x], i-1)
	}
	return dp[n]
}
