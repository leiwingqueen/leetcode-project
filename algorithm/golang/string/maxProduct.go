package string

// Given a string array words, return the maximum value of length(word[i]) * length(word[j]) where the two words do not share common letters. If no such two words exist, return 0.
//
//
//
//Example 1:
//
//Input: words = ["abcw","baz","foo","bar","xtfn","abcdef"]
//Output: 16
//Explanation: The two words can be "abcw", "xtfn".
//Example 2:
//
//Input: words = ["a","ab","abc","d","cd","bcd","abcd"]
//Output: 4
//Explanation: The two words can be "ab", "cd".
//Example 3:
//
//Input: words = ["a","aa","aaa","aaaa"]
//Output: 0
//Explanation: No such pair of words.
//
//
//Constraints:
//
//2 <= words.length <= 1000
//1 <= words[i].length <= 1000
//words[i] consists only of lowercase English letters.

// 时间复杂度O(n^2),n是数组的长度，是否有更高效的做法？
func maxProduct(words []string) int {
	n := len(words)
	convert := func(word string) int {
		res := 0
		for _, w := range word {
			i := int(w - 'a')
			res |= 1 << i
		}
		return res
	}
	res := 0
	for i := 0; i < n-1; i++ {
		for j := i + 1; j < n; j++ {
			a1, a2 := convert(words[i]), convert(words[j])
			if a1&a2 == 0 && len(words[i])*len(words[j]) > res {
				res = len(words[i]) * len(words[j])
			}
		}
	}
	return res
}

func maxProduct3(words []string) int {
	n := len(words)
	convert := func(word string) int {
		res := 0
		for _, w := range word {
			i := int(w - 'a')
			res |= 1 << i
		}
		return res
	}
	masks := make([]int, n)
	for i, word := range words {
		masks[i] = convert(word)
	}
	res := 0
	for i := 0; i < n-1; i++ {
		for j := i + 1; j < n; j++ {
			a1, a2 := masks[i], masks[j]
			if a1&a2 == 0 && len(words[i])*len(words[j]) > res {
				res = len(words[i]) * len(words[j])
			}
		}
	}
	return res
}

func maxProduct2(words []string) int {
	max := func(a, b int) int {
		if a > b {
			return a
		} else {
			return b
		}
	}
	n := len(words)
	convert := func(word string) int {
		res := 0
		for _, w := range word {
			i := int(w - 'a')
			res |= 1 << i
		}
		return res
	}
	res := 0
	mp := make(map[int]int)
	for i := 0; i < n; i++ {
		a := convert(words[i])
		for k, v := range mp {
			if a&k == 0 {
				res = max(len(words[i])*v, res)
			}
		}
		if v, ok := mp[a]; ok {
			mp[a] = max(v, len(words[i]))
		} else {
			mp[a] = len(words[i])
		}
	}
	return res
}
