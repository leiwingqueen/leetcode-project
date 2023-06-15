package array

//给你一个字符串 s，请你对 s 的子串进行检测。
//
// 每次检测，待检子串都可以表示为 queries[i] = [left, right, k]。我们可以 重新排列 子串 s[left], ..., s[
//right]，并从中选择 最多 k 项替换成任何小写英文字母。
//
// 如果在上述检测过程中，子串可以变成回文形式的字符串，那么检测结果为 true，否则结果为 false。
//
// 返回答案数组 answer[]，其中 answer[i] 是第 i 个待检子串 queries[i] 的检测结果。
//
// 注意：在替换时，子串中的每个字母都必须作为 独立的 项进行计数，也就是说，如果 s[left..right] = "aaa" 且 k = 2，我们只能替换
//其中的两个字母。（另外，任何检测都不会修改原始字符串 s，可以认为每次检测都是独立的）
//
//
//
// 示例：
//
// 输入：s = "abcda", queries = [[3,3,0],[1,2,0],[0,3,1],[0,3,2],[0,4,1]]
//输出：[true,false,false,true,true]
//解释：
//queries[0] : 子串 = "d"，回文。
//queries[1] : 子串 = "bc"，不是回文。
//queries[2] : 子串 = "abcd"，只替换 1 个字符是变不成回文串的。
//queries[3] : 子串 = "abcd"，可以变成回文的 "abba"。 也可以变成 "baab"，先重新排序变成 "bacd"，然后把 "cd"
//替换为 "ab"。
//queries[4] : 子串 = "abcda"，可以变成回文的 "abcba"。
//
//
//
//
// 提示：
//
//
// 1 <= s.length, queries.length <= 10^5
// 0 <= queries[i][0] <= queries[i][1] < s.length
// 0 <= queries[i][2] <= s.length
// s 中只有小写英文字母
//
//
// Related Topics 位运算 数组 哈希表 字符串 前缀和 👍 120 👎 0

// 这里漏了一个重排的条件，答案错误
// 既然允许重排，这意味着我们需需要统计每个小写字母的数量即可
func canMakePaliQueries(s string, queries [][]int) []bool {
	check := func(l, r, k int) bool {
		cnt := 0
		for l < r {
			if s[l] != s[r] {
				cnt++
				if cnt > k {
					return false
				}
			}
			l++
			r--
		}
		return true
	}
	res := make([]bool, len(queries))
	for i, query := range queries {
		res[i] = check(query[0], query[1], query[2])
	}
	return res
}

// 前缀和
func canMakePaliQueries2(s string, queries [][]int) []bool {
	n := len(s)
	preSum := make([][]int, n+1)
	preSum[0] = make([]int, 26)
	for i := 0; i < n; i++ {
		preSum[i+1] = make([]int, 26)
		for j := 0; j < 26; j++ {
			preSum[i+1][j] = preSum[i][j]
		}
		idx := s[i] - 'a'
		preSum[i+1][idx]++
	}
	check := func(l, r, k int) bool {
		// 统计字母为奇数的个数
		cnt := 0
		for i := 0; i < 26; i++ {
			c := preSum[r+1][i] - preSum[l][i]
			if c%2 == 1 {
				cnt++
			}
		}
		// 这些落单的字母只需要改变一半即可
		return cnt/2 <= k
	}
	res := make([]bool, len(queries))
	for i, query := range queries {
		res[i] = check(query[0], query[1], query[2])
	}
	return res
}
