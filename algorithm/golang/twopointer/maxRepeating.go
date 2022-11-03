package twopointer

// 给你一个字符串 sequence ，如果字符串 word 连续重复 k 次形成的字符串是 sequence 的一个子字符串，那么单词 word 的 重复值为 k 。单词 word 的 最大重复值 是单词 word 在 sequence 中最大的重复值。如果 word 不是 sequence 的子串，那么重复值 k 为 0 。
//
//给你一个字符串 sequence 和 word ，请你返回 最大重复值 k 。
//
//
//
//示例 1：
//
//输入：sequence = "ababc", word = "ab"
//输出：2
//解释："abab" 是 "ababc" 的子字符串。
//示例 2：
//
//输入：sequence = "ababc", word = "ba"
//输出：1
//解释："ba" 是 "ababc" 的子字符串，但 "baba" 不是 "ababc" 的子字符串。
//示例 3：
//
//输入：sequence = "ababc", word = "ac"
//输出：0
//解释："ac" 不是 "ababc" 的子字符串。
//
//
//提示：
//
//1 <= sequence.length <= 100
//1 <= word.length <= 100
//sequence 和 word 都只包含小写英文字母。
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/maximum-repeating-substring
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

func maxRepeating(sequence string, word string) int {
	p1 := 0
	p2 := 0
	n1 := len(sequence)
	n2 := len(word)
	res := 0
	for p1 < n1 {
		p2 = 0
		cnt := 0
		p3 := p1
		for p3 < n1 && sequence[p3] == word[p2] {
			p3++
			p2++
			if p2 == n2 {
				cnt++
				p2 = 0
			}
		}
		if cnt > res {
			res = cnt
		}
		p1++
	}
	return res
}
