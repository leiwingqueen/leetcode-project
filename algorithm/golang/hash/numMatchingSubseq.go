package hash

// 给定字符串 s 和字符串数组 words, 返回  words[i] 中是s的子序列的单词个数 。
//
//字符串的 子序列 是从原始字符串中生成的新字符串，可以从中删去一些字符(可以是none)，而不改变其余字符的相对顺序。
//
//例如， “ace” 是 “abcde” 的子序列。
//
//
//示例 1:
//
//输入: s = "abcde", words = ["a","bb","acd","ace"]
//输出: 3
//解释: 有三个是 s 的子序列的单词: "a", "acd", "ace"。
//Example 2:
//
//输入: s = "dsahjpjauf", words = ["ahjpjau","ja","ahbwzgqnuk","tnmlanowax"]
//输出: 2
//
//
//提示:
//
//1 <= s.length <= 5 * 104
//1 <= words.length <= 5000
//1 <= words[i].length <= 50
//words[i]和 s 都只由小写字母组成。
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/number-of-matching-subsequences
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

func numMatchingSubseq(s string, words []string) int {
	index := make([][]int, 26)
	for i := 0; i < len(s); i++ {
		idx := s[i] - 'a'
		index[idx] = append(index[idx], i)
	}
	var check func(word string) bool
	check = func(word string) bool {
		pre := -1
		for i := 0; i < len(word); i++ {
			idx := word[i] - 'a'
			if len(index[idx]) == 0 || index[idx][len(index[idx])-1] <= pre {
				return false
			}
			l := 0
			r := len(index[idx]) - 1
			for l < r {
				mid := l + (r-l)/2
				if index[idx][mid] > pre {
					r = mid
				} else {
					l = mid + 1
				}
			}
			pre = index[idx][l]
		}
		return true
	}
	cnt := 0
	for _, word := range words {
		if check(word) {
			cnt++
		}
	}
	return cnt
}
